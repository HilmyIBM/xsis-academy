package com.xsis.bc345.frontend.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
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

import com.xsis.bc345.frontend.models.Pagination;
import com.xsis.bc345.frontend.models.ProductView;
import com.xsis.bc345.frontend.models.VariantView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/product")
public class ProductController {

  private RestTemplate restTemplate = new RestTemplate();

  @Value("${application.api.url}")
  private String apiUrl;

  @Value("${application.page.size}")
  private Integer pageSize;
  // @GetMapping("")
  // public ModelAndView index() {
  // ModelAndView view = new ModelAndView("master/product/index");
  // ResponseEntity<ProductView[]> apiResponse = null;

  // try {
  // apiResponse = restTemplate.getForEntity(apiUrl + "/product",
  // ProductView[].class);
  // if (apiResponse.getStatusCode() == HttpStatus.OK) {
  // view.addObject("product", apiResponse.getBody());
  // } else {
  // throw new Exception(apiResponse.getStatusCode().toString() + ": " +
  // apiResponse.getBody());
  // }
  // } catch (Exception e) {
  // // TODO: handle exception
  // view.addObject("errorMsg", e.getMessage());
  // }
  // return view;
  // }

  @GetMapping("/paginated/{page}/{size}")
  public ModelAndView index(@PathVariable int page, @PathVariable int size) {
    ModelAndView view = new ModelAndView("master/product/index");
    ResponseEntity<Pagination> apiResponse = null;

    try {
      apiResponse = restTemplate.getForEntity(apiUrl + "/product/native/paginated/" + page + "/" + size , Pagination.class);
      if (apiResponse.getStatusCode() == HttpStatus.OK) {
        view.addObject("product", apiResponse.getBody());
      } else {
        throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
      }
    } catch (Exception e) {
      // TODO: handle exception
      view.addObject("errorMsg", e.getMessage());
    }
    view.addObject("currPageSize", pageSize);
    return view;
  }

  // @GetMapping("")
  // public ModelAndView index() {
  //   ModelAndView view = new ModelAndView("master/product/index");
  //   ResponseEntity<ProductView[]> apiResponse = null;

  //   try {
  //     apiResponse = restTemplate.getForEntity(apiUrl + "/product/native", ProductView[].class);
  //     if (apiResponse.getStatusCode() == HttpStatus.OK) {
  //       view.addObject("product", apiResponse.getBody());
  //     } else {
  //       throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
  //     }
  //   } catch (Exception e) {
  //     // TODO: handle exception
  //     view.addObject("errorMsg", e.getMessage());
  //   }
  //   return view;
  // }

  @GetMapping("/{id}")
  public ModelAndView detail(@PathVariable int id) {
    ModelAndView view = new ModelAndView("master/product/detail");
    ResponseEntity<ProductView> apiResponse = null;

    try {
      apiResponse = restTemplate.getForEntity(apiUrl + "/product/native/id/" + id, ProductView.class);
      if (apiResponse.getStatusCode() == HttpStatus.OK) {
        view.addObject("product", apiResponse.getBody());
      } else {
        throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
      }
    } catch (Exception e) {
      // TODO: handle
      view.addObject("errorMsg", e.getMessage());
    }
    view.addObject("title", "Product Detail");
    return view;
  }

  @GetMapping("/add")
  public ModelAndView add() {
    ModelAndView view = new ModelAndView("master/product/add");
    view.addObject("title", "Add new Product");
    ResponseEntity<VariantView[]> apiVariantResponse = null;
    try {
      apiVariantResponse = restTemplate.getForEntity(apiUrl + "/variant", VariantView[].class);
      if (apiVariantResponse.getStatusCode() == HttpStatus.OK) {
        view.addObject("variant", apiVariantResponse.getBody());
      } else {
        throw new Exception(apiVariantResponse.getStatusCode().toString() + ": " + apiVariantResponse.getBody());
      }
    } catch (Exception e) {
      // TODO: handle exception
      view.addObject("errorMsg", e.getMessage());
    }
    return view;
  }

