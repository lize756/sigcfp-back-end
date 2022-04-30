package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.controllers;


import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Noti;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services.INotiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/notis")
public class NotiController implements INotiController{

    @Autowired INotiService iNotiService;

    @Override
    public  ResponseEntity<Noti> addNoti(@RequestBody Noti noti) {
        return new ResponseEntity<>(iNotiService.addNoti(noti), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Noti> updateNoti(@PathVariable long notiId, @RequestBody Noti noti) {
        return new ResponseEntity<>(iNotiService.updateNoti(noti), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Noti> getNoti(@PathVariable long notiId) {
        return new ResponseEntity<>(iNotiService.searchNoti(notiId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteNoti(long notiId) {
        iNotiService.deleteNoti(notiId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Noti>> getNotis() {
        return new ResponseEntity<>(iNotiService.notis(), HttpStatus.OK);
    }

    @Override
    @PutMapping("/manualStartNotificationToContacts")
    public ResponseEntity<?> sendManualStartNotificationsToContacts() {
       iNotiService.sendManualStartNotificationsToContacts();
        return new ResponseEntity<>("Correo de apertura enviado a los contacto exitosamente",HttpStatus.OK);
    }

    @Override
    @PutMapping("/manualEndNotificationToContacts")
    public ResponseEntity<?> sendManualEndNotificationsToContacts() {
        iNotiService.sendManualEndNotificationsToContacts();
        return new ResponseEntity<>("Correo de cierre enviado a los contacto exitosamente",HttpStatus.OK);
    }
}
