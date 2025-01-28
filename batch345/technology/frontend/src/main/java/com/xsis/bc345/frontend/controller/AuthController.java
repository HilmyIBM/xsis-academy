package com.xsis.bc345.frontend.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.bc345.frontend.models.CategoryView;
import com.xsis.bc345.frontend.models.CustomerView;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/auth")
public class AuthController {
  // HTTP Client
  private RestTemplate restTemplate = new RestTemplate();

  // API URL
  private final String apiUrl = "http://localhost:8080/api/customer";
  
  @GetMapping("/login")
  public ModelAndView login() {
      ModelAndView view = new ModelAndView("auth/login");
      view.addObject("title", "Login");
      return view;
  }
  
  @SuppressWarnings("null")
  @PostMapping("/login")
  public ResponseEntity<?> login(@ModelAttribute CustomerView data, HttpSession sess) {
    try {
      ResponseEntity<CustomerView> apiResponse = restTemplate.getForEntity(apiUrl + "/email/" + data.getEmail(), CustomerView.class);
      
      if (apiResponse.getStatusCode() == HttpStatus.OK) {
        CustomerView customer = apiResponse.getBody();
        if(stringToHex(data.getPassword()).equals(customer.getPassword())){
           sess.setAttribute("userId", customer.getId());
           sess.setAttribute("email", customer.getEmail());
           sess.setAttribute("roleId", customer.getRoleId());
           sess.setAttribute("userName", customer.getName());
           
           return new ResponseEntity<CustomerView>(customer,HttpStatus.OK);
        }else{
          sess.invalidate();
          sess.setAttribute("errorMsg", "Invalid Password");

          return new ResponseEntity<String>("Invalid Password", HttpStatus.UNAUTHORIZED);
        }
      } else {
          sess.invalidate();
          sess.setAttribute("errorMsg", "Invalid Email");

          return new ResponseEntity<String>("Invalid Email", HttpStatus.NOT_FOUND);
      
      }
    } catch (Exception e) {
          sess.invalidate();
          sess.setAttribute("errorMsg", e.getMessage());

          return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
  }

  @GetMapping("/logout")
  public ModelAndView logout(HttpSession sess) {
      sess.invalidate();
      return new ModelAndView("redirect:/");
  }
  
  

  private static String stringToHex(String password) throws Exception{
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
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

}
