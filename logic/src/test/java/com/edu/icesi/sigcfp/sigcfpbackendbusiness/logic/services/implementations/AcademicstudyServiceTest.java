package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Academicstudy;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IAcademicstudyRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AcademicstudyServiceTest {

    final static long ACAD_STUD_ID = 9658;

    @MockBean
    private IAcademicstudyRepo iAcademicstudyRepo;
    @Autowired
    private AcademicstudyService academicstudyService;

    @BeforeAll
    static void init(){
        System.out.println("|| ---- AcademicstudyServiceTest Started ---- ||");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addAcademicstudy() {
        Academicstudy academicstudy = new Academicstudy();
        academicstudy.setAcadStudId(ACAD_STUD_ID);
        academicstudy.setAcadStudLevel("PROFESIONAL");
        academicstudy.setAcadStudStartDate(new Date());
        academicstudy.setAcadStudEndDate(new Date());
        academicstudy.setAcadStudInsti("UnIcesi");
        academicstudy.setAcadStudStatus("TERMINADO");

        Mockito.when(iAcademicstudyRepo.save(academicstudy)).thenReturn(academicstudy);

        assertThat(academicstudyService.addAcademicstudy(academicstudy))
                .isNotNull()
                .isEqualTo(academicstudy);
    }

    @Test
    void updateAcademicstudy() {

        Academicstudy academicstudy = new Academicstudy();
        academicstudy.setAcadStudId(ACAD_STUD_ID);
        academicstudy.setAcadStudLevel("PROFESIONAL");
        academicstudy.setAcadStudStartDate(new Date());
        academicstudy.setAcadStudEndDate(new Date());
        academicstudy.setAcadStudInsti("UnIcesi");
        academicstudy.setAcadStudStatus("TERMINADO");

        Mockito.when(iAcademicstudyRepo.getById(ACAD_STUD_ID)).thenReturn(academicstudy);

        academicstudy.setAcadStudInsti("ICESI");
        Mockito.when(iAcademicstudyRepo.save(academicstudy)).thenReturn(academicstudy);

        assertThat(academicstudyService.updateAcademicstudy(academicstudy))
                .isNotNull()
                .isEqualTo(academicstudy);
        assertEquals("ICESI", academicstudyService.updateAcademicstudy(academicstudy).getAcadStudInsti());
    }

    @Test
    void searchAcademicstudy() {

        Academicstudy academicstudy = new Academicstudy();
        academicstudy.setAcadStudId(ACAD_STUD_ID);
        academicstudy.setAcadStudLevel("PROFESIONAL");
        academicstudy.setAcadStudStartDate(new Date());
        academicstudy.setAcadStudEndDate(new Date());
        academicstudy.setAcadStudInsti("UnIcesi");
        academicstudy.setAcadStudStatus("TERMINADO");

        Mockito.when(iAcademicstudyRepo.existsById(ACAD_STUD_ID)).thenReturn(true);
        Mockito.when(iAcademicstudyRepo.getById(ACAD_STUD_ID)).thenReturn(academicstudy);

        assertThat(academicstudyService.searchAcademicstudy(ACAD_STUD_ID))
                .isNotNull()
                        .isEqualTo(academicstudy);
        assertEquals("UnIcesi", academicstudyService.searchAcademicstudy(ACAD_STUD_ID).getAcadStudInsti());
    }

    @Test
    void deleteAcademicstudy() {

        Academicstudy academicstudy = new Academicstudy();
        academicstudy.setAcadStudId(ACAD_STUD_ID);
        academicstudy.setAcadStudInsti("USB");

        Mockito.when(iAcademicstudyRepo.getById(ACAD_STUD_ID)).thenReturn(academicstudy);
        Mockito.when(iAcademicstudyRepo.existsById(ACAD_STUD_ID)).thenReturn(false);

        assertNull(academicstudyService.searchAcademicstudy(ACAD_STUD_ID));

    }

    @Test
    void academicstudies() {

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

        assertThat(academicstudyService.academicstudies())
                .isNotNull()
                .isEqualTo(academicstudyList);
        assertFalse(academicstudyList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- AcademicstudyServiceTest Finished ---- ||");
    }


}
