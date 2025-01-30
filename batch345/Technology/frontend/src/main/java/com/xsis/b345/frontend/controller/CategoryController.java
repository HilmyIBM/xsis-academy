package com.xsis.b345.frontend.controller;

import org.springframework.beans.factory.annotation.Value;
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
import com.xsis.b345.frontend.models.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Value("${application.api.url}")
    private String apiUrl;
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${application.page.size}")
    private int pageSize;

    @GetMapping("")
    public ModelAndView index(String filter,HttpSession session,Integer page, Integer number) {
        if (session.getAttribute("role")!=null && session.getAttribute("role").equals(1) ){
            ModelAndView view = new ModelAndView("/category/index");
            ResponseEntity<pagingView> apiResponse = null;
             //isi halaman
             page=(page!=null)?page:pageSize;
             //nomor halaman
             number = (number != null) ? number : 0 ;
            try {
                if (filter == null || filter.isEmpty()) {
                    apiResponse = restTemplate.getForEntity(apiUrl + "/category/paginated/"+number+"/"+page, pagingView.class);
                } else {
                    apiResponse = restTemplate.getForEntity(apiUrl + "/category/filter/"+ filter+"/"+number+"/"+page,
                    pagingView.class);
                }
                if (apiResponse.getStatusCode() == HttpStatus.OK) {
                    view.addObject("category", apiResponse.getBody());
                } else {
                    throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
                }
            } catch (Exception e) {
                view.addObject("errorMSG", e.getMessage());
            }
            view.addObject("filter", filter);
            view.addObject("pageSize", page);
            return view;
        }
        else{
            return new ModelAndView("redirect:/");
        }
    }

    @GetMapping("/add")
    public ModelAndView addData() {
        ModelAndView view = new ModelAndView("/category/add");
        view.addObject("title", "Add new category");
        return view;
    }

    @PostMapping("/save")
    ResponseEntity<?> save(@ModelAttribute categoryView Category) {
        ResponseEntity<categoryView> apiResponse = null;
        try {
            apiResponse = restTemplate.postForEntity(apiUrl + "/category", Category, categoryView.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                return new ResponseEntity<categoryView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editdata(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/category/edit");
        view.addObject("title", "Edit Category");
        ResponseEntity<categoryView> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "/category/id/" + id, categoryView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("category", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            view.addObject("errorMSG", e.getMessage());
        }
        return view;
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute categoryView Category) {
        ResponseEntity<categoryView> apiResponse = null;
        try {
            restTemplate.put(apiUrl + "/category", Category);
            apiResponse = restTemplate.getForEntity(apiUrl + "/category/id/" + Category.getId(), categoryView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<categoryView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/category/detail");
        view.addObject("title", "Detail Category");
        ResponseEntity<categoryView> apiResponse = null;
        try {
            apiResponse = restTemplate.getForEntity(apiUrl + "category/id/" + id, categoryView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("category", apiResponse.getBody());
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMSG", e.getMessage());

        }
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView viewDelete(@PathVariable int id) {
        ModelAndView view = new ModelAndView("category/delete");
        view.addObject("id", id);
        view.addObject("title", "Hapus Kategori");
        return view;
    }

    @PostMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> deleteData(@PathVariable int id, @PathVariable int userId) {
        ResponseEntity<categoryView> apiResponse = null;
        try {
            apiResponse = restTemplate.exchange(apiUrl + "/category/delete/" + id + "/" + userId, HttpMethod.DELETE,
                    null,
                    categoryView.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<categoryView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody().toString());
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
