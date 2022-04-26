package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.controllers;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services.EmailBody;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services.IEmailNotificationManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emailNotifications")
@CrossOrigin(origins = "*")
public class EmailNotificationController implements IEmailNotificationController{

    @Autowired private IEmailNotificationManualService iEmailNotificationManualService;


    @Override
    @PostMapping("/sendSimpleEmail")
    public ResponseEntity<?> sendSimpleEmail() {
        iEmailNotificationManualService.sendSimpleEmail();
        return new ResponseEntity<>("Email enviado exitosamente", HttpStatus.OK);
    }




    @Override
    public void sendStartInternPeriodNotification() {

    }

    @Override
    public void sendEndInternPeriodNotification() {

    }

    @Override
    @GetMapping("/sendNotificationsToContacts")
    public ResponseEntity<?> sendNotificationsToContacts() {
        iEmailNotificationManualService.sendEndNotificationsToContacts();
        return new ResponseEntity<>("Los correos se han enviado a los contactos correctamente", HttpStatus.OK);
    }

}
