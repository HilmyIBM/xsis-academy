package com.xsis.master.variant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VariantController {

    @GetMapping("/variant")
    public ModelAndView variant() {
        return new ModelAndView("master/variant/index");
    }

}
