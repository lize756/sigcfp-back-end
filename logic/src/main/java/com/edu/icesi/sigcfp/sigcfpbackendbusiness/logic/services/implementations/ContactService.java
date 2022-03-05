package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Contact;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IContactService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ContactService implements IContactService {

    IContactRepo iContactRepo;

    @Autowired
    public ContactService(IContactRepo iContactRepo) {
        this.iContactRepo = iContactRepo;
    }

    @Override
    @Transactional
    public Contact addContact(Contact contact) {
        if (!iContactRepo.existsById(contact.getContId())) {
            return iContactRepo.save(contact);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Contact updateContact(long contId, Contact contact) {
        if (iContactRepo.existsById(contId)) {
            return iContactRepo.save(contact);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Contact searchContact(long contId) {
        if (iContactRepo.existsById(contId)) {
            return iContactRepo.getById(contId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Contact deleteContact(long contId) {
        Contact contactToDelete = null;
        if (iContactRepo.existsById(contId)) {
            contactToDelete = iContactRepo.findById(contId).get();
            iContactRepo.delete(iContactRepo.getById(contId));
        } else {
            return null;
        }
        return contactToDelete;
    }

    @Override
    @Transactional
    public List<Contact> contacts() {
        return iContactRepo.findAll();
    }
}
