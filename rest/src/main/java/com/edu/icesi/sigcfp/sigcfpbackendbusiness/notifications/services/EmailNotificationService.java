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

   // private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(EmailNotificationService.class);
    @Autowired private JavaMailSender mailSender;

    @Override
    public void sendSimpleEmail(EmailBody emailBody) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(emailBody.getEmail());
            helper.setText(emailBody.getContent(), true);
            helper.setSubject(emailBody.getSubject());
            mailSender.send(message);
            //LOGGER.info("Mail enviado!");
        } catch (MessagingException e) {
           // LOGGER.error("Hubo un error al enviar el mail: {}", e.getMessage());
        }
    }

    @Override
    public void sendSimpleEmail() {

    }

    @Override
    public void sendEmailWithAttachment(EmailBody emailBody) {

    }



    @Override
    public void sendEmailWithAttachment() {

    }
}
