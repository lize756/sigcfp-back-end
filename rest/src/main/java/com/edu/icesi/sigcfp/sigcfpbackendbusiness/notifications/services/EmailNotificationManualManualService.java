package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Contact;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Noti;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICompanyService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.INotiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailNotificationManualManualService implements IEmailNotificationManualService {

    public final static long START_PERIOD = 1L;
    public final static long END_PERIOD = 2L;

    @Autowired private JavaMailSender mailSender;
    @Autowired private ICompanyService iCompanyService;

    @Autowired private INotiService iNotiService;


    @Override
    public void sendSimpleEmail() {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        Noti noti = iNotiService.searchNoti(START_PERIOD);
        if (noti != null) {
            try {
                helper.setTo(noti.getNotiEmailDestination());
                helper.setText(noti.getNotiDescription(), true);
                helper.setSubject(noti.getNotiSubject());
                mailSender.send(message);
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
        }
    }



    @Override
    public void sendStartInternPeriodNotification() {

    }

    @Override
    public void sendEndInternPeriodNotification() {
    }

    @Override
    public void sendStartNotificationsToContacts() {

    }

    @Override
    public void sendEndNotificationsToContacts() {

        List<Contact> contactList = new ArrayList<>();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            if (iCompanyService.companies() != null) {
                for (Company company : iCompanyService.companies()) {
                    if (company.getContacts() != null) {
                        for (Contact contact : company.getContacts()) {
                            helper.setTo(contact.getContEmail());
                            helper.setText("Hola " + contact.getContName() +", este es un aviso sobre la apertura o cierre de los periodos de práctica de Unicesi. " +
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
