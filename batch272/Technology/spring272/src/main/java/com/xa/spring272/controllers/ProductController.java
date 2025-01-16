package com.xa.spring272.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/product/")
public class ProductController {
	
	@GetMapping(value="/index")
	ModelAndView index() {
		ModelAndView view = new ModelAndView("/product/index");
		return view;
	}

	@GetMapping(value="/addform")
	ModelAndView addform() {
		ModelAndView view = new ModelAndView("/product/addform");
		return view;
	}

	@GetMapping(value="/editform/{id}")
	ModelAndView editform(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/product/editform");
		return view;
	}
}
