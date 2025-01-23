package com.xsis.frontend.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

import com.xsis.frontend.model.ProductView;
import com.xsis.frontend.model.VariantView;

@Controller
@RequestMapping("/product")
public class ProductController {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${application.api.url}")
    private String apiUrl;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("")
    public ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("product/index");
        ResponseEntity<ProductView[]> response = null;

        try {
            if (filter == null || filter.isBlank()) {
                response = restTemplate.getForEntity(apiUrl + "/products", ProductView[].class);
            } else {
                response = restTemplate.getForEntity(apiUrl + "/products/filter/" + filter, ProductView[].class);
            }

            if (response.getStatusCode() == HttpStatus.OK) {
                ProductView[] data = response.getBody();
                view.addObject("product", data);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("filter", filter);
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("product/detail");
        ResponseEntity<ProductView> response = null;

        try {
            response = restTemplate.getForEntity(apiUrl + "/products/id/" + id, ProductView.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                ProductView data = response.getBody();
                view.addObject("product", data);
            } else {

            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Product Detail");

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("product/add");
        ResponseEntity<VariantView[]> respose = null;

        try {
            respose = restTemplate.getForEntity(apiUrl + "/variants", VariantView[].class);

            if (respose.getStatusCode() == HttpStatus.OK) {
                VariantView[] data = respose.getBody();
                view.addObject("variant", data);
            } else {
                throw new Exception(respose.getStatusCode().toString() + ": " + respose.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Add Product");

        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute ProductView product,
            @RequestParam("setImage") MultipartFile imgFile) throws IOException {

        ResponseEntity<ProductView> apiResponse = null;
        try {
            if (imgFile != null && !imgFile.isEmpty()) {
                String fileName = imgFile.getOriginalFilename();
                Path imgPath = Paths.get(uploadDir + fileName);
                Files.write(imgPath, imgFile.getBytes());
                product.setImage(fileName);

            }
            apiResponse = restTemplate.postForEntity(apiUrl + "/products", product, ProductView.class);

            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("product/edit");
        ResponseEntity<ProductView> resProduct = null;
        ResponseEntity<VariantView[]> resVariants = null;
        ProductView product = new ProductView();

        try {
            resProduct = restTemplate.getForEntity(apiUrl + "/products/id/" + id, ProductView.class);
            resVariants = restTemplate.getForEntity(apiUrl + "/variants", VariantView[].class);

            if (resProduct.getStatusCode() == HttpStatus.OK && resVariants.getStatusCode() == HttpStatus.OK) {
                product = resProduct.getBody();
                VariantView[] variants = resVariants.getBody();
                view.addObject("product", product);
                view.addObject("variants", variants);
            } else {
                throw new Exception(resProduct.getStatusCode().toString() + ": " + resProduct.getBody() + "\n"
                        + resVariants.getStatusCode().toString() + ": " + resVariants.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Edit Product");
        return view;
    }

    @SuppressWarnings("null")
    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute ProductView product,
            @RequestParam(value = "setImage", required = false) MultipartFile imgFile,
            @RequestParam(value = "existingImage", required = false) String currImg) throws IOException {
        ResponseEntity<ProductView> response = null;
        try {
            String fileName;
            if (imgFile != null && !imgFile.isEmpty()) {
                fileName = imgFile.getOriginalFilename();
                Path imgPath = Paths.get(uploadDir + fileName);

                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }

                Files.write(imgPath, imgFile.getBytes());
                product.setImage(fileName);
            } else {
                if (currImg.length() > 0) {
                    product.setImage(currImg);
                }
            }

            restTemplate.put(apiUrl + "/products", product);
            response = restTemplate.getForEntity(apiUrl + "/products/id/" + product.getId(), ProductView.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<ProductView>(response.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView view = new ModelAndView("product/delete");

        view.addObject("id", id);
        view.addObject("title", "Delete Product");
        return view;
    }

    @SuppressWarnings("null")
    @PostMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id, @PathVariable int userId) {
        ResponseEntity<ProductView> response = null;
        ProductView product = new ProductView();

        product.setId(id);
        product.setUpdateBy(userId);
        try {
            restTemplate.delete(apiUrl + "/products/delete/" + id + "/" + userId);
            response = restTemplate.exchange(apiUrl + "/products/delete/" + id + "/" + userId, HttpMethod.DELETE,
                    new HttpEntity<ProductView>(product), ProductView.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<ProductView>(response.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
