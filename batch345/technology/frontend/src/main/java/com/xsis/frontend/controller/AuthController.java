package com.xsis.frontend.controller;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
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
    public ResponseEntity<?> login(@ModelAttribute CustomerView loginReq, HttpSession session) {
        ResponseEntity<CustomerView> response = null;

        String password = loginReq.getPassword();
        String hashPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

        try {
            // response = restTemplate.getForEntity(apiUrl + "/customers/email/" +
            // loginReq.getEmail(),
            // CustomerView.class);

            response = restTemplate.exchange(apiUrl + "/customers/email/" + loginReq.getEmail(), HttpMethod.GET, null,
                    CustomerView.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                CustomerView customer = response.getBody();
                if (hashPassword.equals(customer.getPassword())) {
                    session.setAttribute("userId", customer.getId());
                    session.setAttribute("email", customer.getEmail());
                    session.setAttribute("roleId", customer.getRoleId());
                    session.setAttribute("userName", customer.getName());

                    return new ResponseEntity<CustomerView>(customer, HttpStatus.OK);
                } else {
                    session.setAttribute("errorMsg", "Invalid Password");
                    return new ResponseEntity<String>("Invalid Password", HttpStatus.UNAUTHORIZED);
                }
            } else {
                session.setAttribute("errorMsg", "Invalid Email");
                return new ResponseEntity<String>("Invalid Email", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            session.setAttribute("errorMsg", e.getMessage());
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
