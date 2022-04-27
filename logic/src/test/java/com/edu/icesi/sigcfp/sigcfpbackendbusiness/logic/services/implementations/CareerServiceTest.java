package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Faculty;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICareerRepo;
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
class CareerServiceTest {

    final static long CARE_ID = 3275;

    @MockBean
    ICareerRepo iCareerRepo;
    @Autowired
    CareerService careerService;

    @BeforeAll
    static void init() {
        System.out.println("|| ---- CareerServiceTest Started ---- ||");
    }

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

        assertThat(careerService.addCareer(career))
                .isNotNull()
                .isEqualTo(career);
        assertEquals("Ing. de Sistemas", careerService.addCareer(career).getCareName());
        assertEquals("TIC", careerService.addCareer(career).getFaculty().getFacuName());

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

        career.setCareName("Biología");
        Mockito.when(iCareerRepo.save(career)).thenReturn(career);

        assertThat(careerService.updateCareer(career))
                .isNotNull()
                .isEqualTo(career);
        assertEquals("Biología", careerService.updateCareer(career).getCareName());

    }

    @Test
    void searchCareer() {

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

        assertThat(careerService.searchCareer(CARE_ID))
                .isNotNull()
                .isEqualTo(career);
        assertEquals("Ing. de Sistemas", careerService.searchCareer(CARE_ID).getCareName());

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

        assertNull(careerService.searchCareer(CARE_ID));

    }

    @Test
    void careers() {

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

        assertThat(careerService.careers())
                .isNotNull()
                .isEqualTo(careerList);
        assertFalse(careerList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- CareerServiceTest Finished ---- ||");
    }

}
