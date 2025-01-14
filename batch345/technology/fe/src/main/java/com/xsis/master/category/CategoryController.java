package com.xsis.master.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {

    @GetMapping("/category")
    public ModelAndView category() {
        return new ModelAndView("master/category/index");
    }

}
