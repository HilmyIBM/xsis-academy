package com.xsis.bc345.fe.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.fe.models.CustomerView;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    RestTemplate restTemplate = new RestTemplate();
    @Value("${application.api.url}")
    private String apiUrl;

    private static String bytesToHex(String pass) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
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
    public ModelAndView login(){
        ModelAndView view = new ModelAndView("/auth/login");
        view.addObject("title", "Login Pages");
        return view;
    }

    @PostMapping("/login")
    public ResponseEntity<?> postMethodName(@ModelAttribute CustomerView customer, HttpSession sess) {
        //TODO: process POST request
        try {
            ResponseEntity<CustomerView> apiResponse = restTemplate.getForEntity(apiUrl+"/customer/email/" +customer.getEmail(), CustomerView.class);
            if(apiResponse.getStatusCode() == HttpStatus.OK){
                CustomerView customerApiResponse = apiResponse.getBody();
                if(bytesToHex(customer.getPassword()).equals(customerApiResponse.getPassword())){
                    sess.setAttribute("userId", customerApiResponse.getId());
                    sess.setAttribute("email", customerApiResponse.getEmail());
                    sess.setAttribute("roleId", customerApiResponse.getRoleId());
                    sess.setAttribute("userName", customerApiResponse.getName());
                    return new ResponseEntity<CustomerView>(customerApiResponse, HttpStatus.OK);
                }else{
                    sess.invalidate();
                    sess.setAttribute("errorMsg", "Wrong Password");
                    return new ResponseEntity<String>("Wrong Password", HttpStatus.UNAUTHORIZED);
                }
            }else{
                sess.invalidate();
                sess.setAttribute("errorMsg", "Invalid Email");
                return new ResponseEntity<String>("Invalid Email", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            sess.invalidate();
            sess.setAttribute("errorMsg", e.getMessage());
           return new ResponseEntity<String>("errorMsg", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession sess) {
        sess.invalidate();
        return new ModelAndView("redirect:/"); 
    }
}
