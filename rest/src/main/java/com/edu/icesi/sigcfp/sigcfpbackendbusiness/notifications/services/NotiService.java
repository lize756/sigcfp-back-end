package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Contact;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Noti;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICompanyService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.INotiRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotiService implements INotiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotiService.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String TIME_ZONE = "America/Bogota";

    public final static long START_PERIOD = 1L;
    public final static long END_PERIOD = 2L;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ICompanyService iCompanyService;
    @Autowired
    private INotiRepo iNotiRepo;

    private Noti noti;

    private MimeMessage mimeMessage;
    private MimeMessageHelper helper;

    public NotiService(ICompanyService iCompanyService,
                       INotiRepo iNotiRepo,
                       JavaMailSender mailSender) {
        this.iCompanyService = iCompanyService;
        this.iNotiRepo = iNotiRepo;
        this.mailSender = mailSender;
        mimeMessage = mailSender.createMimeMessage();
        helper = new MimeMessageHelper(mimeMessage);
        noti = new Noti();

    }

    @Override
    @Transactional
    public Noti addNoti(Noti noti) {
        if (!iNotiRepo.existsById(noti.getNotiId())) {
            return iNotiRepo.save(noti);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Noti updateNoti(Noti noti) {
        return iNotiRepo.save(noti);
    }

    @Override
    @Transactional
    public Noti searchNoti(long notiId) {
        if (iNotiRepo.existsById(notiId)) {
            return iNotiRepo.getById(notiId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Noti deleteNoti(long notiId) {
        Noti notiToDelete;
        if (iNotiRepo.existsById(notiId)) {
            notiToDelete = iNotiRepo.findById(notiId).get();
            iNotiRepo.delete(iNotiRepo.getById(notiId));
        } else {
            return null;
        }
        return notiToDelete;
    }

    @Override
    @Transactional
    public List<Noti> notis() {
        return iNotiRepo.findAll();
    }

    @Override
    @Scheduled(cron = "* 36 18 8 5 *", zone = TIME_ZONE)
    public void sendManualStartNotificationsToContacts() {
        LOGGER.warn("Sending manual start notifications to contacts");
        noti = iNotiRepo.getById(START_PERIOD);
        try {
            if (iCompanyService.companies() != null) {
                for (Company company : iCompanyService.companies()) {
                    if (company.getContacts() != null) {
                        for (Contact contact : company.getContacts()) {
                            helper.setTo(contact.getContEmail());
                            helper.setText(noti.getNotiDescription(), true);
                            helper.setSubject(noti.getNotiSubject());
                            mailSender.send(mimeMessage);
                            LOGGER.warn("Notification sent to " + contact.getContEmail());
                        }
                    }
                }
            }
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    @Scheduled(cron = "* 0 19 9 5 *", zone = TIME_ZONE)
    public void sendManualEndNotificationsToContacts() {
        LOGGER.warn("Sending manual end notifications to contacts");
        noti = iNotiRepo.getById(END_PERIOD);
        try {
            if (iCompanyService.companies() != null) {
                for (Company company : iCompanyService.companies()) {
                    if (company.getContacts() != null) {
                        for (Contact contact : company.getContacts()) {
                            helper.setTo(contact.getContEmail());
                            helper.setText(noti.getNotiDescription(), true);
                            helper.setSubject(noti.getNotiSubject());
                            mailSender.send(mimeMessage);
                            LOGGER.warn("Notification sent to " + contact.getContEmail());
                        }
                    }
                }
            }
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    //@Scheduled(cron= "0/30 * * * * ?" , zone = TIME_ZONE)    //@Scheduled(cron = "0 0 0 * * *")
    public void configureManualNotificationsForAllContacts(Noti noti) {
        Noti notiCreated = iNotiRepo.save(noti);
        if (notiCreated != null) {
            try {
                if (iCompanyService.companies() != null) {
                    for (Company company : iCompanyService.companies()) {
                        if (company.getContacts() != null) {
                            for (Contact contact : company.getContacts()) {
                                helper.setTo(noti.getNotiEmailDestination());
                                helper.setText(noti.getNotiDescription(), true);
                                helper.setSubject(noti.getNotiSubject());
                                mailSender.send(mimeMessage);
                                LOGGER.warn("Sending manual notifications to contacts");
                            }
                        }
                    }
                }
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
        }


    }

    @Override
    public void configureManualNotificationsForAllContacts() {
        LOGGER.info("Sending automatic notifications to contacts");
    }

    @Override
    public void sendAutomaticNotificationsToContacts() {

    }


}
