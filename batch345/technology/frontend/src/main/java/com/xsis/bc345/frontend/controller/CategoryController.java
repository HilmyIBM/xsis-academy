package com.xsis.bc345.frontend.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/category")
public class CategoryController {

    private List<Category> data = new ArrayList<Category>();
    
    CategoryController(){
      data.add(new Category(1, "Makanan", "Kategori Makanan", false, 1, LocalDateTime.now(), 1, LocalDateTime.now()));
      data.add(new Category(2, "Obat", "Kategori Obat-Obatan", false, 1, LocalDateTime.now(), 1, LocalDateTime.now()));
    }
    

  @GetMapping("")
  public ModelAndView index() {

    
      ModelAndView view = new ModelAndView("master/category/index");
     
      
      view.addObject("category", data);
      
      return view;
  }
  @GetMapping("/add")
  public ModelAndView add() {
      ModelAndView view = new ModelAndView("master/category/add");
      view.addObject("title", "Add new Category");
      return view;
  }

  @PostMapping("/save")
  public String save(@ModelAttribute Category category) {
    if(category.getName() == ""){
      return "Gagal";
    }
    else {
      return "Sukses";
    }
  }

  @GetMapping("/edit/{id}") 
  ModelAndView edit(@PathVariable long id){
    ModelAndView view = new ModelAndView("master/category/edit");

    // Category category = new Category();
    // category.setId(id);
    // category.setName("Makanan");
    // category.setDescription("Kategori Makanan");
    // category.setDeleted(false);
    // view.addObject("title", "Edit Category");
    // view.addObject("category", category);
  
    return view;
  }
  
}
