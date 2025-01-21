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
import com.xa.pos313.models.Variant;
import com.xa.pos313.repositories.CategoryRepository;
import com.xa.pos313.repositories.VariantRepository;


@Controller
@RequestMapping(value="variant")
public class VariantController {
    
    @Autowired private VariantRepository variantRepo;
    @Autowired private CategoryRepository categoryRepo;

    @GetMapping(value="index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/variant/index");
        List<Variant> variant = this.variantRepo.findAll();
        view.addObject("variantdata", variant);
        return view;
    }

    @GetMapping(value="form")
    public ModelAndView form() {
        ModelAndView view = new ModelAndView("/variant/form");
        Variant variant = new Variant();
        view.addObject("variant", variant);
        List<Category> category = this.categoryRepo.findAll();
        view.addObject("category", category);
        return view;
    }

    @PostMapping(value="save")
    public ModelAndView save(
        @ModelAttribute Variant variant,
        BindingResult result
    ) {
        if(!result.hasErrors()) {
            this.variantRepo.save(variant);
        }
        return new ModelAndView("redirect:/variant/index");
    }

    @GetMapping(value="edit/{id}")
    public ModelAndView editform(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("/variant/form");
        Variant variant = this.variantRepo.findById(id).orElse(null);
        view.addObject("variant", variant);
        List<Category> category = this.categoryRepo.findAll();
        view.addObject("category", category);
        return view;
    }

    @GetMapping(value="delete/{id}")
    public ModelAndView deleteform(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("/variant/delform");
        Variant variant = this.variantRepo.findById(id).orElse(null);
        view.addObject("variant", variant);
        return view;
    }

    @GetMapping(value="del/{id}")
    public ModelAndView deldata(@PathVariable("id") Long id) {
        if(id != null)
            this.variantRepo.deleteById(id);
        return new ModelAndView("redirect:/variant/index");
    }


}
