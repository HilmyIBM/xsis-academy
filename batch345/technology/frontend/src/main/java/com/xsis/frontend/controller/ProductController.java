package com.xsis.frontend.controller;


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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.frontend.model.ProductView;
import com.xsis.frontend.model.ResponseView;
import com.xsis.frontend.model.VariantView;

@Controller
@RequestMapping("/product")
public class ProductController {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${application.api.url}")
    private String apiUrl;

    @Value("${aplication.page.size}")
    private Integer pageSize;

    @GetMapping("")
    public ModelAndView index(String filter, Integer currPageSize, Integer pageNumber, String orderBy) {
        ModelAndView view = new ModelAndView("product/index");
        ResponseEntity<ResponseView> response = null;
        String sort,sd;

        currPageSize = (currPageSize != null) ? currPageSize : pageSize;
        pageNumber = (pageNumber != null) ? pageNumber : 0;
        orderBy = (orderBy != null) ? orderBy : "";
        //Process Sort

        switch (orderBy) {
            case "id_desc":
                sort = "id";
                sd = "desc";
            break;
            
            case "name" :
                sort = "name";
                sd = "desc";
                break;
        
            case "name_desc" :
                sort = "name";
                sd = "asc";
                break;
            
            case "price" :
                sort = "price";
                sd = "desc";
                break;
        
            case "price_desc" :
                sort = "price";
                sd = "asc";
                break;
                
            case "stock" :
                sort = "stock";
                sd = "desc";
                break;
        
            case "stock_desc" :
                sort = "stock";
                sd = "asc";
                break;
            
            case "variant" :
                sort = "variant";
                sd = "desc";
                break;
        
            case "variant_desc" :
                sort = "variant";
                sd = "asc";
                break;
            
            case "category" :
                sort = "category";
                sd = "desc";
                break;
        
            case "category_desc" :
                sort = "category";
                sd = "asc";
                break;
        
            default:
                sort = "id";
                sd = "asc";
                break;
        }

        try {
            if (filter == null || filter.isBlank()) {
                response = restTemplate.getForEntity(apiUrl + "/products/paginated/" +pageNumber+"/" +currPageSize + "?sort=" + sort + "&sd=" + sd, ResponseView.class);
            } else {
                response = restTemplate.getForEntity(apiUrl + "/products/paginationfilter/" + filter + "/" +pageNumber+ "/" + currPageSize, ResponseView.class);
            }

            if (response.getStatusCode() == HttpStatus.OK) {
                view.addObject("product", response.getBody());
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        view.addObject("orderId",(orderBy.equals("id")) ?"id_desc" : "id" );
        view.addObject("orderName",(orderBy.equals("id")) ?"name_desc" : "name" );
        view.addObject("orderPrice",(orderBy.equals("id")) ?"price_desc" : "price" );
        view.addObject("orderStock",(orderBy.equals("id")) ?"stock_desc" : "stock" );
        view.addObject("orderVariant",(orderBy.equals("id")) ?"variant_desc" : "variant" );
        view.addObject("orderCategory",(orderBy.equals("id")) ?"category_desc" : "category" );


        view.addObject("filter", filter);
        view.addObject("currPageSize", currPageSize);

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
    public ResponseEntity<?> create(@ModelAttribute ProductView product) {
        ResponseEntity<ProductView> apiResponse = null;
        try {
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

    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute ProductView product) {
        ResponseEntity<ProductView> response = null;
        try {
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
