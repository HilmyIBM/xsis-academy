package com.xa.spring272.controllers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import com.xa.spring272.models.Category;
import com.xa.spring272.models.Users;
import com.xa.spring272.models.Variant;
import com.xa.spring272.repositories.CategoryRepo;
import com.xa.spring272.repositories.VariantRepo;

@Controller
@RequestMapping(value="/variant/")
public class VariantController {
	
	@Autowired
	private VariantRepo variantrepo;
	
	@Autowired
	private CategoryRepo categoryrepo;
	
	@GetMapping(value="/index")
	ModelAndView index() {
		ModelAndView view = new ModelAndView("/variant/index");
		List<Variant> listvariant = this.variantrepo.findAll();
		view.addObject("listvariant", listvariant);
		return view;
	}
	
	@GetMapping(value="/addform")
	ModelAndView addform() {
		ModelAndView view = new ModelAndView("/variant/addform");
		Variant variant = new Variant();
		view.addObject("variant", variant);
		List<Category> listcat = this.categoryrepo.findAll();
		view.addObject("listcat", listcat);
		return view;
	}

	@PostMapping(value="save")
	ModelAndView save(@ModelAttribute Variant variant, BindingResult result) {
		if(!result.hasErrors()) {
			this.variantrepo.save(variant);
		}
		return new ModelAndView("redirect:/variant/index");
	}
	
	@GetMapping(value="/edit/{id}")
	ModelAndView editform(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/variant/addform");
		Variant variant = this.variantrepo.findById(id).orElse(null);
		view.addObject("variant", variant);
		List<Category> listcat = this.categoryrepo.findAll();
		view.addObject("listcat", listcat);
		return view;
	}
}
