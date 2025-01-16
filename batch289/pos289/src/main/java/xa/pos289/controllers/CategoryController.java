package xa.pos289.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import xa.pos289.models.Category;
import xa.pos289.repositories.CategoryRepo;

@Controller
@RequestMapping(value="/category/")
public class CategoryController {
	
	@Autowired
	private CategoryRepo catrepo;

	@GetMapping(value="index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/category/index");
		List<Category> listcat = this.catrepo.findAll();
		view.addObject("listcat", listcat);
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
	public ModelAndView save(@ModelAttribute Category category, BindingResult result) {
		if(!result.hasErrors()) {
			category.setCreate_by("shwibowo");
			this.catrepo.save(category);
		}
		return new ModelAndView("redirect:/category/index");
	}
	
	@GetMapping(value="/edit/{id}")
	public ModelAndView editform(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/category/form");
		Category category = this.catrepo.findById(id).orElse(null);
		view.addObject("category", category);
		return view;
	}

	@GetMapping(value="/deleteform/{id}")
	public ModelAndView deleteform(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/category/deleteform");
		Category category = this.catrepo.findById(id).orElse(null);
		view.addObject("category", category);
		return view;
	}
	
	@GetMapping(value="/del/{id}")
	public ModelAndView del(@PathVariable("id") Long id) {
		if(id != null) {
			this.catrepo.deleteById(id);
		}
		return new ModelAndView("redirect:/category/index");
	}
}
