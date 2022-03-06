package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Contact;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IContactController {
	
	/**
	 * Allow add new contact in the system.
	 * 
	 * @param contact contact to add.
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */

    ResponseEntity<Contact> addContact(Contact contact);

    ResponseEntity<Contact> updateContact(long contId, Contact contact);

    /**
	 * Allows to obtain a contact through you id.
	 * 
	 * @param id to search contact
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<Contact> getContact(long contId);

    /**
	 * Allows delete a contact through you id
	 * 
	 * @param contId id of the contact that you want to delete
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<HttpStatus> deleteContact(long contId);

    /**
	 * Allows to obtain the list of contacts saved in the database.
	 * 
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<List<Contact>> getContacts();
}
