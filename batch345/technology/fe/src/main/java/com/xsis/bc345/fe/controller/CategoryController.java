package com.xsis.bc345.fe.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.Category;


@Controller
@RequestMapping("/category")
public class CategoryController {
    @GetMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/category/index");
        return view;
    }
    List<Category> category = new ArrayList<Category>();
    
    category.add(new category(){

        category.add(0).setId(1);
        category.add(0).setName("Makanan");
        category.add(0).setDescriptions("Kategori Makanan");
        category.add(0).setDelated(false);
        category.add(0).setCreateBy(1);
        category.add(0).setCreateDate(LocalDateTime.now());
        category.add(new category());
        
        category.add(new category());
        category.add(1).setId(2);
        category.add(1).setName("Obat");
        category.add(1).setDescriptions("Kategori Obat-obatan");
        category.add(1).setDelated(false);
        category.add(1).setCreateBy(1);
        category.add(1).setCreateDate(LocalDateTime.now());
    });
    
    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/category/add");
        view.addObject("title", "Add New Category");
        return view;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Category category){
        String result ="";
        if(category.getName() == ""){
            return "Gagal";
        }else{
            return "Sukses";
        }
    }

    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable int id){
        ModelAndView view = new ModelAndView("/category/edit");

        Category category = new Category();
        category.setId(id);
        category.setName("Makanan");
        category.setDescriptions("Kategory Makanan");
        category.setDelated(false);
        view.addObject("title", "Edit Category");
        view.addObject("id", id);
        view.addObject("category", category);
        return view;
    }
    
}
