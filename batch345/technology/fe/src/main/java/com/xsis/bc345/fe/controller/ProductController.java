package com.xsis.bc345.fe.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
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

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("product")
public class ProductController {

    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API Url
    private final String apiUrl = "http://localhost:8080/api/product";

    private String uploadDir = "src/main/resources/static/lib/images/";

    @Value("${application.page.size}")
    private Integer pageSize;

    @GetMapping("")
    public ModelAndView index(String filter, Integer currPageSize, Integer pageNumber, HttpSession sess) {
        ModelAndView view = new ModelAndView("/product/index");

        ResponseEntity<PagingView> apiResponse = null;

        currPageSize = (currPageSize != null) ? currPageSize : pageSize;
        pageNumber = (pageNumber!= null) ? pageNumber : 0;

        try {
            if (filter == null || filter.isBlank()) {

                apiResponse = restTemplate.getForEntity(apiUrl + "/paginated/" + pageNumber + "/" + currPageSize, PagingView.class);
            } else {
                apiResponse = restTemplate.getForEntity(apiUrl + "/paginated/filter/" + filter + "/" + pageNumber + "/" + currPageSize, PagingView.class);

            }
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                PagingView data = apiResponse.getBody();
                view.addObject("product", data);
                // view.addObject("data", apiResponse.getBody()); ---ini juga bisa
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("filter", filter);
        // view.addObject("imgFolder", filter);
        view.addObject("currPageSize", currPageSize);


        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/product/edit");

        ResponseEntity<ProductView> apiResponse = null;
        ResponseEntity<ProductView[]> variantApiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, ProductView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                ProductView product = apiResponse.getBody();
                view.addObject("title", "Edit Product");
                view.addObject("product", product);
            } else {
                throw new Exception("Error: " + apiResponse.getStatusCode());
            }

            // Fetch Variants for dropdown
            variantApiResponse = restTemplate.getForEntity("http://localhost:8080/api/variant", ProductView[].class);
            if (variantApiResponse.getStatusCode() == HttpStatus.OK) {
                ProductView[] variant = variantApiResponse.getBody();
                view.addObject("variant", variant);
            } else {
                throw new Exception("Error: " + variantApiResponse.getStatusCode());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute ProductView product) throws Exception {
        // TODO: process POST request
        ResponseEntity<ProductView> apiResponse = null;

        try {
            restTemplate.put(apiUrl, product);
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + product.getId(), ProductView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.OK);
            } else {

                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/product/detail");

        ResponseEntity<ProductView> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, ProductView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                ProductView data = apiResponse.getBody();
                view.addObject("product", data);
                // view.addObject("data", apiResponse.getBody()); ---ini juga bisa
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("Product Detail");

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/product/add");

        // Category List
        ResponseEntity<VariantView[]> apiVariantResponse = null;

        try {
            // Get all Categories
            apiVariantResponse = restTemplate.getForEntity("http://localhost:8080/api/variant", VariantView[].class);
            view.addObject(
                    "variants",
                    (apiVariantResponse.getStatusCode() == HttpStatus.OK) ? apiVariantResponse.getBody()
                            : new CategoryView());
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("title", "Add New Product");

        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute ProductView product,
            @RequestParam("setImage") MultipartFile imgFile) throws IOException {

        ResponseEntity<ProductView> apiResponse = null;

        try {
            // Check if the uploaded file is not null and not empty
            if (imgFile != null && !imgFile.isEmpty()) {
                // Get the original file name
                String originalFileName = imgFile.getOriginalFilename();

                // Get the file extension (e.g., ".jpg", ".png")
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

                // Generate a unique file name using the current timestamp
                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String uniqueFileName = originalFileName.replace(fileExtension, "") + "_" + timeStamp + fileExtension;

                // Alternatively, you could use a UUID
                // String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

                // Define the full file path
                Path imgPath = Paths.get(uploadDir + uniqueFileName);

                // Write the file to the specified location
                Files.write(imgPath, imgFile.getBytes());

                // Set the unique file name in the product object
                product.setImage(uniqueFileName);
            }

            // Send the product object to the API
            apiResponse = restTemplate.postForEntity(apiUrl, product, ProductView.class);

            // Check the API response status
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            // Handle any exceptions and return an error response
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   

}
