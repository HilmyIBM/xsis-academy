package com.xsis.master.product;

import com.xsis.master.category.CategoryModel;
import com.xsis.master.util.ErrorModel;
import com.xsis.master.util.ProcessAPI;
import com.xsis.master.util.RequestType;
import com.xsis.master.variant.VariantDTO;
import com.xsis.master.variant.VariantModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final RestTemplate restTemplate;
    private final ProcessAPI<ProductDTO, ProductModel> request;
    private final String API_URL;

    public ProductController(ProcessAPI<ProductDTO, ProductModel> request, Environment env) {
        this.restTemplate = new RestTemplate();
        this.request = request;
        this.API_URL = env.getProperty("api.url") + "/product";
    }

    @GetMapping("/add")
    ModelAndView add() {
        ModelAndView view = new ModelAndView("master/product/add");
        view.addObject("title", "Add New Product");

        ResponseEntity<List<VariantModel>> apiResponse;

        try {
            apiResponse = restTemplate.exchange("http://localhost:8080/api/variant", HttpMethod.GET,
                    new HttpEntity<>(new ArrayList<>()),
                    new ParameterizedTypeReference<>(){});

            if (apiResponse.getStatusCode() == HttpStatus.OK || apiResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
                view.addObject("variants", apiResponse.getBody());

                return view;
            }

            view.addObject("error", apiResponse.getBody());
        } catch (Exception e) {
            view.addObject("error", e.getMessage());
        }

        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        var view = new ModelAndView("master/product/edit");

        view.addObject("title", "Edit Product");

        ResponseEntity<ProductModel> apiProductResp;
        ResponseEntity<List<VariantModel>> apiVariantsResp;
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            var header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);

            var httpEntity = new HttpEntity<>(header);

            Future<ResponseEntity<ProductModel>> fProduct = executor.submit(() ->
                    restTemplate.exchange(API_URL + "/" + id,
                            HttpMethod.GET, httpEntity, ProductModel.class));

            Future<ResponseEntity<List<VariantModel>>> fVariant = executor.submit(() ->
                    restTemplate.exchange("http://localhost:8080/api/variant", HttpMethod.GET,
                            new HttpEntity<>(new ArrayList<>(), header),
                            new ParameterizedTypeReference<>(){}));

            apiProductResp = fProduct.get();
            apiVariantsResp = fVariant.get();

            if (apiProductResp.getStatusCode() == HttpStatus.OK && apiVariantsResp.getStatusCode() == HttpStatus.OK) {
                view.addObject("product", apiProductResp.getBody());
                view.addObject("variants", apiVariantsResp.getBody());
            }

        } catch (HttpClientErrorException e) {
            ErrorModel er = e.getResponseBodyAs(ErrorModel.class);
            log.error(Objects.requireNonNull(er).toString());
            view.addObject("error", er);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        } finally {
            executor.shutdown();
        }

        return view;
    }

    @GetMapping("/{type}/{id}")
    public ModelAndView detail(@PathVariable RequestType type, @PathVariable int id) {
        var view = getViewModel(type);

        ResponseEntity<ProductModel> apiResponse;

        try {
            var header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);

            var httpEntity = new HttpEntity<>(header);

            apiResponse = restTemplate.exchange(API_URL + "/" + id,
                    HttpMethod.GET, httpEntity, ProductModel.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK)
                view.addObject("product", apiResponse.getBody());

        } catch (HttpClientErrorException e) {
            ErrorModel er = e.getResponseBodyAs(ErrorModel.class);
            log.error(Objects.requireNonNull(er).toString());
            view.addObject("error", er);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return view;
    }

    // ======================= REST ======================= //

    @GetMapping
    public ModelAndView product() {
        var view = new ModelAndView("master/product/index");
        ResponseEntity<List<ProductModel>> apiResponse;

        try {
            apiResponse = restTemplate.exchange(API_URL, HttpMethod.GET,
                    new HttpEntity<>(new ArrayList<>()),
                    new ParameterizedTypeReference<>(){});

            if (apiResponse.getStatusCode() == HttpStatus.OK || apiResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
                view.addObject("products", apiResponse.getBody());

                return view;
            }

            view.addObject("error", apiResponse.getBody());

        } catch (Exception e) {
            view.addObject("error", e.getMessage());
        }

        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@ModelAttribute ProductDTO model) {
        return request.send(model, ProductModel.class,
                HttpMethod.POST, HttpStatus.CREATED,
                API_URL);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@ModelAttribute ProductDTO model) {
        return request.send(model, ProductModel.class,
                HttpMethod.PUT, HttpStatus.OK,
                API_URL);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@ModelAttribute ProductDTO model) {
        return request.send(model, ProductModel.class,
                HttpMethod.DELETE, HttpStatus.OK,
                API_URL);
    }


    private ModelAndView getViewModel(RequestType type) {
        Map<String, Object> attributes = new HashMap<>();

        switch (type) {
            case DETAIL -> {
                attributes.put("title", "Product Details");
                return new ModelAndView("master/product/details", attributes); }
            case DELETE -> {
                attributes.put("title", "Delete Product");
                return new ModelAndView("master/product/delete", attributes); }
            default -> throw new UnsupportedOperationException();
        }
    }

}
