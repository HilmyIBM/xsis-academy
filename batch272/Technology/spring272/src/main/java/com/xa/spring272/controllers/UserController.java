package com.xa.spring272.controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
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

import com.sun.mail.util.MailSSLSocketFactory;
import com.xa.spring272.models.Users;
import com.xa.spring272.repositories.UsersRepo;

@Controller
@RequestMapping("/users/")
public class UserController {

	@Autowired
	private UsersRepo userrepo;
	
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
	
	@GetMapping(value="/index")
	ModelAndView index() {
		ModelAndView view = new ModelAndView("/users/index");
		List<Users> listuser = this.userrepo.findAll();
		view.addObject("listuser", listuser);
		return view;
	}
	
	@GetMapping(value="/addform")
	ModelAndView addform() {
		ModelAndView view = new ModelAndView("/users/addform");
		Users users = new Users();
		view.addObject("users", users);
		return view;
	}
	
	private static String UPLOADED_FOLDER = "/home/bowo/javafile/";
	
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
					sendmail2(result.getFieldValue("email").toString());
					System.out.println("DONE!");
		        } catch (MessagingException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("redirect:/users/index");
	}
	
	@GetMapping(value="/edit/{id}")
	ModelAndView editform(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/users/addform");
		Users users = this.userrepo.findById(id).orElse(null);
		view.addObject("users", users);
		return view;
	}
	
	@GetMapping(value="/deleteform/{id}")
	ModelAndView delete(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/users/deleteform");
		Users getusers = this.userrepo.findById(id).orElse(null);
		view.addObject("getusers", getusers);
		return view;
	}
	
	@PostMapping(value="delete")
	ModelAndView delete(@ModelAttribute Users users, BindingResult result) {
		Long id = Long.parseLong(result.getFieldValue("Id").toString(),10);
		if(!result.hasErrors()) {
			this.userrepo.deleteById(id);
		}
		return new ModelAndView("redirect:/users/index"); 
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
					redirect = "redirect:/home";
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
	
	public void sendmail(String address) throws AddressException, MessagingException, IOException {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("shwibowo17@gmail.com");
		mailSender.setPassword("your password");
		
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "false");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		 
		mailSender.setJavaMailProperties(properties);
		
		String from = "shwibowo17@gmail.com";
		String to = address;
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(from);
		message.setTo(to);
		message.setSubject("This is a plain text email");
		message.setText("Hello guys! This is a plain text email.");
		
		mailSender.send(message);
	}
	
	@Autowired
	private JavaMailSender javamailsender;
	
	public void sendmail2(String address) throws Exception {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(address);

        msg.setSubject("POS-272 Registration");
        msg.setText("Welcome to POS-272 application"
        		+ " please follow this link to verify your email...");

        javamailsender.send(msg);
    }
	
}
