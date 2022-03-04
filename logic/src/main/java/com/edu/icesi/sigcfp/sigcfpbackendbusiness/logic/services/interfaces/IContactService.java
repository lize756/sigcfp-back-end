package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Contact;

import java.util.List;

public interface IContactService {

    Contact addContact(Contact contact);

    Contact updateContact(Contact contact);

    Contact searchContact(long contId);

    Contact deleteContact(long contId);

    List<Contact> contacts();
}
