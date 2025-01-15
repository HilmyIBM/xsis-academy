package com.xsis.frontend.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.frontend.model.Category;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
    List<Category> data = new ArrayList<Category>();

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
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("category/index");
        view.addObject("category", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("category/add");
        view.addObject("title", "Add New Category");
        return view;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Category category) {
        if (category.getName() == "") {
            return "gagal";
        } else {
            return "success";
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable long id) {
        ModelAndView view = new ModelAndView("category/edit");
        Category category = data.get((int) id - 1);

        view.addObject("title", "Edit Category");
        view.addObject("category", category);
        return view;
    }

}
