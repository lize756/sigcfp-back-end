package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Person;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IPersonRepo;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerTest {

    final static long PERS_ID = 3275;
    TestRestTemplate restTemplate = new TestRestTemplate();
    @LocalServerPort
    private int port;
    @MockBean
    private IPersonRepo iPersonRepo;

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

        // When
        ResponseEntity<Person> personResponseEntity = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/persons/add", person, Person.class
        );

        // /then
        assertEquals(HttpStatus.CREATED, personResponseEntity.getStatusCode());


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

        // then
        this.restTemplate.put(
                "http://localhost:" + port + "/persons/update/" + PERS_ID, person
        );

    }

    @Test
    void getPerson() {

        Person person = new Person();
        person.setPersId(PERS_ID);
        person.setPersFirstName("Juan");
        person.setPersLastName("López");
        person.setPersEmail("jlopez@mail.com");

        Mockito.when(iPersonRepo.existsById(PERS_ID)).thenReturn(true);
        Mockito.when(iPersonRepo.getById(PERS_ID)).thenReturn(person);

        // when
        ResponseEntity<Person> personResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/persons/" + PERS_ID, Person.class);

        // then
        assertThat(personResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);


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

        // when
        restTemplate.delete("http://localhost:" + port + "/persons/" + PERS_ID);
        ResponseEntity<Person> personResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/persons/" + PERS_ID, Person.class);

        // then
        assertThat(personResponseEntity.getStatusCode()).isNotEqualTo(HttpStatus.NO_CONTENT);


    }

    @Test
    void getPersons() {

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

        // when
        ResponseEntity<Person[]> personResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/persons", Person[].class);

        // then
        assertThat(personResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(Objects.requireNonNull(personResponseEntity.getBody())[0].getPersFirstName())
                .isNotNull();


    }
}
