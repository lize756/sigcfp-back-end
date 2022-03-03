package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Contact;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IContactController {

    ResponseEntity<Contact> addContact(Contact contact);

    ResponseEntity<Contact> updateContact( long contId, Contact contact);

    ResponseEntity<Contact> getContact(long compId);

    ResponseEntity<Contact> deleteContact(long contId);

    ResponseEntity<List<Contact>> getContacts();
}
