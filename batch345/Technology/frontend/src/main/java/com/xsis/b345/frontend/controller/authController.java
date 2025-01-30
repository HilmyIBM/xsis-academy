package com.xsis.b345.frontend.controller;

import java.security.MessageDigest;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.b345.frontend.models.customerView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class authController {

    private RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "http://localhost:8080/api/customer";

    private static String bytestoHex(byte[] hash){
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView view = new ModelAndView("auth/login");
        view.addObject("txtTitleLogin", "Login");
        return view;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login_method(HttpSession session,@RequestParam String email,@RequestParam String password) throws Exception {
        ResponseEntity<customerView> apiResponse = null;
        apiResponse = restTemplate.getForEntity(apiUrl + "/email/"+email, customerView.class);
        if (apiResponse.getStatusCode()==HttpStatus.OK) {
            customerView customer = apiResponse.getBody();
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes("UTF-8"));
                password = bytestoHex(hash);
                if(email.equals(customer.getEmail()) && password.equals(customer.getPassword())){
                    session.setAttribute("userName", customer.getName());
                    session.setAttribute("email", customer.getEmail());
                    session.setAttribute("role", customer.getRoleId());
                    session.setAttribute("id", customer.getId());
                    return new ResponseEntity<customerView>(customer, HttpStatus.OK);
                } else {
                    session.invalidate();
                    return new ResponseEntity<String>("Email or password is wrong", HttpStatus.UNAUTHORIZED);
                }
            }else{
                session.invalidate();
                return new ResponseEntity<>("Email user tidak ditemukan", HttpStatus.NO_CONTENT);
            }
        }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("redirect:/customer/add");
    }
}
