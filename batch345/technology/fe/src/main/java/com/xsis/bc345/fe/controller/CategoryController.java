package com.xsis.bc345.fe.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private List<Category> data = new ArrayList<Category>();

    CategoryController() {
        data.add(new Category());
        data.get(0).setId(1);
        data.get(0).setName("Makanan");
        data.get(0).setDescription("Kategori Makanan");
        data.get(0).setDeleted(false);
        data.get(0).setCreateBy(1);
        data.get(0).setCreateDate(LocalDateTime.now());

        data.add(new Category());
        data.get(1).setId(2);
        data.get(1).setName("Obat");
        data.get(1).setDescription("Kategori Obat-obatan");
        data.get(1).setDeleted(false);
        data.get(1).setCreateBy(1);
        data.get(1).setCreateDate(LocalDateTime.now());
    }
    
    @GetMapping("")
    ModelAndView index() {
        ModelAndView view = new ModelAndView("/category/index");

        view.addObject("category", data);

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
        Category category =  data.get((int)id-1);

        view.addObject("title", "Edit Category");
        view.addObject("category", category);

        return view;
    }

    @PostMapping("/update")
    String update(@ModelAttribute Category category) {
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
}
