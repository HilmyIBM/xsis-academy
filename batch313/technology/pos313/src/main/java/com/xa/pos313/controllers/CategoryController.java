package com.xa.pos313.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xa.pos313.models.Category;
import com.xa.pos313.repositories.CategoryRepository;

@Controller
@RequestMapping(value="category")
public class CategoryController {

    @Autowired 
    private CategoryRepository categoryRepo;

    @GetMapping(value="index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/category/index");
        List<Category> category = this.categoryRepo.findAll();
        view.addObject("categorydata", category);
        return view;
    }

    @GetMapping(value="form")
    public ModelAndView form() {
        ModelAndView view = new ModelAndView("/category/form");
        Category category = new Category();
        view.addObject("category", category);
        return view;
    }

    @PostMapping(value="save")
    public ModelAndView save(
        @ModelAttribute Category category,
        BindingResult result
    ) {
        if(!result.hasErrors()) {
            this.categoryRepo.save(category);
        }
        return new ModelAndView("redirect:/category/index");
    }

    @GetMapping(value="edit/{id}")
    public ModelAndView editform(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("/category/form");
        Category category = this.categoryRepo.findById(id).orElse(null);
        view.addObject("category", category);
        return view;
    }
    
    @GetMapping(value="delete/{id}")
    public ModelAndView deleteform(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("/category/delform");
        Category category = this.categoryRepo.findById(id).orElse(null);
        view.addObject("category", category);
        return view;
    }

    @GetMapping(value="del/{id}")
    public ModelAndView deldata(@PathVariable("id") Long id) {
        if(id != null)
            this.categoryRepo.deleteById(id);
        return new ModelAndView("redirect:/category/index");
    }
}
