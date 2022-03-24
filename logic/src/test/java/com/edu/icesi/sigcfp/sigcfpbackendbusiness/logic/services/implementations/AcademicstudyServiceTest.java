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
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        System.out.println("|| ---- CityServiceTest Started ---- ||");
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

        academicstudy.setAcadStudStatus("CANCELADO");
        academicstudy.setAcadStudLevel("TÉCNICO");
        Mockito.when(iAcademicstudyRepo.save(academicstudy)).thenReturn(academicstudy);

        assertThat(academicstudyService.addAcademicstudy(academicstudy))
s                .isEqualTo(academicstudy);
        assertEquals("CANCELADO", academicstudyService.updateAcademicstudy(academicstudy).getAcadStudLevel());
        assertEquals("TÉCNICO", academicstudyService.updateAcademicstudy(academicstudy).getAcadStudLevel());
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

        Academicstudy byId = academicstudyService.searchAcademicstudy(1);

        assertEquals("EDI - SUPRESS", byId.getAcadStudInsti());
        assertEquals("TERMINADO", byId.getAcadStudStatus());
    }

    @Test
    void deleteAcademicstudy() {
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

        when(iAcademicstudyRepo.findAll()).thenReturn(academicstudyList);

        //test
        List<Academicstudy> academicstudies = academicstudyService.academicstudies();

        assertEquals(3, academicstudies.size());
        verify(iAcademicstudyRepo, times(1)).findAll();
    }

    @AfterEach
    void tearDown() {
        System.out.println("----------- Finish -----------");
    }


}
