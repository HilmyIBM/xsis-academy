package com.xsis.bc345.fe.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;
import com.xsis.bc345.fe.models.PagingView;
import com.xsis.bc345.fe.models.ProductView;
import com.xsis.bc345.fe.models.VariantView;

import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/product")
public class ProductController {
    private RestTemplate restTemplate = new RestTemplate();
    //api url
    // private  final String apiUrl = "http://localhost:8080/api/category";
    @Value("${application.api.url}")
    private String apiUrl;
    @Value("${application.page.size}")
    private Integer pageSize;
    @GetMapping("")
    public ModelAndView index(String filter, Integer currPageSize, Integer pageNumber) {
        ModelAndView view = new ModelAndView("product/index");
        ResponseEntity<PagingView> apiResponse = null;
        currPageSize = (currPageSize != null) ? currPageSize : pageSize;
        pageNumber = (pageNumber != null) ? pageNumber : 0;
        try {
            if(filter == null || filter.isBlank()){
                apiResponse = restTemplate.getForEntity(apiUrl + "/product/paginate/" + pageNumber + "/" +currPageSize, PagingView.class);
            }else{
                apiResponse = restTemplate.getForEntity(apiUrl + "/product/paginate/filter/" + filter + "/" + pageNumber + "/" +currPageSize, PagingView.class);
            }
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                view.addObject("product", apiResponse.getBody());
            }else{
                throw new Exception(apiResponse.getStatusCode().toString()+ ": "+ apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("filter", filter);
        view.addObject("currPageSize", currPageSize);
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/product/add");
        ResponseEntity<VariantView[]> resVariant = null;
        try {
            resVariant = restTemplate.getForEntity(apiUrl + "/variant", VariantView[].class);
            if(resVariant.getStatusCode() == HttpStatus.OK){
                VariantView[] data = resVariant.getBody();
                view.addObject("variant", data);
            }else{
                throw new Exception(resVariant.getStatusCode().toString() + ": " + resVariant.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
            // TODO: handle exception
        }
        view.addObject("title", "Add Product");
        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute ProductView product, @RequestParam("setImage") MultipartFile file) throws IOException{
        ResponseEntity<ProductView> apiResponse = null;
        try {
            if(!file.isEmpty()){
                String fileName = file.getOriginalFilename();
                Path fileNamePath = Paths.get("src/main/resources/static/lib/images/" + fileName);
                Files.write(fileNamePath, file.getBytes());
                product.setImage(fileName);
            }
            apiResponse = restTemplate.postForEntity(apiUrl + "/product", product,ProductView.class);
            if(apiResponse.getStatusCode() == HttpStatus.CREATED){
                return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.OK);
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable int id){
        ModelAndView view = new ModelAndView("/product/edit");
        ResponseEntity<ProductView> apiResponse = null;
        ResponseEntity<VariantView[]> resVariant = null;
        try {
            resVariant = restTemplate.getForEntity(apiUrl+"/variant", VariantView[].class);
            if(resVariant.getStatusCode() == HttpStatus.OK){
                view.addObject("variant", resVariant.getBody());
            }
            apiResponse = restTemplate.getForEntity(apiUrl +"/product/id/" + id, ProductView.class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                view.addObject("product", apiResponse.getBody());
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody() + "  HALOOOOOOOOOOOOOOOOOOOOOO INI ERRORRRRR");
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Edit Product");
        return view;
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute ProductView product, @RequestParam("setImage") MultipartFile file, @RequestParam("currImage") String img)throws IOException {
        ResponseEntity<ProductView> apiResponse = null;
        try {
            String fileName = img;
            if(!file.isEmpty()){ // ini kosong
                if(!img.isEmpty()){ // ini ada
                    Path fileNamePathChange = Paths.get("src/main/resources/static/lib/images/" + img);
                    Files.delete(fileNamePathChange);
                }
                fileName = file.getOriginalFilename();
                Path fileNamePath = Paths.get("src/main/resources/static/lib/images/" + fileName);
                Files.write(fileNamePath, file.getBytes());
            }
            product.setImage(fileName);
            restTemplate.put(apiUrl+"/product", product);
            apiResponse = restTemplate.getForEntity(apiUrl +"/product/id/" + product.getId(), ProductView.class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.OK);
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": "+ apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}")
    ModelAndView delete(@PathVariable int id){
        ModelAndView view = new ModelAndView("/product/delete");
        ResponseEntity<ProductView> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl +"/product/id/" + id, ProductView.class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                view.addObject("title", "Delete Product");
                view.addObject("product", apiResponse.getBody());
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody() + "  HALOOOOOOOOOOOOOOOOOOOOOO INI ERRORRRRR");
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }

    @PostMapping("/delete-confirm")
    public ResponseEntity<?> deleteConfirm(@ModelAttribute ProductView product) {
        ResponseEntity<ProductView> apiResponse = null;
        try {
            apiResponse = restTemplate.exchange(apiUrl + "/product/delete/" + product.getId() + "/3", HttpMethod.DELETE, new HttpEntity<ProductView>(product), ProductView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.OK);
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
