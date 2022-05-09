package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Noti;

import java.util.List;

public interface INotiService {

    Noti addNoti(Noti noti);

    Noti updateNoti(Noti noti);

    Noti searchNoti(long notiId);

    Noti deleteNoti(long notiId);

    List<Noti> notis();

    public void sendManualStartNotificationsToContacts();

    public void sendManualEndNotificationsToContacts();

    public void configureManualNotificationsForAllContacts(Noti noti);

    void sendManualNotificationToOneContact(Noti noti, long contId);
}
