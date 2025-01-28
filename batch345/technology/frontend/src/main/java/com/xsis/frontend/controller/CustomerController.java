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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.frontend.model.CustomerView;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("")
    public ModelAndView index(String filter) {
        ModelAndView view = new ModelAndView("customer/index");
        ResponseEntity<CustomerView[]> response = null;

        try {
            if (filter == null || filter.isBlank()) {
                response = restTemplate.getForEntity(apiUrl + "/customers", CustomerView[].class);
            } else {
                response = restTemplate.getForEntity(apiUrl + "/customers/filter/" + filter, CustomerView[].class);
            }

            if (response.getStatusCode() == HttpStatus.OK) {
                CustomerView[] data = response.getBody();
                view.addObject("customer", data);
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
        ModelAndView view = new ModelAndView("customer/detail");
        ResponseEntity<CustomerView> response = null;
        try {
            response = restTemplate.getForEntity(apiUrl + "/customers/id/" + id, CustomerView.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                CustomerView data = response.getBody();
                view.addObject("customer", data);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "User Detail");

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("customer/add");
        view.addObject("title", "Add New User");
        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute CustomerView customer,
            @RequestParam(value = "confirmPassword", required = true) String confirmPassword) {

        ResponseEntity<CustomerView> response = null;
        try {
            if (!customer.getPassword().equals(confirmPassword)) {
                throw new Exception("Password doesn't match!");
            }
            response = restTemplate.postForEntity(apiUrl + "/customers", customer, CustomerView.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<CustomerView>(response.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody());
            }

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("customer/edit");
        ResponseEntity<CustomerView> response = null;

        try {
            response = restTemplate.getForEntity(apiUrl + "/customers/id/" + id, CustomerView.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                CustomerView data = response.getBody();
                view.addObject("customer", data);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("title", "Edit Customer");

        return view;
    }

    @SuppressWarnings("null")
    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute CustomerView customer) {
        ResponseEntity<CustomerView> response = null;

        try {
            restTemplate.put(apiUrl + "/customers", customer);
            response = restTemplate.getForEntity(apiUrl + "/customers/id/" + customer.getId(), CustomerView.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<CustomerView>(response.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView view = new ModelAndView("customer/delete");

        view.addObject("id", id);
        view.addObject("title", "Delete Category");
        return view;
    }

    @SuppressWarnings("null")
    @PostMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id, @PathVariable int userId) {
        ResponseEntity<CustomerView> response = null;
        CustomerView customer = new CustomerView();

        customer.setId(id);
        customer.setUpdateBy(userId);

        try {
            restTemplate.delete(apiUrl + "/customers/delete/" + id + "/" + userId);
            response = restTemplate.exchange(apiUrl + "/customers/delete/" + id + "/" + userId, HttpMethod.DELETE,
                    new HttpEntity<CustomerView>(customer), CustomerView.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<CustomerView>(response.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(response.getStatusCode().toString() + ": " + response.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
