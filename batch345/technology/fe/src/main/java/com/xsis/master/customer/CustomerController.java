package com.xsis.master.customer;

import com.xsis.master.category.CategoryModel;
import com.xsis.master.util.ErrorModel;
import com.xsis.master.util.ProcessAPI;
import com.xsis.master.util.RequestType;
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

@Controller
@RequestMapping("/user")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private final RestTemplate restTemplate;
    private final ProcessAPI<CustomerDTO, CustomerModel> request;
    private final String API_URL;

    public CustomerController(ProcessAPI<CustomerDTO, CustomerModel> request, Environment env) {
        this.restTemplate = new RestTemplate();
        this.request = request;
        this.API_URL = env.getProperty("api.url") + "/customer";
    }

    @GetMapping("/add")
    public ModelAndView add() {
        var view = new ModelAndView("master/user/add");

        view.addObject("title", "Create Customer");

        return view;
    }

    @GetMapping("/{type}/{id}")
    public ModelAndView detail(@PathVariable("type") RequestType type, @PathVariable("id") int id) {
        ModelAndView view = getViewModel(type);

        ResponseEntity<CustomerModel> apiResponse;

        var header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(header);

        try {
            apiResponse = restTemplate.exchange(API_URL + "/" + id,
                    HttpMethod.GET, httpEntity, CustomerModel.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK)
                view.addObject("customer", apiResponse.getBody());

        } catch (HttpClientErrorException e) {
            ErrorModel er = e.getResponseBodyAs(ErrorModel.class);

            log.error(Objects.requireNonNull(er).toString());
            view.addObject("error", er);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return view;
    }


    // ==================== REST ====================

    @GetMapping()
    public ModelAndView customers() {
        var view = new ModelAndView("master/user/index");
        ResponseEntity<List<CustomerModel>> apiResponse;

        try {
            apiResponse = restTemplate.exchange(API_URL, HttpMethod.GET,
                    new HttpEntity<>(new ArrayList<CustomerModel>()),
                    new ParameterizedTypeReference<>() {});

            if (apiResponse.getStatusCode().is2xxSuccessful()) {
                view.addObject("customers", apiResponse.getBody());
                return view;
            }

            view.addObject("error", apiResponse.getBody());

        } catch (Exception e) {
            view.addObject("error", e.getMessage());
        }

        return view;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@ModelAttribute CustomerDTO customerDTO) {
        log.info(customerDTO.toString());

        return request.send(customerDTO, CustomerModel.class,
                HttpMethod.POST, HttpStatus.CREATED,
                API_URL);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@ModelAttribute CustomerDTO customerDTO) {
        return request.send(customerDTO, CustomerModel.class,
                HttpMethod.PUT, HttpStatus.OK,
                API_URL);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCustomer(@ModelAttribute CustomerDTO customerDTO) {
        return request.send(customerDTO, CustomerModel.class,
                HttpMethod.DELETE, HttpStatus.OK,
                API_URL);
    }

    private ModelAndView getViewModel(RequestType type) {
        Map<String, Object> attributes = new HashMap<>();

        switch (type) {
            case DETAIL -> {
                attributes.put("title", "Customer Details");
                return new ModelAndView("master/user/detail", attributes); }
            case EDIT -> {
                attributes.put("title", "Edit Customer");
                return new ModelAndView("master/user/edit", attributes); }
            case DELETE -> {
                attributes.put("title", "Delete Customer");
                return new ModelAndView("master/user/delete", attributes); }
            default -> throw new UnsupportedOperationException();
        }
    }

}
