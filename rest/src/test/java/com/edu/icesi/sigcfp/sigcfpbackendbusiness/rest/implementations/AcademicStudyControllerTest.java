package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Academicstudy;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IAcademicstudyRepo;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICityRepo;
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
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AcademicStudyControllerTest {

    final static long ACAD_STUD_ID = 9658;

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    @MockBean
    private IAcademicstudyRepo iAcademicstudyRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addAcademicstudy() {

        // given
        Academicstudy academicstudy = new Academicstudy();
        academicstudy.setAcadStudId(ACAD_STUD_ID);
        academicstudy.setAcadStudLevel("PROFESIONAL");
        academicstudy.setAcadStudStartDate(new Date());
        academicstudy.setAcadStudEndDate(new Date());
        academicstudy.setAcadStudInsti("UnIcesi");
        academicstudy.setAcadStudStatus("TERMINADO");

        Mockito.when(iAcademicstudyRepo.save(academicstudy)).thenReturn(academicstudy);

        // When
        ResponseEntity<Academicstudy> academicstudyResponseEntity = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/academicStudies/add", academicstudy, Academicstudy.class
        );

        // /then
        assertEquals(HttpStatus.CREATED, academicstudyResponseEntity.getStatusCode());

    }

    @Test
    void updateAcademicstudy() {

        // given
        Academicstudy academicstudy = new Academicstudy();
        academicstudy.setAcadStudId(ACAD_STUD_ID);
        academicstudy.setAcadStudLevel("PROFESIONAL");
        academicstudy.setAcadStudStartDate(new Date());
        academicstudy.setAcadStudEndDate(new Date());
        academicstudy.setAcadStudInsti("UnIcesi");
        academicstudy.setAcadStudStatus("TERMINADO");

        // when
        when(iAcademicstudyRepo.save(academicstudy)).thenReturn(academicstudy);

        // then
        this.restTemplate.put(
                "http://localhost:" + port + "/academicStudies/update/"+ACAD_STUD_ID, academicstudy
        );

    }

    @Test
    void getAcademicstudy() {

        Academicstudy academicstudy = new Academicstudy();
        academicstudy.setAcadStudId(ACAD_STUD_ID);
        academicstudy.setAcadStudLevel("PROFESIONAL");
        academicstudy.setAcadStudStartDate(new Date());
        academicstudy.setAcadStudEndDate(new Date());
        academicstudy.setAcadStudInsti("UnIcesi");
        academicstudy.setAcadStudStatus("TERMINADO");

        Mockito.when(iAcademicstudyRepo.existsById(ACAD_STUD_ID)).thenReturn(true);
        Mockito.when(iAcademicstudyRepo.getById(ACAD_STUD_ID)).thenReturn(academicstudy);

        // when
        ResponseEntity<Academicstudy> academicstudyResponseEntity = restTemplate.getForEntity("http://localhost:" + port +"/academicStudies/"+ACAD_STUD_ID, Academicstudy.class);

        // then
        assertThat( academicstudyResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void deleteAcademicstudy() {

        Academicstudy academicstudy = new Academicstudy();
        academicstudy.setAcadStudId(ACAD_STUD_ID);
        academicstudy.setAcadStudInsti("USB");

        Mockito.when(iAcademicstudyRepo.getById(ACAD_STUD_ID)).thenReturn(academicstudy);
        Mockito.when(iAcademicstudyRepo.existsById(ACAD_STUD_ID)).thenReturn(false);

        // when
        restTemplate.delete("http://localhost:" + port +"/academicStudies/"+ACAD_STUD_ID);
        ResponseEntity<Academicstudy> academicstudyResponseEntity = restTemplate.getForEntity("http://localhost:" + port +"/academicStudies/"+ACAD_STUD_ID, Academicstudy.class);

        // then
        assertThat(academicstudyResponseEntity.getStatusCode()).isNotEqualTo(HttpStatus.NO_CONTENT);


    }

    @Test
    void getAcademicstudies() {

        List<Academicstudy> academicstudyList = new ArrayList<>();

        Academicstudy academicstudy1 = new Academicstudy();
        academicstudy1.setAcadStudId(1);
        academicstudy1.setAcadStudInsti("EDI - SUPRESS");
        academicstudy1.setAcadStudStatus("TERMINADO");

        Academicstudy academicstudy2 = new Academicstudy();
        academicstudy2.setAcadStudId(2);
        academicstudy2.setAcadStudInsti("EDI - CARLIN");
        academicstudy2.setAcadStudStatus("COMENZADO");

        Academicstudy academicstudy3 = new Academicstudy();
        academicstudy3.setAcadStudId(3);
        academicstudy3.setAcadStudInsti("EDI - CARGAM");
        academicstudy3.setAcadStudStatus("EN PROCESO");

        academicstudyList.add(academicstudy1);
        academicstudyList.add(academicstudy2);
        academicstudyList.add(academicstudy3);

        Mockito.when(iAcademicstudyRepo.findAll()).thenReturn(academicstudyList);

        // when
        ResponseEntity<Academicstudy[]> academicStudiesResponseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/academicStudies", Academicstudy[].class);

        // then
        assertThat(academicStudiesResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(Objects.requireNonNull(academicStudiesResponseEntity.getBody())[0].getAcadStudInsti())
                .isNotNull();


    }
}
