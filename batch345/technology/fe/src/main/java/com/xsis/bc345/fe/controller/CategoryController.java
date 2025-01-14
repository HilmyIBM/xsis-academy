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
@RequestMapping("category")
public class CategoryController {
    private List<Category> data = new ArrayList<>();

    @GetMapping("")
    public ModelAndView category() {
        ModelAndView view = new ModelAndView("category/index");

        data.add(new Category());
        data.get(0).setId(1);
        data.get(0).setName("Makanan");
        data.get(0).setDescription("Kategori Makanan");;
        data.get(0).setDeleted(false);
        data.get(0).setCreatedBy(1);
        data.get(0).setCreateDate(LocalDateTime.now()); 
        
        data.add(new Category());
        data.get(1).setId(2);
        data.get(1).setName("Obat");
        data.get(1).setDescription("Kategori Obat");;
        data.get(1).setDeleted(false);
        data.get(1).setCreatedBy(1);
        data.get(1).setCreateDate(LocalDateTime.now());

        view.addObject("category", data);
        return view;
    }

    @GetMapping("add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("category/add");
        view.addObject("title", "Add New Category");
        view.addObject("nama", "Nama saya budi");
        return view;
    }

    @PostMapping("update")
    public String save(@ModelAttribute Category category) {
        try {
            if (category.getName() == "") {
                // return view gagal
                return "gagal";
            } else {
                // return view sukses
                return "sukses";
            }
        } catch (Exception e) {
            // return view gagal
            return "gagal";
        }
        
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable long id) {
        //TODO: process POST request
        ModelAndView view = new ModelAndView("category/edit");

        // Get Category Data by requested category id
        Category category = data.get((int)id-1);

        view.addObject("title", "Edit Category");
        view.addObject("category", category);
        return view;
    }
}