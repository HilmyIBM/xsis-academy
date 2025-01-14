package com.xsis.master.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller()
@RequestMapping("/category")
public class CategoryController {

    private List<CategoryModel> categoryModels = new ArrayList<>();
    private int id = 1;

    public CategoryController() {
        categoryModels.add(new CategoryModel(id++, "Bodrek", "Obat", false, 1, LocalDateTime.now()));
        categoryModels.add(new CategoryModel(id++, "Ketoprak", "Makanan", false, 2, LocalDateTime.now()));
        categoryModels.add(new CategoryModel(id++, "Panadol Merah", "Obat", false, 1, LocalDateTime.now()));
        categoryModels.add(new CategoryModel(id++, "Buah Naga", "Buah", false, 4, LocalDateTime.now()));
    }

    @GetMapping("")
    public ModelAndView category() {
        var view = new ModelAndView("master/category/index");

        view.addObject("category", categoryModels);

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        var view = new ModelAndView("master/category/add");

        view.addObject("title", "Add new category");

        return view;
    }

    @PostMapping("/add")
    public String saveCategory(@ModelAttribute CategoryModel model) {

        if (model.getName().equalsIgnoreCase("") || model.getDescription().equalsIgnoreCase(""))
            return "gagal";

        model.setId(id++);
        model.setCreateDate(LocalDateTime.now());
        model.setCreateBy((long) (Math.random() * (10 - 1 + 1)) + 1);

        categoryModels.add(model);

        var currentDate = LocalDateTime.now();

        model.setCreateDate(currentDate);

        System.out.println(model.toString());

        return "sukses";
    }

    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView("master/category/edit");

        CategoryModel category = categoryModels
                .stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow();

        view.addObject("title", "Edit Category");
        view.addObject("category", category);

        return view;
    }

}
