package com.xa.spring272.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xa.spring272.models.Users;
import com.xa.spring272.repositories.UsersRepo;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping(value = "/")
	ModelAndView index() {
		ModelAndView view = new ModelAndView("/index");
		return view;
	}
	
	
	@GetMapping(value="/profile")
	ModelAndView profile(HttpSession sess) {
		ModelAndView view = new ModelAndView("/profile");
		view.addObject("sessfullname", sess.getAttribute("fullname"));
		return view;
	}

	@GetMapping(value="/home")
	// ModelAndView home(HttpSession sess) {
	ModelAndView home() {
		ModelAndView view = new ModelAndView("/home");
		// if(sess.getAttribute("fullname").equals("")) {
		// 	view = new ModelAndView("redirect:/");
		// } else {
		// 	view.addObject("sessfullname", sess.getAttribute("fullname"));
		// }
		return view;
	}

	@GetMapping(value="/logout")
	ModelAndView logout(HttpSession sess) {
		ModelAndView view = new ModelAndView("/logout");
		sess.setAttribute("uid", "");
		sess.setAttribute("username", "");
		sess.setAttribute("fullname", "");
		return new ModelAndView("redirect:/");
	}
}
