package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.controllers;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services.EmailBody;
import org.springframework.http.ResponseEntity;

public interface IEmailNotificationController {

    public ResponseEntity<?> sendSimpleEmail(EmailBody emailBody);

    public void sendEmailWithAttachment(EmailBody emailBody);

    public void sendEmailWithAttachment(EmailBody emailBody, String attachmentPath);

    public void sendStartInternPeriodNotification();

    public void sendEndInternPeriodNotification();

    public ResponseEntity<?> sendNotificationsToContacts();
}
