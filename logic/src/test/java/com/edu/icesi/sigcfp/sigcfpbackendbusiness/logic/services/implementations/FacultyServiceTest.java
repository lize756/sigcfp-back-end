package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Faculty;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IFacultyRepo;
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
class FacultyServiceTest {


    final static long FACU_ID = 6974;

    @MockBean
    IFacultyRepo iFacultyRepo;
    @Autowired
    FacultyService facultyService;

    @BeforeAll
    static void init() {
        System.out.println("|| ---- FacultyServiceTest Started ---- ||");
    }

    @AfterAll
    static void finish() {
        System.out.println("|| ---- FacultyServiceTest Finished ---- ||");
    }

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

        assertThat(facultyService.addFaculty(faculty))
                .isNotNull()
                .isEqualTo(faculty);
        assertEquals("Ciencias", facultyService.addFaculty(faculty).getFacuName());

    }

    @Test
    void updateFaculty() {

        Faculty faculty = new Faculty();
        faculty.setFacuId(FACU_ID);
        faculty.setFacuName("Ciencias");

        Mockito.when(iFacultyRepo.getById(FACU_ID)).thenReturn(faculty);

        faculty.setFacuName("Idiomas");
        Mockito.when(iFacultyRepo.save(faculty)).thenReturn(faculty);

        assertThat(facultyService.updateFaculty(faculty))
                .isNotNull()
                .isEqualTo(faculty);
        assertEquals("Idiomas", facultyService.updateFaculty(faculty).getFacuName());

    }

    @Test
    void searchFaculty() {

        Faculty faculty = new Faculty();
        faculty.setFacuId(FACU_ID);
        faculty.setFacuName("Ciencias Naturales");

        Mockito.when(iFacultyRepo.existsById(FACU_ID)).thenReturn(true);
        Mockito.when(iFacultyRepo.getById(FACU_ID)).thenReturn(faculty);

        assertThat(facultyService.searchFaculty(FACU_ID))
                .isNotNull()
                .isEqualTo(faculty);
        assertEquals("Ciencias Naturales", facultyService.searchFaculty(FACU_ID).getFacuName());

    }

    @Test
    void deleteFaculty() {

        Faculty faculty = new Faculty();
        faculty.setFacuId(FACU_ID);
        faculty.setFacuName("Ciencias Naturales");

        Mockito.when(iFacultyRepo.getById(FACU_ID)).thenReturn(faculty);
        Mockito.when(iFacultyRepo.existsById(FACU_ID)).thenReturn(false);

        assertNull(facultyService.searchFaculty(FACU_ID));


    }

    @Test
    void faculties() {

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

        assertThat(facultyService.faculties())
                .isNotNull()
                .isEqualTo(facultyList);
        assertFalse(facultyList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }


}
