package com.edu.icesi.sigcfp.sigcfpbackendbusiness.notifications.services;

public interface IEmailNotificationService {

    public void sendSimpleEmail(EmailBody emailBody);
    public void sendEmailWithAttachment(EmailBody emailBody);

}
