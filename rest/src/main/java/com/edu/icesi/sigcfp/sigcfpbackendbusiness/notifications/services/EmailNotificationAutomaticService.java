package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICompanyService;
import com.sun.istack.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import static org.slf4j.Logger.*;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class EmailNotificationAutomaticService implements IEmailNotificationAutomaticService {

    private static final Logger LOG = Logger.getLogger(EmailNotificationAutomaticService.class);

    @Autowired private JavaMailSender mailSender;
    @Autowired private ICompanyService iCompanyService;


    @Override
    //@Scheduled(cron = "${cron.startExpression}")
    public void sendEmailNotificationStartInternPeriod() {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo("simply753@gmail.com");
            helper.setText("Este es un ejemplo de alerta automática con cron." +
                    "Te recordamos que tal fecha, iniciamos nuestro periodo para la solicitud de practicantes." +
                    "Estaremos atentos para atenderles." +
                    "Equipo SIGCFP", true);
            helper.setSubject("Apertura de periodo de solicitud de practicantes");
            mailSender.send(message);
            LOG.warning("Se ha enviado un correo de alerta automática");
        } catch (MessagingException ex) {
            ex.printStackTrace();
            LOG.severe("Error al enviar el correo de alerta automática");
        }
    }

    @Override
    //@Scheduled(cron = "${cron.endExpression}")
    public void sendEmailNotificationEndInternPeriod() {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo("simply753@gmail.com");
            helper.setText("Este es un ejemplo de alerta automática con cron." +
                    "Te recordamos que tal fecha, iniciamos nuestro periodo para la solicitud de practicantes." +
                    "Estaremos atentos para atenderles." +
                    "Equipo SIGCFP", true);
            helper.setSubject("Cierre de periodo de solicitud de practicantes");
            mailSender.send(message);
            LOG.warning("Se ha enviado un correo de alerta automática");
        } catch (MessagingException ex) {
            ex.printStackTrace();
            LOG.severe("Error al enviar el correo de alerta automática");
        }
    }
}
