package com.xsis.bc345.fe.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
@RequestMapping("/category")
public class CategoryController {
    // List<CategoryView> data = new ArrayList<>();
    //http client
    private RestTemplate restTemplate = new RestTemplate();

    //api url
    private  final String apiUrl = "http://localhost:8080/api/category";
    // CategoryController(){
    //     data.add(new Category());
    //     data.get(0).setId(1);
    //     data.get(0).setName("Makanan");
    //     data.get(0).setDesc("Kategori Makanan");
    //     data.get(0).setCreateBy(1);
    //     data.get(0).setDeleted(false);
    //     data.get(0).setCreatedDate(LocalDateTime.now());

    //     data.add(new Category());
    //     data.get(1).setId(2);
    //     data.get(1).setName("Minuman");
    //     data.get(1).setDesc("Kategori Minuman");
    //     data.get(1).setCreateBy(1);
    //     data.get(1).setDeleted(false);
    //     data.get(1).setCreatedDate(LocalDateTime.now());

    //     data.add(new Category());
    //     data.get(2).setId(2);
    //     data.get(2).setName("Obat-obatan");
    //     data.get(2).setDesc("Kategori Obat-obatan");
    //     data.get(2).setCreateBy(1);
    //     data.get(2).setDeleted(false);
    //     data.get(2).setCreatedDate(LocalDateTime.now());
    // }
    @GetMapping("")
    public ModelAndView index() {
        System.out.println("test");
        ModelAndView view = new ModelAndView("category/index");
        ResponseEntity<CategoryView[]> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl, CategoryView[].class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                CategoryView[] data = apiResponse.getBody();
                view.addObject("category", data);
            }else{
                throw new Exception(apiResponse.getStatusCode().toString()+ ": "+ apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }
    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("category/detail");
        ResponseEntity<CategoryView> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl +"/id/" + id, CategoryView.class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                view.addObject("title", "Detail Category");
                view.addObject("category", apiResponse.getBody());
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody() + "  HALOOOOOOOOOOOOOOOOOOOOOO INI ERRORRRRR");
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }
    
    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/category/add");
        view.addObject("title", "Add New Category");
        view.addObject("name", "budi");
        return view;
    }
    
    @PostMapping("/save")
    public String save(@ModelAttribute CategoryView category){
        String result = "sukses";
        if(category.getCategoryName() == ""){
            return "gagal";
        }
        return result;
    }
    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable int id){
        ModelAndView view = new ModelAndView("/category/edit");
        ResponseEntity<CategoryView> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl +"/id/" + id, CategoryView.class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                view.addObject("category", apiResponse.getBody());
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody() + "  HALOOOOOOOOOOOOOOOOOOOOOO INI ERRORRRRR");
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        return view;
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute CategoryView category) {
        System.out.println("halo ini gimana");
        ResponseEntity<CategoryView> apiResponse = null;
        try {
            restTemplate.put(apiUrl, category);
            apiResponse = restTemplate.getForEntity(apiUrl +"/id/" + category.getId(), CategoryView.class);
            if(category.getCategoryName() == ""){

            }
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                return new ResponseEntity<CategoryView>(apiResponse.getBody(), HttpStatus.OK);
            }else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": "+ apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // @GetMapping("/{id}")
    // ModelAndView delete(@PathVariable long id){
    //     CategoryView[] data;
    //     ModelAndView view = new ModelAndView("/category/edit");
    //     // data.remove((int)id-1);
    //     // category.setId(id);
    //     // category.setName("Makanan");
    //     // category.setDesc("Kategory Makanan");
    //     // category.setDeleted(false);
    //     view.addObject("title", "Edit");
    //     // view.addObject("category", category);
    //     return view;
    // }
    
}
