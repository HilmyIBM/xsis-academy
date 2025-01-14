package com.xsis.transaction.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    @GetMapping("/order")
    public ModelAndView order() {
        return new ModelAndView("transaction/order/index");
    }

}
