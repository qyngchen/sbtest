package com.qyngchen.sbemail.service;

/**
 * 邮件
 *
 * @author chenqingyang
 */
public interface MailService {

    /**
     * 发送简单文本邮件
     */
    void sendEmail();

    /**
     * 发送模板邮件
     */
    void sendEmailTemplates();
}
