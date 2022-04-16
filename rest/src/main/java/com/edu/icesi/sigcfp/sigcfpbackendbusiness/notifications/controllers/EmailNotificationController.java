package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.controllers;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services.EmailBody;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services.EmailNotificationService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services.IEmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emailNotifications")
@CrossOrigin(origins = "*")
public class EmailNotificationController implements IEmailNotificationController{

    @Autowired private IEmailNotificationService iEmailNotificationService;


    @Override
    @PostMapping("/sendSimpleEmail")
    public ResponseEntity<?> sendSimpleEmail(@RequestBody EmailBody emailBody) {
        iEmailNotificationService.sendSimpleEmail(emailBody);
        return new ResponseEntity<>("Email enviado exitosamente", HttpStatus.OK);
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
