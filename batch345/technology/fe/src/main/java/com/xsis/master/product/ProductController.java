package com.xsis.master.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @GetMapping("/product")
    public ModelAndView product() {
        return new ModelAndView("master/product/index");
    }

}
