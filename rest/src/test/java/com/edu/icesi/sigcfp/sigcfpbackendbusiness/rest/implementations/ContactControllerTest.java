package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Contact;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICityRepo;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IContactRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContactControllerTest {

    final static long CONT_ID = 2547;

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    @MockBean
    private IContactRepo iContactRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addContact() {

        Contact contact = new Contact();
        contact.setContId(CONT_ID);
        contact.setContName("Carlos Godoy");
        contact.setContPosition("Auxiliar Gestión Humana");
        contact.setContEmail("cgodoy@mail.com");
        contact.setContPhone("3215874569");

        Mockito.when(iContactRepo.save(contact)).thenReturn(contact);

        // When
        ResponseEntity<Contact> contactResponseEntity = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/contacts/add", contact, Contact.class
        );

        // /then
        assertEquals(HttpStatus.CREATED, contactResponseEntity.getStatusCode());

    }

    @Test
    void updateContact() {

        Contact contact = new Contact();
        contact.setContId(CONT_ID);
        contact.setContName("Carlos Godoy");
        contact.setContPosition("Auxiliar Gestión Humana");
        contact.setContEmail("cgodoy@mail.com");
        contact.setContPhone("3215874569");

        Mockito.when(iContactRepo.getById(CONT_ID)).thenReturn(contact);

        contact.setContPosition("Auxiliar Talento Humano");
        Mockito.when(iContactRepo.save(contact)).thenReturn(contact);

        // then
        this.restTemplate.put(
                "http://localhost:" + port + "/contacts/update/"+CONT_ID, contact
        );


    }

    @Test
    void getContact() {

        Contact contact = new Contact();
        contact.setContId(CONT_ID);
        contact.setContName("Carlos Godoy");
        contact.setContPosition("Auxiliar Gestión Humana");
        contact.setContEmail("cgodoy@mail.com");
        contact.setContPhone("3215874569");

        Mockito.when(iContactRepo.existsById(CONT_ID)).thenReturn(true);
        Mockito.when(iContactRepo.getById(CONT_ID)).thenReturn(contact);

        // when
        ResponseEntity<Contact> contactResponseEntity = restTemplate.getForEntity("http://localhost:" + port +"/contacts/"+CONT_ID, Contact.class);

        // then
        assertThat(contactResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);


    }

    @Test
    void deleteContact() {

        Contact contact = new Contact();
        contact.setContId(CONT_ID);
        contact.setContName("Carlos Godoy");
        contact.setContPosition("Auxiliar Gestión Humana");
        contact.setContEmail("cgodoy@mail.com");
        contact.setContPhone("3215874569");

        Mockito.when(iContactRepo.getById(CONT_ID)).thenReturn(contact);
        Mockito.when(iContactRepo.existsById(CONT_ID)).thenReturn(false);

        // when
        restTemplate.delete("http://localhost:" + port +"/contacts/"+CONT_ID);
        ResponseEntity<Contact> contactResponseEntity = restTemplate.getForEntity("http://localhost:" + port +"/contacts/"+CONT_ID, Contact.class);

        // then
        assertThat(contactResponseEntity.getStatusCode()).isNotEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void getContacts() {

        Contact contact1 = new Contact();
        contact1.setContId(CONT_ID);
        contact1.setContName("Carlos Godoy");
        contact1.setContPosition("Auxiliar Gestión Humana");
        contact1.setContEmail("cgodoy@mail.com");
        contact1.setContPhone("3215874569");

        Contact contact2 = new Contact();
        contact2.setContId(CONT_ID);
        contact2.setContName("Carlos Godoy");
        contact2.setContPosition("Auxiliar Gestión Humana");
        contact2.setContEmail("cgodoy@mail.com");
        contact2.setContPhone("3215874569");

        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact1);
        contactList.add(contact2);

        Mockito.when(iContactRepo.findAll()).thenReturn(contactList);

        // when
        ResponseEntity<Contact[]> contactResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/contacts", Contact[].class);

        // then
        assertThat(contactResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(Objects.requireNonNull(contactResponseEntity.getBody())[0].getContName())
                .isNotNull();



    }
}
