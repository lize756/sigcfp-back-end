package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Faculty;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICareerRepo;
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
class CareerControllerTest {

    final static long CARE_ID = 3275;
    TestRestTemplate restTemplate = new TestRestTemplate();
    @LocalServerPort
    private int port;
    @MockBean
    private ICareerRepo iCareerRepo;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCareer() {

        Career career = new Career();
        career.setCareId(CARE_ID);
        career.setCareName("Ing. de Sistemas");
        career.setCareDescription("Esta es la descripción de Ing. de Sistemas");
        Faculty faculty = new Faculty();
        faculty.setFacuId(342);
        faculty.setFacuName("TIC");
        career.setFaculty(faculty);

        Mockito.when(iCareerRepo.save(career)).thenReturn(career);

        // When
        ResponseEntity<Career> careerResponseEntity = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/careers/add", career, Career.class
        );

        // /then
        assertEquals(HttpStatus.CREATED, careerResponseEntity.getStatusCode());

    }

    @Test
    void updateCareer() {

        Career career = new Career();
        career.setCareId(CARE_ID);
        career.setCareName("Ing. de Sistemas");
        career.setCareDescription("Esta es la descripción de Ing. de Sistemas");
        Faculty faculty = new Faculty();
        faculty.setFacuId(342);
        faculty.setFacuName("TIC");
        career.setFaculty(faculty);

        Mockito.when(iCareerRepo.getById(CARE_ID)).thenReturn(career);

        // When
        career.setCareName("Biología");
        Mockito.when(iCareerRepo.save(career)).thenReturn(career);

        // then
        this.restTemplate.put(
                "http://localhost:" + port + "/careers/update/" + CARE_ID, career
        );


    }

    @Test
    void getCareer() {

        Career career = new Career();
        career.setCareId(CARE_ID);
        career.setCareName("Ing. de Sistemas");
        career.setCareDescription("Esta es la descripción de Ing. de Sistemas");
        Faculty faculty = new Faculty();
        faculty.setFacuId(342);
        faculty.setFacuName("TIC");
        career.setFaculty(faculty);

        Mockito.when(iCareerRepo.existsById(CARE_ID)).thenReturn(true);
        Mockito.when(iCareerRepo.getById(CARE_ID)).thenReturn(career);

        // when
        ResponseEntity<Career> careerResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/careers/" + CARE_ID, Career.class);

        // then
        assertThat(careerResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void deleteCareer() {

        Career career = new Career();
        career.setCareId(CARE_ID);
        career.setCareName("Ing. de Sistemas");
        career.setCareDescription("Esta es la descripción de Ing. de Sistemas");
        Faculty faculty = new Faculty();
        faculty.setFacuId(342);
        faculty.setFacuName("TIC");
        career.setFaculty(faculty);

        Mockito.when(iCareerRepo.getById(CARE_ID)).thenReturn(career);
        Mockito.when(iCareerRepo.existsById(CARE_ID)).thenReturn(false);

        // when
        restTemplate.delete("http://localhost:" + port + "/careers/" + CARE_ID);
        ResponseEntity<Career> careerResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/careers/" + CARE_ID, Career.class);

        // then
        assertThat(careerResponseEntity.getStatusCode()).isNotEqualTo(HttpStatus.NO_CONTENT);


    }

    @Test
    void getCareers() {

        Career career1 = new Career();
        career1.setCareId(CARE_ID);
        career1.setCareName("Ing. de Sistemas");
        career1.setCareDescription("Esta es la descripción de Ing. de Sistemas");
        Faculty faculty = new Faculty();
        faculty.setFacuId(342);
        faculty.setFacuName("TIC");
        career1.setFaculty(faculty);

        Career career2 = new Career();
        career2.setCareId(CARE_ID);
        career2.setCareName("Ing. de Sistemas");
        career2.setCareDescription("Esta es la descripción de Ing. de Sistemas");
        career2.setFaculty(faculty);

        Career career3 = new Career();
        career3.setCareId(CARE_ID);
        career3.setCareName("Ing. de Sistemas");
        career3.setCareDescription("Esta es la descripción de Ing. de Sistemas");
        career3.setFaculty(faculty);

        List<Career> careerList = new ArrayList<>();
        careerList.add(career1);
        careerList.add(career2);
        careerList.add(career3);

        Mockito.when(iCareerRepo.findAll()).thenReturn(careerList);

        // when
        ResponseEntity<Career[]> careerResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/careers", Career[].class);

        // then
        assertThat(careerResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(Objects.requireNonNull(careerResponseEntity.getBody())[0].getCareName())
                .isNotNull();


    }
}
