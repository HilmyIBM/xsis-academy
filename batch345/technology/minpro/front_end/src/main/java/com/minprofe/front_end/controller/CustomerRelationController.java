package com.minprofe.front_end.controller;

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

import com.minprofe.front_end.models.CustomerRelationView;

@Controller
@RequestMapping("/customer-relation")
public class CustomerRelationController {
    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API
    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("")
    public ModelAndView index(String filter){
        ModelAndView view = new ModelAndView("/customerRelation/index");
        ResponseEntity<CustomerRelationView[]> apiResponse = null; 

        try {
            if (filter == null || filter.isBlank()) {
                apiResponse = restTemplate.getForEntity(apiUrl + "customer-relation", CustomerRelationView[].class);
            }else {
                apiResponse = restTemplate.getForEntity(apiUrl + "customer-relation/filter/" + filter, CustomerRelationView[].class);
            }

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                CustomerRelationView[] data = apiResponse.getBody();
                view.addObject("relations", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        view.addObject("filter", filter);

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("/customerRelation/add");
        view.addObject("title", "Create Name Relation");
        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute CustomerRelationView cr){
        ResponseEntity<CustomerRelationView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "customer-relation", cr , CustomerRelationView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<CustomerRelationView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getBody().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/customerRelation/edit");
        ResponseEntity<CustomerRelationView> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "customer-relation/id/" + id , CustomerRelationView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                CustomerRelationView data = apiResponse.getBody();
                view.addObject("relations", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("title", "Edit Relation");
        
        return view;
    }

    @PostMapping("/update")
    ResponseEntity<?> update(@ModelAttribute CustomerRelationView cr){
        ResponseEntity<CustomerRelationView> apiResponse = null;

        try {
            restTemplate.put(apiUrl + "customer-relation", cr);
            apiResponse = restTemplate.getForEntity(apiUrl + "customer-relation/id/" + cr.getId() , CustomerRelationView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<CustomerRelationView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getBody().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}/{name}")
    public ModelAndView delete(@PathVariable int id, @PathVariable String name){
        ModelAndView view = new ModelAndView("/customerRelation/delete");
        view.addObject("id", id);
        view.addObject("name", name);
        view.addObject("title", "Delete Confirmation");
        return view;
    }

    @PostMapping("/delete/{id}/{userId}")
    ModelAndView delete(@PathVariable int id, @PathVariable int userId) {
        ModelAndView view = new ModelAndView("/customerRelation/delete");
        ResponseEntity<CustomerRelationView> apiResponse = null;
        CustomerRelationView customerRelation = new CustomerRelationView();

        customerRelation.setId(id);
        customerRelation.setDeletedBy(userId);

        try {
            apiResponse = restTemplate.exchange(
                apiUrl + "/customer-relation/delete/" + id + "/" + userId, 
                HttpMethod.DELETE, new HttpEntity<CustomerRelationView>(customerRelation), 
                CustomerRelationView.class
            );
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                CustomerRelationView data = apiResponse.getBody();
                view.addObject("relations", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("title", "Delete Confirmation");
        
        return view;
    }
}
