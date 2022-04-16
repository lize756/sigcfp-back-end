package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.controllers;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services.EmailBody;
import org.springframework.http.ResponseEntity;

public interface IEmailNotificationController {

    public ResponseEntity<?> sendSimpleEmail(EmailBody emailBody);
    public void sendSimpleEmail();
    public void sendEmailWithAttachment(EmailBody emailBody);
    public void sendEmailWithAttachment();

}
