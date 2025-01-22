package com.xsis.master.variant;

import com.xsis.master.category.CategoryModel;
import com.xsis.master.util.ErrorModel;
import com.xsis.master.util.ProcessAPI;
import com.xsis.master.util.RequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/variant")
public class VariantController {

    private static final Logger log = LoggerFactory.getLogger(VariantController.class);
    private final RestTemplate restTemplate;
    private final ProcessAPI<VariantDTO, VariantModel> request;
    private final String API_URL;

    @Autowired
    public VariantController(ProcessAPI<VariantDTO, VariantModel> request, Environment env) {
        this.restTemplate = new RestTemplate();
        this.request = request;
        this.API_URL = env.getProperty("api.url") + "/variant";
    }

    @GetMapping("/add")
    ModelAndView add() {
        ModelAndView view = new ModelAndView("master/variant/add");
        view.addObject("title", "Add New Variant");

        ResponseEntity<List<CategoryModel>> apiResponse;

        try {
            apiResponse = restTemplate.exchange("http://localhost:8080/api/category", HttpMethod.GET,
                    new HttpEntity<>(new ArrayList<>()),
                    new ParameterizedTypeReference<>(){});

            if (apiResponse.getStatusCode() == HttpStatus.OK || apiResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
                view.addObject("category", apiResponse.getBody());

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
        var view = new ModelAndView("master/variant/edit");

        view.addObject("title", "Edit Variant");

        ResponseEntity<VariantModel> apiVariantResp;
        ResponseEntity<List<CategoryModel>> apiCategoryResp;
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            var header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);

            var httpEntity = new HttpEntity<>(header);

            Future<ResponseEntity<VariantModel>> fVariant = executor.submit(() ->
                    restTemplate.exchange(API_URL + "/" + id,
                    HttpMethod.GET, httpEntity, VariantModel.class));

            Future<ResponseEntity<List<CategoryModel>>> fCategory = executor.submit(() ->
                    restTemplate.exchange("http://localhost:8080/api/category", HttpMethod.GET,
                            new HttpEntity<>(new ArrayList<>(), header),
                            new ParameterizedTypeReference<>(){}));

            apiVariantResp = fVariant.get();
            apiCategoryResp = fCategory.get();

            if (apiVariantResp.getStatusCode() == HttpStatus.OK && apiCategoryResp.getStatusCode() == HttpStatus.OK) {
                view.addObject("variant", apiVariantResp.getBody());
                view.addObject("category", apiCategoryResp.getBody());
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

        ResponseEntity<VariantModel> apiResponse;

        try {
            var header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);

            var httpEntity = new HttpEntity<>(header);

            apiResponse = restTemplate.exchange(API_URL + "/" + id,
                    HttpMethod.GET, httpEntity, VariantModel.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK)
                view.addObject("variant", apiResponse.getBody());

        } catch (HttpClientErrorException e) {
            ErrorModel er = e.getResponseBodyAs(ErrorModel.class);
            log.error(Objects.requireNonNull(er).toString());
            view.addObject("error", er);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return view;
    }


    @GetMapping
    public ModelAndView variants() {
        var view = new ModelAndView("master/variant/index");
        ResponseEntity<List<VariantModel>> apiResponse;

        try {
            apiResponse = restTemplate.exchange(API_URL, HttpMethod.GET,
                    new HttpEntity<>(new ArrayList<>()),
                    new ParameterizedTypeReference<>(){});

            if (apiResponse.getStatusCode() == HttpStatus.OK || apiResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
                view.addObject("variant", apiResponse.getBody());

                return view;
            }

            view.addObject("error", apiResponse.getBody());

        } catch (Exception e) {
            view.addObject("error", e.getMessage());
        }

        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createVariant(@ModelAttribute VariantDTO model) {
        return request.send(model, VariantModel.class,
                HttpMethod.POST, HttpStatus.CREATED,
                API_URL);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateVariant(@ModelAttribute VariantDTO model) {
        return request.send(model, VariantModel.class,
                HttpMethod.PUT, HttpStatus.OK,
                API_URL);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteVariant(@ModelAttribute VariantDTO model) {
        return request.send(model, VariantModel.class,
                HttpMethod.DELETE, HttpStatus.OK,
                API_URL);
    }

    private ModelAndView getViewModel(RequestType type) {
        Map<String, Object> attributes = new HashMap<>();

        switch (type) {
            case DETAIL -> {
                attributes.put("title", "Variant Details");
                return new ModelAndView("master/variant/details", attributes); }
            case DELETE -> {
                attributes.put("title", "Delete Variant");
                return new ModelAndView("master/variant/delete", attributes); }
            default -> throw new UnsupportedOperationException();
        }
    }

}
