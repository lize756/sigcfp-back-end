package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Faculty;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IFacultyRepo;
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
class FacultyControllerTest {

    final static long FACU_ID = 6974;

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    @MockBean
    private IFacultyRepo iFacultyRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addFaculty() {

        Faculty faculty = new Faculty();
        faculty.setFacuId(FACU_ID);
        faculty.setFacuName("Ciencias");

        Mockito.when(iFacultyRepo.save(faculty)).thenReturn(faculty);

        // When
        ResponseEntity<Faculty> facultyResponseEntity = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/faculties/add", faculty, Faculty.class
        );

        // /then
        assertEquals(HttpStatus.CREATED, facultyResponseEntity.getStatusCode());


    }

    @Test
    void updateFaculty() {

        Faculty faculty = new Faculty();
        faculty.setFacuId(FACU_ID);
        faculty.setFacuName("Ciencias");

        Mockito.when(iFacultyRepo.getById(FACU_ID)).thenReturn(faculty);

        faculty.setFacuName("Idiomas");
        Mockito.when(iFacultyRepo.save(faculty)).thenReturn(faculty);


        // then
        this.restTemplate.put(
                "http://localhost:" + port + "/faculties/update/"+FACU_ID, faculty
        );

    }

    @Test
    void getFaculty() {

        Faculty faculty = new Faculty();
        faculty.setFacuId(FACU_ID);
        faculty.setFacuName("Ciencias Naturales");

        Mockito.when(iFacultyRepo.existsById(FACU_ID)).thenReturn(true);
        Mockito.when(iFacultyRepo.getById(FACU_ID)).thenReturn(faculty);

        // when
        ResponseEntity<Faculty> facultyResponseEntity = restTemplate.getForEntity("http://localhost:" + port +"/faculties/"+FACU_ID, Faculty.class);

        // then
        assertThat(facultyResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void deleteContact() {

        Faculty faculty = new Faculty();
        faculty.setFacuId(FACU_ID);
        faculty.setFacuName("Ciencias Naturales");

        Mockito.when(iFacultyRepo.getById(FACU_ID)).thenReturn(faculty);
        Mockito.when(iFacultyRepo.existsById(FACU_ID)).thenReturn(false);

        // when
        restTemplate.delete("http://localhost:" + port +"/faculties/"+FACU_ID);
        ResponseEntity<Faculty> facultyResponseEntity = restTemplate.getForEntity("http://localhost:" + port +"/faculties/"+FACU_ID, Faculty.class);

        // then
        assertThat(facultyResponseEntity.getStatusCode()).isNotEqualTo(HttpStatus.NO_CONTENT);


    }

    @Test
    void getFaculties() {

        Faculty faculty1 = new Faculty();
        faculty1.setFacuId(58);
        faculty1.setFacuName("Ciencias Naturales");

        Faculty faculty2 = new Faculty();
        faculty2.setFacuId(5344);
        faculty2.setFacuName("Ciencias Naturales");

        List<Faculty> facultyList = new ArrayList<>();
        facultyList.add(faculty1);
        facultyList.add(faculty2);

        Mockito.when(iFacultyRepo.findAll()).thenReturn(facultyList);

        // when
        ResponseEntity<Faculty[]> facultyResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/faculties", Faculty[].class);

        // then
        assertThat(facultyResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(Objects.requireNonNull(facultyResponseEntity.getBody())[0].getFacuName())
                .isNotNull();


    }
}
