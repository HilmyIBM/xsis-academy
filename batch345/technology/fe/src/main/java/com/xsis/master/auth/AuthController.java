package com.xsis.master.auth;

import com.xsis.master.customer.CustomerModel;
import com.xsis.util.ProcessAPI;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final ProcessAPI<AuthDTO, CustomerModel> request;

    private final String API_URL;

    public AuthController(ProcessAPI<AuthDTO, CustomerModel> request, Environment env) {
        this.request = request;
        this.API_URL = env.getProperty("api.url") + "/auth";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        var view = new ModelAndView("auth/login");

        view.addObject("title", "Login Page");

        return view;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession httpSession) {
        httpSession.invalidate();

        return new ModelAndView("redirect:/");
    }

    // ======================== REST ========================

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute AuthDTO model, HttpSession httpSession) {
        ResponseEntity<?> resp = request.send(model, CustomerModel.class,
                HttpMethod.POST, HttpStatus.OK,
                API_URL);

        if (resp.getStatusCode().is2xxSuccessful()) {
            CustomerModel userData = (CustomerModel) Objects.requireNonNull(resp.getBody());

            httpSession.setAttribute("user", userData);

            switch (userData.getRoleId()) {
                case 1 -> httpSession.setAttribute("ROLE", AuthRole.ADMIN);
                case 2 -> httpSession.setAttribute("ROLE", AuthRole.USER);
                default -> httpSession.setAttribute("ROLE", AuthRole.GOBLIN);
            }

            log.info("Login as {}", httpSession.getAttribute("ROLE"));
            return new ResponseEntity<>(userData, HttpStatus.OK);
        }

        // Any kind of error WILL be handled
        // by ControllerAdvice (ErrorsHandler class)
        return null;
    }

}
