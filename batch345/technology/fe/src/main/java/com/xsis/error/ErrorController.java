package com.xsis.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @GetMapping("/403")
    public ModelAndView forbidden() {
        return new ModelAndView("error/403");
    }

    @GetMapping("/401")
    public ModelAndView unauthorized() {
        return new ModelAndView("error/401");
    }

}
