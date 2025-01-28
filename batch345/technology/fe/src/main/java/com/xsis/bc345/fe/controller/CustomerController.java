package com.xsis.bc345.fe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CategoryView;
import com.xsis.bc345.fe.models.CustomerView;
import com.xsis.bc345.fe.models.CustomerView;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    //HTTP Client
    private RestTemplate restTemplate = new RestTemplate();
    
    //API URL
    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("")
    public ModelAndView index(String filter, HttpSession sess) throws Exception {
        ModelAndView view = new ModelAndView("/customer/index");
        ResponseEntity<CustomerView[]> apiResponse = null;

        try {
            if (filter == null || filter.isBlank()) {
                apiResponse = restTemplate.getForEntity(apiUrl + "/customer", CustomerView[].class);
            }
            else {
                apiResponse = restTemplate.getForEntity(apiUrl + "/customer/filter/" + filter, CustomerView[].class);
            }

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("customers", apiResponse.getBody());
            }
            else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        }
        catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/customer/add");

        ResponseEntity<CustomerView> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/customer/add/", CustomerView.class);
            
            if (apiResponse.getStatusCode() == HttpStatus.OK){
                CustomerView data = apiResponse.getBody();
                view.addObject("customer", data);
                // view.addObject("data", apiResponse.getBody());  ---ini juga bisa
            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+": "+apiResponse.getBody());
            }
            
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("Category Detail");

   
        return view;
    }


    @PostMapping("/create")
    ResponseEntity<?> create(@ModelAttribute CustomerView customer) {
        ResponseEntity<CustomerView> apiResponse = null;

        try {
            apiResponse = restTemplate.postForEntity(apiUrl +"/customer", customer, CustomerView.class);

            if(apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<CustomerView>(apiResponse.getBody(), HttpStatus.CREATED);
            }
            else{
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        }
        catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
    ModelAndView view = new ModelAndView("/customer/edit");

    ResponseEntity<CustomerView> apiResponse = null;

    try {
        apiResponse = restTemplate.getForEntity(apiUrl + "/customer/id/" + id, CustomerView.class);

        if (apiResponse.getStatusCode() == HttpStatus.OK) {
            CustomerView customer = apiResponse.getBody();
            view.addObject("title", "Edit customer");
            view.addObject("customer", customer);
        } else {
            throw new Exception("Error: " + apiResponse.getStatusCode());
        }
    } catch (Exception e) {
        view.addObject("errorMsg", e.getMessage());
    }

    return view;
}

    

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/customer/detail");

        ResponseEntity<CustomerView> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl+"/customer/id/"+id, CustomerView.class);
            
            if (apiResponse.getStatusCode() == HttpStatus.OK){
                CustomerView data = apiResponse.getBody();
                view.addObject("customer", data);
                // view.addObject("data", apiResponse.getBody());  ---ini juga bisa
            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+": "+apiResponse.getBody());
            }
            
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("customer Detail");

        return view;
    }

    @PostMapping("/update")
    public ResponseEntity update(@ModelAttribute CustomerView customer) throws Exception {
        //TODO: process POST request
        ResponseEntity<CustomerView> apiResponse = null;
        
        try {
            restTemplate.put(apiUrl+"/customer", customer);
            apiResponse = restTemplate.getForEntity(apiUrl+"/customer/id/"+customer.getId(), CustomerView.class);

            if(apiResponse.getStatusCode()==HttpStatus.OK){
                return new ResponseEntity<CustomerView>(apiResponse.getBody(), HttpStatus.OK);
            } else {

                throw new Exception(apiResponse.getStatusCode().toString() +": " +apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/customer/delete");

        view.addObject("title", "Delete Customer");
        view.addObject("id", id);

        return view;
    }

    
}