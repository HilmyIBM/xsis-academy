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

import com.minprofe.front_end.models.BloodGroupView;

@Controller
@RequestMapping("/blood")
public class BloodGroupController {

    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API
    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("/blood/index");
        ResponseEntity<BloodGroupView[]> apiResponse = null; 

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "blood", BloodGroupView[].class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                BloodGroupView[] data = apiResponse.getBody();
                view.addObject("bloods", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("/blood/add");
        view.addObject("title", "Create Blood Type");
        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute BloodGroupView blood){
        ResponseEntity<BloodGroupView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "blood", blood , BloodGroupView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<BloodGroupView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getBody().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/blood/edit");
        ResponseEntity<BloodGroupView> apiResponse = null;

        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "blood/id/" + id , BloodGroupView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                BloodGroupView data = apiResponse.getBody();
                view.addObject("bloods", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ": " + apiResponse.getBody());
            }

        } catch (Exception e) {
            view.addObject("errorMsg", e.getMessage());
        }
        
        view.addObject("title", "Edit Blood");
        
        return view;
    }

    @PostMapping("/update")
    ResponseEntity<?> update(@ModelAttribute BloodGroupView blood){
        ResponseEntity<BloodGroupView> apiResponse = null;

        try {
            restTemplate.put(apiUrl + "blood", blood);
            apiResponse = restTemplate.getForEntity(apiUrl + "blood/id/" + blood.getId() , BloodGroupView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<BloodGroupView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getBody().toString() + ": " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}/{code}")
    public ModelAndView delete(@PathVariable int id, @PathVariable String code){
        ModelAndView view = new ModelAndView("/blood/delete");
        view.addObject("id", id);
        view.addObject("code", code);
        view.addObject("title", "Delete Confirmation");
        return view;
    }

    @PostMapping("/delete/{id}/{userId}")
    ModelAndView delete(@PathVariable int id, @PathVariable int userId) {
        ModelAndView view = new ModelAndView("/blood/delete");
        ResponseEntity<BloodGroupView> apiResponse = null;
        BloodGroupView bloodGroup = new BloodGroupView();

        bloodGroup.setId(id);
        bloodGroup.setDeletedBy(userId);

        try {
            apiResponse = restTemplate.exchange(
                apiUrl + "/blood/delete/" + id + "/" + userId, 
                HttpMethod.DELETE, new HttpEntity<BloodGroupView>(bloodGroup), 
                BloodGroupView.class
            );
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                BloodGroupView data = apiResponse.getBody();
                view.addObject("bloods", data);
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
