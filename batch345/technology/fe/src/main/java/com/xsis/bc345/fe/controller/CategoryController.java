package com.xsis.bc345.fe.controller;

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
    ModelAndView index() {
        ModelAndView view = new ModelAndView("/category/index");

        return view;
    }

    @GetMapping("/add")
    ModelAndView add() {
        ModelAndView view = new ModelAndView("/category/add");
        view.addObject("title", "Add New Category");
        view.addObject("nama", "Nama Saya Budi");

        return view;
    }
    
    @PostMapping("/save")
    String save(@ModelAttribute Category category) {
        try {
            if (category.getName() == "") {
                return "Gagal";
            }
            else {
                return "Sukses";
            }
        }
        catch(Exception e) {
            return "Gagal";
        }
    }

    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable long id) {
        ModelAndView view = new ModelAndView("/category/edit");

        // Get Category data by requested Category ID
        Category category = new Category();
        category.setId(id);
        category.setName("Makanan");
        category.setDescription("Kategory Makanan");
        category.setDeleted(false);

        view.addObject("title", "Edit Category");
        view.addObject("category", category);

        return view;
    }
}
