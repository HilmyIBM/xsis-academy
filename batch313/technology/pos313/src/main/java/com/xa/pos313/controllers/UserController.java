package com.xa.pos313.controllers;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xa.pos313.models.Users;
import com.xa.pos313.repositories.UserRepo;

@Controller
@RequestMapping(value="user")
public class UserController {

	@Autowired UserRepo userrepo;
	
	@GetMapping(value="index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/user/index");
		List<Users> listuser = this.userrepo.findAll();
		view.addObject("listuser", listuser);
		return view;
	}
	
	@GetMapping(value="getuser/{id}")
	public ModelAndView getuser(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/user/showimage");
		Optional<Users> getuser = this.userrepo.findById(id);
		view.addObject("getuser", getuser);
		return view;
	}

	@GetMapping(value="form")
	public ModelAndView form() {
		ModelAndView view = new ModelAndView("/user/form");
		Users user = new Users();
		view.addObject("user", user);
		return view;
	}
	
	private static String bytesToHex(byte[] hash) {
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
	
	private static String UPLOADED_FOLDER = "/home/shwibowo/javafile/";
	
	@PostMapping(value="save")
	ModelAndView save(
			@ModelAttribute Users users, 
			BindingResult result,
			@RequestParam("photofile") MultipartFile file) throws Exception
	{
		if(!result.hasErrors()) {
			try {
				if(file.getOriginalFilename() != "") { 
					byte[] bytes = file.getBytes();
					Path path = Paths.get(UPLOADED_FOLDER+file.getOriginalFilename());
					Files.write(path, bytes);
				}
				
				String pass = (String) result.getFieldValue("Password");
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				byte[] encodehash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
				users.setPassword(bytesToHex(encodehash));
				this.userrepo.save(users);
				
				try {
					System.out.println("Sending...");
					sendmail(result.getFieldValue("email").toString());
					System.out.println("Done!");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("redirect:/user/index");
	}
	
	@Autowired
	private JavaMailSender javamailsender;
	
	public void sendmail(String address) throws Exception {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(address);
		msg.setSubject("new user registered");
		msg.setText("Welcome to POS 313...");
		javamailsender.send(msg);
	}

	@PostMapping(value="ceklogin")
	ModelAndView ceklogin(@ModelAttribute Users users, BindingResult result, HttpSession sess) {
		String redirect = "";
		if(!result.hasErrors()) {
			String username = (String) result.getFieldValue("UserName");
			String password = (String) result.getFieldValue("Password");

			try {
				MessageDigest digest;
				digest = MessageDigest.getInstance("SHA-256");
				byte[] encodehash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
				String pass = bytesToHex(encodehash);
				
				List<Users> getuser = this.userrepo.getLogin(username, pass);
				
				try {
					sess.setAttribute("uid", getuser.get(0).getId());
					sess.setAttribute("username", getuser.get(0).getUserName());
					sess.setAttribute("fullname", getuser.get(0).getFullName());
					System.out.println("Access Granted!");
					if(getuser.get(0).getId()==null) {
						redirect = "redirect:/";
					} else {
						redirect = "redirect:/home";
					}
				} catch (Exception e) {
					System.out.println("Access Denied!");
					redirect = "redirect:/";
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				redirect = "redirect:/";
			}
		}
		return new ModelAndView(redirect);
	}
	
	
}
