package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Academicstudy;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IAcademicstudyRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
class AcademicstudyServiceTest {

    @Mock
    private IAcademicstudyRepo iAcademicstudyRepo;

    @InjectMocks
    private AcademicstudyService academicstudyService;

    @BeforeEach
    void setUp() {
        System.out.println("----------- Init -----------");
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addAcademicstudy() {
    }

    @Test
    void updateAcademicstudy() {
    }

    @Test
    void searchAcademicstudy() {

        Academicstudy academicstudy1 = new Academicstudy();
        academicstudy1.setAcadStudId(1);
        academicstudy1.setAcadStudInsti("EDI - SUPRESS");
        academicstudy1.setAcadStudStatus("TERMINADO");

        when(iAcademicstudyRepo.getById(1L)).thenReturn(academicstudy1);

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
