package com.xa.pos313.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xa.pos313.configurations.EmailConfig;
import com.xa.pos313.models.SendMail;

@RestController
@RequestMapping(value="/sendmail")
public class EmailController {

    @Autowired
    private EmailConfig emailConfig;

    @PostMapping(value="send")
    public void send(@RequestBody SendMail sendMail) {
        this.emailConfig.sendEmail(sendMail.getTo(), sendMail.getSubject(),sendMail.getBody());
    }    
}
