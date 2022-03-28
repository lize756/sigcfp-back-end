package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Contact;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICareerRepo;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IContactRepo;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ContactServiceTest {

    final static long CONT_ID = 2547;

    @MockBean
    IContactRepo iContactRepo;
    @Autowired
    ContactService contactService;

    @BeforeAll
    static void init(){
        System.out.println("|| ---- ContactServiceTest Started ---- ||");
    }

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

        assertThat(contactService.addContact(contact))
                .isNotNull()
                .isEqualTo(contact);
        assertEquals("Carlos Godoy", contactService.addContact(contact).getContName());
        assertEquals("3215874569", contactService.addContact(contact).getContPhone());

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

        assertThat(contactService.addContact(contact))
                .isNotNull()
                .isEqualTo(contact);
        assertEquals("Auxiliar Talento Humano", contactService.addContact(contact).getContPosition());

    }

    @Test
    void searchContact() {

        Contact contact = new Contact();
        contact.setContId(CONT_ID);
        contact.setContName("Carlos Godoy");
        contact.setContPosition("Auxiliar Gestión Humana");
        contact.setContEmail("cgodoy@mail.com");
        contact.setContPhone("3215874569");

        Mockito.when(iContactRepo.existsById(CONT_ID)).thenReturn(true);
        Mockito.when(iContactRepo.getById(CONT_ID)).thenReturn(contact);

        assertThat(contactService.searchContact(CONT_ID))
                .isNotNull()
                .isEqualTo(contact);
        assertEquals("cgodoy@mail.com", contactService.searchContact(CONT_ID).getContEmail());

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

        assertNull(contactService.searchContact(CONT_ID));

    }

    @Test
    void contacts() {

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

        assertThat(contactService.contacts())
                .isNotNull()
                .isEqualTo(contactList);
        assertFalse(contactList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- ContactServiceTest Finished ---- ||");
    }


}
