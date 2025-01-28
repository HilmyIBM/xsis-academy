package com.xsis.b345.frontend.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.b345.frontend.models.categoryView;
import com.xsis.b345.frontend.models.customerView;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class customer {
    private RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "http://localhost:8080/api/customer";

    @GetMapping("/customer")
    public ModelAndView user(HttpSession session) {
        if (session.getAttribute("role")!=null && session.getAttribute("role").equals(1) ) {
            ModelAndView view = new ModelAndView("/customer/index");
            ResponseEntity<customerView[]> apiResponse = null;
            try {
                apiResponse = restTemplate.getForEntity(apiUrl, customerView[].class);
                if (apiResponse.getStatusCode() == HttpStatus.OK) {
                    view.addObject("customer", apiResponse.getBody());
                } else {
                    throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
                }
            } catch (Exception e) {
                view.addObject("errorMSG", e.getMessage());
            }
            return view;
        }else{
            return new ModelAndView("redirect:/");
        }
    }

    @GetMapping("/customer/edit/{id}")
    public ModelAndView editCustomer(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/customer/edit");
        ResponseEntity<customerView> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/"+id, customerView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("customer", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        view.addObject("title", "Edit customer");
        return view;
    }

    @GetMapping("/customer/add")
    public ModelAndView addCustomer() {
        ModelAndView view = new ModelAndView("/customer/add");
        view.addObject("title", "Add new customer");
        return view;
    }   

    @PostMapping("/customer/save")
    public ResponseEntity<?> save(@ModelAttribute customerView customer) {
        ResponseEntity<customerView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl, customer,customerView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<customerView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("customer/detail/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("customer/detail");
        ResponseEntity<customerView> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + id, customerView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("customer", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        view.addObject("title", "Detail Customer");
        return view;
    }

    @PostMapping("/customer/update")
    public ResponseEntity<?> editData(@ModelAttribute customerView customer){
        ResponseEntity<customerView> apiResponse = null;
        try {
            restTemplate.put(apiUrl, customer);
            apiResponse = restTemplate.getForEntity(apiUrl + "/id/" + customer.getId(), customerView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<customerView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/delete/{id}")
    public ModelAndView deleteView(@PathVariable int id) {
        ModelAndView view = new ModelAndView("customer/delete");
        view.addObject("id", id);
        view.addObject("title", "Hapus Customer");
        return view;
    }

    @PostMapping("/customer/delete/{id}/{userId}")
    public ResponseEntity<?> deleteData(@PathVariable int id, @PathVariable int userId) {
        ResponseEntity<customerView> apiResponse = null;
        try {
            apiResponse = restTemplate.exchange(apiUrl + "/delete/" + id + "/" + userId, HttpMethod.DELETE,
                    null,
                    customerView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<customerView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