  @PostMapping("/create")
  public ResponseEntity<?> create(@ModelAttribute ProductView product, @RequestParam("itemImage") MultipartFile file) {
    ResponseEntity<ProductView> apiResponse = null;

    try {
      if (!file.isEmpty()) {
        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFileName != null && originalFileName.contains(".")) {
          fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        // Generate a unique file name
        String uniqueFileName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + fileExtension;

        // Define the file path for the new file
        Path newFilePath = Paths.get("src/main/resources/static/img/" + uniqueFileName);

        // Check if the product already has an image
        if (product.getImage() != null && !product.getImage().isEmpty()) {
          // Define the path of the old image
          Path oldFilePath = Paths.get("src/main/resources/static/img/" + product.getImage());

          // Delete the old image if it exists
          File oldFile = oldFilePath.toFile();
          if (oldFile.exists()) {
            oldFile.delete();
          }
        }

        // Write the new file to storage
        Files.write(newFilePath, file.getBytes());

        // Update the product's image property with the new file name
        product.setImage(uniqueFileName);
      }

      apiResponse = restTemplate.postForEntity(apiUrl + "/product", product, ProductView.class);

      if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
        return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.OK);
      } else {
        throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
      }
    } catch (Exception e) {
      // TODO: handle exception
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/edit/{id}")
  ModelAndView edit(@PathVariable long id) {
    ModelAndView view = new ModelAndView("master/product/edit");
    ResponseEntity<ProductView> apiResponse = null;
    ResponseEntity<VariantView[]> apiVariantResponse = null;

    try {
      apiResponse = restTemplate.getForEntity(apiUrl + "/product/id/" + id, ProductView.class);
      apiVariantResponse = restTemplate.getForEntity(apiUrl + "/variant", VariantView[].class);
      if (apiResponse.getStatusCode() == HttpStatus.OK) {
        view.addObject("product", apiResponse.getBody());
        view.addObject("variant", apiVariantResponse.getBody());
      } else {
        throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
      }
    } catch (Exception e) {
      // TODO: handle exception

    }
    return view;
  }

  @PostMapping("/update")
  public ResponseEntity<?> update(@ModelAttribute ProductView product, @RequestParam("itemImage") MultipartFile file) {
    ResponseEntity<ProductView> apiResponse = null;

    try {
      if (!file.isEmpty()) {
        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFileName != null && originalFileName.contains(".")) {
          fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        // Generate a unique file name
        String uniqueFileName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + fileExtension;

        // Define the path for the new file
        Path newFilePath = Paths.get("src/main/resources/static/img/" + uniqueFileName);

        // Check if the product already has an image
        if (product.getImage() != null && !product.getImage().isEmpty()) {
          // Define the path of the old image
          Path oldFilePath = Paths.get("src/main/resources/static/img/" + product.getImage());

          // Delete the old image if it exists
          File oldFile = oldFilePath.toFile();
          if (oldFile.exists()) {
            oldFile.delete();
          }
        }

        // Save the new file to storage
        Files.write(newFilePath, file.getBytes());

        // Update the product's image property with the new file name
        product.setImage(uniqueFileName);
      } else {
        // Preserve the existing image if no new file is uploaded
        ResponseEntity<ProductView> existingProductResponse = restTemplate
            .getForEntity(apiUrl + "/product/id/" + product.getId(), ProductView.class);
        if (existingProductResponse.getStatusCode() == HttpStatus.OK) {
          // Get the current image name from the existing product
          ProductView existingProduct = existingProductResponse.getBody();
          if (existingProduct != null) {
            product.setImage(existingProduct.getImage());
          }
        } else {
          throw new Exception("Failed to retrieve existing product for image preservation.");
        }
      }
      restTemplate.put(apiUrl + "/product", product);
      apiResponse = restTemplate.getForEntity(apiUrl + "/product/id/" + product.getId(), ProductView.class);
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

  @GetMapping("/delete/{id}")
  public ModelAndView delete(@PathVariable int id) {
      ModelAndView view = new ModelAndView("master/product/delete");
      view.addObject("id", id);
      view.addObject("title", "Delete Product");
      return view;
  }

  @PostMapping("/delete/{id}/{userId}")
  public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
    ResponseEntity<ProductView> apiResponse = null;

      try {
        apiResponse = restTemplate.exchange(apiUrl + "/product/delete/" + id + "/" + userId, HttpMethod.DELETE, null, ProductView.class);
        if (apiResponse.getStatusCode() == HttpStatus.OK){
          return new ResponseEntity<ProductView>(apiResponse.getBody(), HttpStatus.OK);
        }else{
          throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
        }
      } catch (Exception e) {
        // TODO: handle exception
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("")
  public ModelAndView getAll(String filter, Integer page, Integer size, HttpSession sess) {  
    ModelAndView view = null;
    if (sess.getAttribute("roleId") != null && sess.getAttribute("roleId").equals(1)){
      view = new ModelAndView("master/product/index");
      ResponseEntity<Pagination> apiResponse = null;

      size = (size != null) ? size : pageSize;
      page = (page != null) ? page : 0;
      try {
       if (filter == null || filter.isBlank()) {
        apiResponse = restTemplate.getForEntity(apiUrl + "/product/native/paginated/" + page + "/" + size, Pagination.class);
       } else {
        apiResponse = restTemplate.getForEntity(apiUrl + "/produt/native/paginatedfilter/" + filter + "/" + page + "/" + size, Pagination.class);
       }

       if (apiResponse.getStatusCode() == HttpStatus.OK) {
        view.addObject("product", apiResponse.getBody());
       } else {
        throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
       }
      } catch (Exception e) { 
        // TODO: handle
        view.addObject("errorMsg", e.getMessage());
      }
      view.addObject("filter", filter);
      view.addObject("currPageSize", size);
    } else {
      return new ModelAndView("redirect:/auth/login");
    }
    return view;
  }
  
}
