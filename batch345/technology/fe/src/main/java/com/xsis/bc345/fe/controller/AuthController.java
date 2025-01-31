package com.xsis.bc345.fe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.xsis.bc345.fe.models.AuthView;
import com.xsis.bc345.fe.models.CustomerView;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("auth")
public class AuthController {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView view = new ModelAndView("auth/login");
        view.addObject("title", "Login Page");
        return view;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@ModelAttribute AuthView data, HttpSession sess) {
        ResponseEntity<CustomerView> apiResponse = null;
        try {
            apiResponse = restTemplate.exchange(apiUrl + "auth/login", HttpMethod.POST, new HttpEntity<AuthView>(data),
                    CustomerView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {

                sess.setAttribute("userId", apiResponse.getBody().getId());
                sess.setAttribute("email", apiResponse.getBody().getEmail());
                sess.setAttribute("roleId", apiResponse.getBody().getRoleId());
                sess.setAttribute("username", apiResponse.getBody().getName());

                return new ResponseEntity<CustomerView>(apiResponse.getBody(), HttpStatus.OK);
            } else {
                sess.invalidate();
                sess.setAttribute("errorMsg", "Invalid User");
                return new ResponseEntity<String>(
                        apiResponse.getStatusCode().toString() + " : " + apiResponse.getBody(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("register")
    public ModelAndView register() {
        ModelAndView view = new ModelAndView("auth/add");
        return view;
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpSession sess) {
        sess.invalidate();
        return new ModelAndView("redirect:/");
    }

}
