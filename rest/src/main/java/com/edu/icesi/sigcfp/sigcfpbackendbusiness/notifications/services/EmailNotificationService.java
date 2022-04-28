package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Contact;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailNotificationService implements IEmailNotificationService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ICompanyService iCompanyService;


    @Override
    /**
     * Envía una notificación de correo electrónico
     * @param emailBody Contiene los datos del correo electrónico
     */
    public void sendSimpleEmail(EmailBody emailBody) {

    }


    @Override
    public void sendEmailWithAttachment(EmailBody emailBody) {

    }

    @Override
    public void sendEmailWithAttachment(EmailBody emailBody, String attachmentPath) {

    }

    @Override
    public void sendStartInternPeriodNotification() {

    }

    @Override
    public void sendEndInternPeriodNotification() {
    }

    @Override
    public void sendNotificationsToContacts() {

        List<Contact> contactList = new ArrayList<>();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            if (iCompanyService.companies() != null) {
                for (Company company : iCompanyService.companies()) {
                    if (company.getContacts() != null) {
                        for (Contact contact : company.getContacts()) {
                            helper.setTo(contact.getContEmail());
                            helper.setText("Hola " + contact.getContName() + ", este es un aviso sobre la apertura o cierre de los periodos de práctica de Unicesi. " +
                                    "Te invitamos a crear tus solicitudes de practicantes; estaremos atentos para ayudarte. " +
                                    "Atte. Equipo SIGCFP", true);
                            helper.setSubject("Aviso de cierre o apertura de periodos de práctica de Unicesi");
                            mailSender.send(message);
                        }

                    }

                }
            }
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }


}
