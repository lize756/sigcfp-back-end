package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailNotificationService implements IEmailNotificationService{

    @Autowired private JavaMailSender mailSender;


    @Override
    /**
     * Envía una notificación de correo electrónico
     * @param emailBody Contiene los datos del correo electrónico
     */
    public void sendSimpleEmail(EmailBody emailBody) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(emailBody.getEmail());
            helper.setText(emailBody.getContent(), true);
            helper.setSubject(emailBody.getSubject());
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendEmailWithAttachment(EmailBody emailBody) {

    }


}
