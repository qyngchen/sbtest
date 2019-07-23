package com.qyngchen.sbemail.service.impl;

import com.qyngchen.sbemail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 模板引擎
     */
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${recipient.username}")
    private String recipient;

    /**
     * 发送邮件测试
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

    @Override
    public void sendEmailTemplates() {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(sender);
            helper.setTo(sender);

            Context context = new Context();
            Map<String, Object> map = new HashMap<>();
            map.put("visitorName", "chenqingyang");
            map.put("dayTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            map.put("hourTime", new SimpleDateFormat("HH:mm:ss").format(new Date()));
            map.put("newDayTime",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            context.setVariables(map);
            //context.setVariable("user", "chenqingyang");
            String mailTemplate = templateEngine.process("mailTemplate", context);

            helper.setSubject("测试邮件发送");
            helper.setText(mailTemplate, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
