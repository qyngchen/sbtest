package com.qyngchen.sbemail.controller;

import com.qyngchen.sbemail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SendMailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/send")
    public void send() {

        mailService.sendEmail();
    }
}
