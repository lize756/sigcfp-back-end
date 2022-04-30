package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services;

public interface IEmailNotificationManualService {

    public void sendSimpleEmail();

    public void sendStartInternPeriodNotification();

    public void sendEndInternPeriodNotification();

    public void sendStartNotificationsToContacts();

    public void sendEndNotificationsToContacts();





}
