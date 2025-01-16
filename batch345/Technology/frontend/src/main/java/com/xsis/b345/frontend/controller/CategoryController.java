package com.xsis.b345.frontend.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

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
import com.xsis.b345.frontend.models.*;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/category")
public class CategoryController {
   /*  private List<categoryView> data = new ArrayList<categoryView>(); */
    private RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl="http://localhost:8080/api/category";

/*     CategoryController(){
        data.add(new category());
        data.get(0).setId(1);
        data.get(0).setName("Makanan");
        data.get(0).setDeskripsi("Kategori Makanan");
        data.get(0).setDeleted(false);
        data.get(0).setCreateBy(1);
        data.get(0).setCreateDate(LocalDateTime.now());

        data.add(new category());
        data.get(1).setId(2);
        data.get(1).setName("Obat");
        data.get(1).setDeskripsi("Kategori Obat-Obatan");
        data.get(1).setDeleted(false);
        data.get(1).setCreateBy(1);
        data.get(1).setCreateDate(LocalDateTime.now());
    } */

    @GetMapping("")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("/category/index");
        ResponseEntity<categoryView[]> apiResponse=null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl,categoryView[].class);
            if (apiResponse.getStatusCode()==HttpStatus.OK) {
                view.addObject("category", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+" : "+apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        return view;
    }

    @GetMapping("/add")
    public ModelAndView addData() {
        ModelAndView view= new ModelAndView("/category/add");
        view.addObject("title", "Add new category");
        return view;
    }

    @PostMapping("/save")
    ResponseEntity<?>save(@ModelAttribute categoryView Category){
        ResponseEntity<categoryView> apiResponse=null;
        try {
            restTemplate.postForEntity(apiUrl, apiResponse, categoryView.class);
            if (apiResponse.getStatusCode()==HttpStatus.CREATED) {
                return new ResponseEntity<categoryView>(apiResponse.getBody(),HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+" : "+apiResponse.getBody().toString());   
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editdata(@PathVariable int id){
        ModelAndView view= new ModelAndView("/category/edit");
        view.addObject("title", "Edit Category");
        ResponseEntity<categoryView> apiResponse=null;
        try {
            apiResponse=restTemplate.getForEntity(apiUrl+"/id/"+id, categoryView.class);
            if (apiResponse.getStatusCode()==HttpStatus.OK) {
                view.addObject("category", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+" : "+apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }  
        return view;
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute categoryView Category){
        ResponseEntity<categoryView> apiResponse=null;
        try {
           restTemplate.put(apiUrl,Category);
           apiResponse=restTemplate.getForEntity(apiUrl+"/id/"+Category.getId(), categoryView.class);
           
           if (apiResponse.getStatusCode()==HttpStatus.OK) {
                return new ResponseEntity<categoryView>(apiResponse.getBody(),HttpStatus.OK);
           } else {
                throw new Exception(apiResponse.getStatusCode().toString()+" : "+apiResponse.getBody().toString());
           }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/category/detail");
        view.addObject("title", "Detail Category");
        ResponseEntity<categoryView> apiResponse=null;
        try {
            apiResponse=restTemplate.getForEntity(apiUrl+"/id/"+id, categoryView.class);
            if (apiResponse.getStatusCode()==HttpStatus.OK) {
                view.addObject("category", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString()+" : "+apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMSG", e.getMessage());
            
        }
        return view;
    }
    
    
}
