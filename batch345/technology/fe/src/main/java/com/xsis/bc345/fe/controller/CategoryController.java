package com.xsis.bc345.fe.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.Category;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/category")
public class CategoryController {
    List<Category> data = new ArrayList<>();
    CategoryController(){
        data.add(new Category());
        data.get(0).setId(1);
        data.get(0).setName("Makanan");
        data.get(0).setDesc("Kategori Makanan");
        data.get(0).setCreateBy(1);
        data.get(0).setDeleted(false);
        data.get(0).setCreatedDate(LocalDateTime.now());

        data.add(new Category());
        data.get(1).setId(2);
        data.get(1).setName("Minuman");
        data.get(1).setDesc("Kategori Minuman");
        data.get(1).setCreateBy(1);
        data.get(1).setDeleted(false);
        data.get(1).setCreatedDate(LocalDateTime.now());

        data.add(new Category());
        data.get(2).setId(2);
        data.get(2).setName("Obat-obatan");
        data.get(2).setDesc("Kategori Obat-obatan");
        data.get(2).setCreateBy(1);
        data.get(2).setDeleted(false);
        data.get(2).setCreatedDate(LocalDateTime.now());
    }
    @GetMapping("")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/category/index");
        view.addObject("category", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/category/add");
        view.addObject("title", "Add New Category");
        view.addObject("name", "budi");
        return view;
    }
    
    @PostMapping("/save")
    public String save(@ModelAttribute Category category){
        String result = "sukses";
        if(category.getName() == ""){
            return "gagal";
        }
        return result;
    }
    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable long id){
        ModelAndView view = new ModelAndView("/category/edit");
        Category category = data.get((int)id-1);
        // category.setId(id);
        // category.setName("Makanan");
        // category.setDesc("Kategory Makanan");
        // category.setDeleted(false);
        view.addObject("title", "Edit");
        view.addObject("category", category);
        return view;
    }
    @GetMapping("/{id}")
    ModelAndView delete(@PathVariable long id){
        ModelAndView view = new ModelAndView("/category/edit");
        data.remove((int)id-1);
        // category.setId(id);
        // category.setName("Makanan");
        // category.setDesc("Kategory Makanan");
        // category.setDeleted(false);
        view.addObject("title", "Edit");
        // view.addObject("category", category);
        return view;
    }
    
}
