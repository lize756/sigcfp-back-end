package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.controllers;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Noti;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INotiController {

    ResponseEntity<Noti> addNoti(Noti noti);

    ResponseEntity<Noti> updateNoti(long notiId, Noti noti);

    ResponseEntity<Noti> getNoti(long notiId);

    ResponseEntity<HttpStatus> deleteNoti(long notiId);

    ResponseEntity<List<Noti>> getNotis();

    // ResponseEntity<?> sendManualStartNotificationsToContacts();
   // ResponseEntity<?> sendManualEndNotificationsToContacts();

     ResponseEntity<?> configureManualNotificationsForAllContacts(Noti noti);

    ResponseEntity<?> sendManualNotificationToOneContact(Noti noti, long contId);






}
