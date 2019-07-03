package com.qyngchen.sbemail.service.impl;

import com.qyngchen.sbemail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${recipient.username}")
    private String recipient;

    /**
     * 简单文本
     */
    @Override
    public void sendEmail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(recipient);
        mailMessage.setSubject("测试邮件发送");
        mailMessage.setText("  来自163邮箱的测试邮件");
        mailSender.send(mailMessage);
    }

    public void setMailSender(){

    }
}
