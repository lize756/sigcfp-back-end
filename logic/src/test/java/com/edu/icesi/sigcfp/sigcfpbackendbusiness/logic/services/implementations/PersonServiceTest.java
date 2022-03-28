package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Person;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICareerRepo;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IPersonRepo;
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
class PersonServiceTest {

    final static long PERS_ID = 3275;

    @MockBean
    IPersonRepo iPersonRepo;
    @Autowired
    PersonService personService;

    @BeforeAll
    static void init() {
        System.out.println("|| ---- PersonServiceTest Started ---- ||");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addPerson() {

        Person person = new Person();
        person.setPersId(PERS_ID);
        person.setPersFirstName("Juan");
        person.setPersLastName("López");
        person.setPersEmail("jlopez@mail.com");

        Mockito.when(iPersonRepo.save(person)).thenReturn(person);

        assertThat(personService.addPerson(person))
                .isNotNull()
                .isEqualTo(person);
        assertEquals("Juan", personService.addPerson(person).getPersFirstName());

    }

    @Test
    void updatePerson() {

        Person person = new Person();
        person.setPersId(PERS_ID);
        person.setPersFirstName("Juan");
        person.setPersLastName("López");
        person.setPersEmail("jlopez@mail.com");

        Mockito.when(iPersonRepo.getById(PERS_ID)).thenReturn(person);

        person.setPersFirstName("Tom");
        Mockito.when(iPersonRepo.save(person)).thenReturn(person);

        assertThat(personService.updatePerson(person))
                .isNotNull()
                .isEqualTo(person);
        assertEquals("Tom", personService.updatePerson(person).getPersFirstName());

    }

    @Test
    void searchPerson() {

        Person person = new Person();
        person.setPersId(PERS_ID);
        person.setPersFirstName("Juan");
        person.setPersLastName("López");
        person.setPersEmail("jlopez@mail.com");

        Mockito.when(iPersonRepo.existsById(PERS_ID)).thenReturn(true);
        Mockito.when(iPersonRepo.getById(PERS_ID)).thenReturn(person);

        assertThat(personService.searchPerson(PERS_ID))
                .isNotNull()
                .isEqualTo(person);
        assertEquals("López", personService.searchPerson(PERS_ID).getPersLastName());

    }

    @Test
    void deletePerson() {

        Person person = new Person();
        person.setPersId(PERS_ID);
        person.setPersFirstName("Juan");
        person.setPersLastName("López");
        person.setPersEmail("jlopez@mail.com");

        Mockito.when(iPersonRepo.getById(PERS_ID)).thenReturn(person);
        Mockito.when(iPersonRepo.existsById(PERS_ID)).thenReturn(false);

        assertNull(personService.searchPerson(PERS_ID));

    }

    @Test
    void persons() {

        Person person1 = new Person();
        person1.setPersId(PERS_ID);
        person1.setPersFirstName("Juan");
        person1.setPersLastName("López");
        person1.setPersEmail("jlopez@mail.com");

        Person person2 = new Person();
        person2.setPersId(PERS_ID);
        person2.setPersFirstName("Juan");
        person2.setPersLastName("López");
        person2.setPersEmail("jlopez@mail.com");

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        Mockito.when(iPersonRepo.findAll()).thenReturn(personList);

        assertThat(personService.persons())
                .isNotNull()
                .isEqualTo(personList);
        assertFalse(personList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- PersonServiceTest Finished ---- ||");
    }



}
