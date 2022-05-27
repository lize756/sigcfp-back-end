package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Curriculum;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICurriculumRepo;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CurriculumServiceTest {


    final static long CURR_ID = 7425;

    @MockBean
    ICurriculumRepo iCurriculumRepo;
    @Autowired
    CurriculumService curriculumService;

    @BeforeAll
    static void init() {
        System.out.println("|| ---- CurriculumServiceTest Started ---- ||");
    }

    @AfterAll
    static void finish() {
        System.out.println("|| ---- CurriculumServiceTest Finished ---- ||");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCurriculum() {

        Curriculum curriculum = new Curriculum();
        curriculum.setCurrId(CURR_ID);
        curriculum.setCurrSalary(String.valueOf(new BigDecimal(1200000)));
        curriculum.setCurrExperience(new BigDecimal(2));

        Mockito.when(iCurriculumRepo.save(curriculum)).thenReturn(curriculum);

        assertThat(curriculumService.addCurriculum(curriculum))
                .isNotNull()
                .isEqualTo(curriculum);
        assertEquals(new BigDecimal(1200000), curriculumService.addCurriculum(curriculum).getCurrSalary());


    }

    @Test
    void updateCurriculum() {

        Curriculum curriculum = new Curriculum();
        curriculum.setCurrId(CURR_ID);
        curriculum.setCurrSalary(String.valueOf(new BigDecimal(1200000)));
        curriculum.setCurrExperience(new BigDecimal(2));

        Mockito.when(iCurriculumRepo.getById(CURR_ID)).thenReturn(curriculum);

        curriculum.setCurrExperience(new BigDecimal(3));
        Mockito.when(iCurriculumRepo.save(curriculum)).thenReturn(curriculum);

        assertThat(curriculumService.updateCurriculum(curriculum))
                .isNotNull()
                .isEqualTo(curriculum);
        assertEquals("3", curriculumService.updateCurriculum(curriculum).getCurrExperience().toString());


    }

    @Test
    void searchCurriculum() {


        Curriculum curriculum = new Curriculum();
        curriculum.setCurrId(CURR_ID);
        curriculum.setCurrSalary(String.valueOf(new BigDecimal(1200000)));
        curriculum.setCurrExperience(new BigDecimal(2));

        Mockito.when(iCurriculumRepo.existsById(CURR_ID)).thenReturn(true);
        Mockito.when(iCurriculumRepo.getById(CURR_ID)).thenReturn(curriculum);

        assertThat(curriculumService.searchCurriculum(CURR_ID))
                .isNotNull()
                .isEqualTo(curriculum);
        assertEquals(new BigDecimal(1200000), curriculumService.searchCurriculum(CURR_ID).getCurrSalary());


    }

    @Test
    void deleteCurriculum() {

        Curriculum curriculum = new Curriculum();
        curriculum.setCurrId(CURR_ID);
        curriculum.setCurrSalary(String.valueOf(new BigDecimal(1200000)));
        curriculum.setCurrExperience(new BigDecimal(2));

        Mockito.when(iCurriculumRepo.getById(CURR_ID)).thenReturn(curriculum);
        Mockito.when(iCurriculumRepo.existsById(CURR_ID)).thenReturn(false);

        assertNull(curriculumService.searchCurriculum(CURR_ID));


    }

    @Test
    void curriculums() {

        Curriculum curriculum1 = new Curriculum();
        curriculum1.setCurrId(859);
        curriculum1.setCurrSalary(String.valueOf(new BigDecimal(1200000)));
        curriculum1.setCurrExperience(new BigDecimal(2));

        Curriculum curriculum2 = new Curriculum();
        curriculum2.setCurrId(969);
        curriculum2.setCurrSalary(String.valueOf(new BigDecimal(1200000)));
        curriculum2.setCurrExperience(new BigDecimal(2));

        List<Curriculum> curriculumList = new ArrayList<>();
        curriculumList.add(curriculum1);
        curriculumList.add(curriculum2);

        Mockito.when(iCurriculumRepo.findAll()).thenReturn(curriculumList);

        assertThat(curriculumService.Curriculums())
                .isNotNull()
                .isEqualTo(curriculumList);
        assertFalse(curriculumList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }


}
