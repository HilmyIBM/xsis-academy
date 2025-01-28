package com.xsis.frontend.controller;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.hash.Hashing;
import com.xsis.frontend.model.CustomerView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${application.api.url}")
    private String apiUrl;

    @GetMapping("/login")
    public ModelAndView loginView() {
        ModelAndView view = new ModelAndView("auth/login");
        view.addObject("title", "Login Page");

        return view;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute CustomerView loginReq) {
        ResponseEntity<CustomerView> response = null;

        String password = loginReq.getPassword();
        String hashPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        loginReq.setPassword(hashPassword);

        try {
            response = restTemplate.postForEntity(apiUrl + "/auth/login", loginReq, CustomerView.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<CustomerView>(response.getBody(), HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("""
                            {"error":"Invalid Email and Password"}
                        """, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        ModelAndView view = new ModelAndView("redirect:/");

        return view;
    }
}
