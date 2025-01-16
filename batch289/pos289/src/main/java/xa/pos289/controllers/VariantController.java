package xa.pos289.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import xa.pos289.models.Category;
import xa.pos289.models.Variant;
import xa.pos289.repositories.CategoryRepo;
import xa.pos289.repositories.VariantRepo;

@Controller
@RequestMapping(value="/variant/")
public class VariantController {
	
	@Autowired VariantRepo varrepo;
	@Autowired CategoryRepo catrepo;

	@GetMapping(value="index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/variant/index");
		List<Variant> variant = this.varrepo.findAll();
		view.addObject("variant", variant);
		return view;
	}

	@GetMapping(value="form")
	public ModelAndView form() {
		ModelAndView view = new ModelAndView("/variant/form");
		Variant variant = new Variant();
		view.addObject("variant", variant);
		List<Category> category = this.catrepo.findAll();
		view.addObject("category", category);
		return view;
	}
	
	@PostMapping(value="save")
	public ModelAndView save(@ModelAttribute Variant variant, BindingResult result) {
		if(!result.hasErrors()) {
			variant.setCreate_by("shwibowo");
			this.varrepo.save(variant);
		}
		return new ModelAndView("redirect:/variant/index");
	}
}
