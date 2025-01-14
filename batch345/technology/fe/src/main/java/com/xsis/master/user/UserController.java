package com.xsis.master.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController{

    @GetMapping("/user")
    public ModelAndView user() {
        return new ModelAndView("master/user/index");
    }

}
