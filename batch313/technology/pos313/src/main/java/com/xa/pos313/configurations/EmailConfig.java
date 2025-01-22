package com.xa.pos313.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailConfig {
    
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String Subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(Subject);
        message.setText(body);

        this.javaMailSender.send(message);
    }

}
