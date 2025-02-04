package com.kel1.healthcare.healthcare_fe.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

import com.kel1.healthcare.healthcare_fe.models.UserView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
    // HTTP Client
    private RestTemplate restTemplate = new RestTemplate();

    // API URL
    @Value("${application.api.url}")
    private String apiUrl;

    // Hash SHA-256
    private static String stringToHex(String strInput) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashByte = digest.digest(strInput.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * hashByte.length);
        for (int i = 0; i < hashByte.length; i++) {
            String hex = Integer.toHexString(0xff & hashByte[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView view = new ModelAndView("/auth/login");

        view.addObject("title", "Login Page");

        return view;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute UserView data, HttpSession sess) {
        try {
            ResponseEntity<UserView> apiResponse = restTemplate
                    .getForEntity(apiUrl + "/customer/email/" + data.getEmail(), UserView.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                UserView customer = apiResponse.getBody();

                if (stringToHex(data.getPassword()).equals(customer.getPassword())) {
                    sess.setAttribute("userId", customer.getId());
                    sess.setAttribute("email", customer.getEmail());
                    sess.setAttribute("roleId", customer.getRoleId());

                    return new ResponseEntity<UserView>(customer, HttpStatus.OK);
                } else {
                    sess.invalidate();
                    sess.setAttribute("errorMsg", "Invalid Password");
                    return new ResponseEntity<String>("Invalid Password", HttpStatus.UNAUTHORIZED);
                }
            } else {
                sess.invalidate();
                sess.setAttribute("errorMsg", "Invalid E-Mail");
                return new ResponseEntity<String>("Invalid E-Mail", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            sess.invalidate();
            sess.setAttribute("errorMsg", e.getMessage());

            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
