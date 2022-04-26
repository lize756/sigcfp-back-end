package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.controllers;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services.EmailBody;
import org.springframework.http.ResponseEntity;

public interface IEmailNotificationController {

    public ResponseEntity<?> sendSimpleEmail();

    public void sendStartInternPeriodNotification();
    public void sendEndInternPeriodNotification();

    public ResponseEntity<?> sendNotificationsToContacts();
}
