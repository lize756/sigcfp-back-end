package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Contact;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IContactService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.IContactController;

@RestController()
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "*")
//@PreAuthorize("")
public class ContactController implements IContactController {

	private IContactService iContactService;

	@Autowired
	public ContactController(IContactService iContactService) {
		this.iContactService = iContactService;
	}

	@Override
	@PostMapping("/add")
	public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
		try {
			Contact _contact = iContactService.addContact(contact);
			return new ResponseEntity<Contact>(_contact, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@PutMapping("/update/{contId}")
	public ResponseEntity<Contact> updateContact(@PathVariable("contId") long contId, @RequestBody Contact contact) {
		Optional<Contact> contactOpt = Optional.of(iContactService.searchContact(contId));
		if (contactOpt.isPresent()) {
			Contact _contact = contactOpt.get();
			return new ResponseEntity<>(iContactService.updateContact(_contact), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@GetMapping("/{contId}")
	public ResponseEntity<Contact> getContact(@PathVariable("contId") long contId) {
		Optional<Contact> contactOpt = Optional.of(iContactService.searchContact(contId));
		if (contactOpt.isPresent()) {
			return new ResponseEntity<>(contactOpt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@DeleteMapping("/{contId}")
	public ResponseEntity<HttpStatus> deleteContact(@PathVariable("contId") long contId) {
		try {
			iContactService.deleteContact(contId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping()
	public ResponseEntity<List<Contact>> getContacts() {
		try {
			List<Contact> contacts = iContactService.contacts();
			if (contacts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(contacts, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
