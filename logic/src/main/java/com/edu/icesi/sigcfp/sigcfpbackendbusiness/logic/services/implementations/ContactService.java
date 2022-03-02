package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Contact;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IContactService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements IContactService {

    IContactRepo iContactRepo;

    @Autowired
    public ContactService(IContactRepo iContactRepo) {
        this.iContactRepo = iContactRepo;
    }

    @Override
    public Contact addContact(Contact contact) {
        return null;
    }

    @Override
    public Contact updateContact(Contact contact) {
        return null;
    }

    @Override
    public Contact searchContact(long contId) {
        return null;
    }

    @Override
    public Contact deleteContact(long contId) {
        return null;
    }

    @Override
    public List<Contact> contacts() {
        return null;
    }
}
