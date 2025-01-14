package com.xsis.transaction.history;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderHistoryController {

    @GetMapping("/order/history")
    public ModelAndView orderHistory() {
        return new ModelAndView("transaction/order_history/index");
    }

}
