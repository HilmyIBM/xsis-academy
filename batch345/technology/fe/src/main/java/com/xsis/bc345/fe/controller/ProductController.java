package com.xsis.bc345.fe.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;
import com.xsis.bc345.fe.models.PagingView;
import com.xsis.bc345.fe.models.ProductView;
import com.xsis.bc345.fe.models.VariantView;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("product")
public class ProductController {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${application.api.url}")
    private String apiUrl;

    @Value("${application.page.size}")
    private Integer pageSize;

    @GetMapping("")
    public ModelAndView categoryProduct(String search, Integer pageNumber, Integer currPageSize, HttpSession sess) {
        ModelAndView view = new ModelAndView("product/index");
        ResponseEntity<PagingView> apiResponse = null;

        currPageSize = (currPageSize != null) ? currPageSize : pageSize;
        pageNumber = (pageNumber != null) ? pageNumber : 0;
        try {
            if (search == null || search.isBlank()) {
                // apiResponse = restTemplate.getForEntity(apiUrl + "product",
                // ProductView[].class);
                apiResponse = restTemplate.getForEntity(
                        apiUrl + "product/pagination/" + pageNumber + "/" + currPageSize, PagingView.class);
            } else {
                // apiResponse = restTemplate.getForEntity(apiUrl + "product/filter/" + search,
                // ProductView[].class);
                apiResponse = restTemplate.getForEntity(
                        apiUrl + "product/paginationFilter/" + search + "/" + pageNumber + "/" + currPageSize,
                        PagingView.class);
            }

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("products", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("filter", search);
        view.addObject("currPageSize", currPageSize);
        return view;
    }

    @GetMapping("add")
    public ModelAndView addProduct() {
        ModelAndView view = new ModelAndView("product/add");
        view.addObject("title", "Add New Product");

        ResponseEntity<CategoryView[]> apiResponseCategory = null;
        try {
            apiResponseCategory = restTemplate.getForEntity(apiUrl + "category", CategoryView[].class);
            if (apiResponseCategory.getStatusCode() == HttpStatus.OK) {
                view.addObject("categories", apiResponseCategory.getBody());
            } else {
                throw new Exception("Error fetching variants list");
            }
        } catch (Exception e) {
            view.addObject("errMsg", e.getLocalizedMessage());
        }
        return view;
    }

    @PostMapping("save")
    public ResponseEntity<?> saveProduct(@ModelAttribute ProductView productView,
            @RequestParam("myImage") MultipartFile image) {
        ResponseEntity<ProductView> apiResponse = null;
        try {
            // Handle the file
            if (!image.isEmpty()) {
                // Get the name
                String imageName = image.getOriginalFilename();
                // get the path
                Path path = Paths.get("src/main/resources/static/lib/images/" + imageName);
                // get the directory
                Path directory = path.getParent();
                // Check if the directory is not exists
                if (!Files.exists(directory)) {
                    Files.createDirectories(directory);
                }
                // Save the file to the path
                Files.write(path, image.getBytes());
                productView.setImage(imageName);
            }

            apiResponse = restTemplate.postForEntity(apiUrl + "product", productView,
                    ProductView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.CREATED);
            } else {
                throw new Exception(apiResponse.getBody().toString() + ": " + HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("product/edit");
        ResponseEntity<ProductView> apiResponse = null;
        ResponseEntity<VariantView[]> apiResponseVariant = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "product/" + id, ProductView.class);
            apiResponseVariant = restTemplate.getForEntity(apiUrl + "variant", VariantView[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK && apiResponseVariant.getStatusCode() == HttpStatus.OK) {
                view.addObject("product", apiResponse.getBody());
                view.addObject("variant", apiResponseVariant.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            view.addObject("errMsg", e.getLocalizedMessage());
        }
        view.addObject("title", "Edit Product");
        return view;
    }

    // Get modal delete view
    @GetMapping("delete/{id}")
    public ModelAndView getDeleteProduct(@PathVariable int id) {
        ModelAndView view = new ModelAndView("product/delete");
        view.addObject("id", id);
        view.addObject("title", "Delete Product");
        return view;
    }

    // Delete Action
    @PostMapping("delete/{id}/{userId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id, @PathVariable int userId) {
        ResponseEntity<ProductView> apiResponse = null;
        ProductView product = new ProductView();
        product.setUpdateBy(userId);
        product.setId(id);
        try {
            apiResponse = restTemplate.exchange(apiUrl + "product/delete/" + id + "/" + userId, HttpMethod.DELETE,
                    new HttpEntity<ProductView>(product), ProductView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.OK);
            }
            throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> updateProduct(@ModelAttribute ProductView product,
            @RequestParam("myImage") MultipartFile image) {
        ResponseEntity<ProductView> apiResponse = null;
        try {
            // Handle the file
            if (!image.isEmpty()) {
                // Get the name
                String imageName = image.getOriginalFilename();
                // get the path
                Path path = Paths.get("src/main/resources/static/lib/images/" + imageName);
                // get the directory
                Path directory = path.getParent();
                // Check if the directory is not exists
                if (!Files.exists(directory)) {
                    Files.createDirectories(directory);
                }
                // Save the file to the path
                Files.write(path, image.getBytes());
                product.setImage(imageName);
            }

            apiResponse = restTemplate.exchange(apiUrl + "product", HttpMethod.PUT,
                    new HttpEntity<ProductView>(product),
                    ProductView.class);
            return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("detail/{id}")
    public ModelAndView getDetailProduct(@PathVariable int id) {
        ModelAndView view = new ModelAndView("product/detail");
        ResponseEntity<ProductView> apiResponse = null;
        try {
            apiResponse = restTemplate.exchange(apiUrl + "product/" + id, HttpMethod.GET, null, ProductView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("product", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errMsg", e.getLocalizedMessage());
        }
        view.addObject("title", "Detail Product");
        return view;
    }

}
