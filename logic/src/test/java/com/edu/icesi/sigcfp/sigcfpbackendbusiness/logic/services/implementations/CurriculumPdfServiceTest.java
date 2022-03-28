package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Curriculum;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.CurriculumPdf;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICurriculumPdfRepo;
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
class CurriculumPdfServiceTest {

    final static long CUPDF_ID = 2312;

    @MockBean
    ICurriculumPdfRepo iCurriculumPdfRepo;
    @Autowired
    CurriculumPdfService curriculumPdfService;

    @BeforeAll
    static void init(){
        System.out.println("|| ---- CurriculumPdfServiceTest Started ---- ||");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addCurriculumPdf() {

        CurriculumPdf curriculumPdf = new CurriculumPdf();
        curriculumPdf.setCuPdfId(CUPDF_ID);
        curriculumPdf.setCuPdfFile("curripath");

        Mockito.when(iCurriculumPdfRepo.save(curriculumPdf)).thenReturn(curriculumPdf);

        assertThat(curriculumPdfService.addCurriculumPdf(curriculumPdf))
                .isNotNull()
                .isEqualTo(curriculumPdf);
        assertEquals("curripath", curriculumPdfService.addCurriculumPdf(curriculumPdf).getCuPdfFile());

    }

    @Test
    void updateCurriculumPdf() {

        CurriculumPdf curriculumPdf = new CurriculumPdf();
        curriculumPdf.setCuPdfId(CUPDF_ID);
        curriculumPdf.setCuPdfFile("curripath");

        Mockito.when(iCurriculumPdfRepo.getById(CUPDF_ID)).thenReturn(curriculumPdf);

        curriculumPdf.setCuPdfFile("othercurripath");
        Mockito.when(iCurriculumPdfRepo.save(curriculumPdf)).thenReturn(curriculumPdf);

        assertThat(curriculumPdfService.updateCurriculumPdf(curriculumPdf))
                .isNotNull()
                .isEqualTo(curriculumPdf);
        assertEquals("othercurripath", curriculumPdfService.updateCurriculumPdf(curriculumPdf).getCuPdfFile());

    }

    @Test
    void searchCurriculumPdf() {

        CurriculumPdf curriculumPdf = new CurriculumPdf();
        curriculumPdf.setCuPdfId(CUPDF_ID);
        curriculumPdf.setCuPdfFile("curripath");

        Mockito.when(iCurriculumPdfRepo.existsById(CUPDF_ID)).thenReturn(true);
        Mockito.when(iCurriculumPdfRepo.getById(CUPDF_ID)).thenReturn(curriculumPdf);

        assertThat(curriculumPdfService.searchCurriculumPdf(CUPDF_ID))
                .isNotNull()
                .isEqualTo(curriculumPdf);
        assertEquals("curripath", curriculumPdfService.searchCurriculumPdf(CUPDF_ID).getCuPdfFile());

    }

    @Test
    void deleteCurriculumPdf() {

        CurriculumPdf curriculumPdf = new CurriculumPdf();
        curriculumPdf.setCuPdfId(CUPDF_ID);
        curriculumPdf.setCuPdfFile("curripath");

        Mockito.when(iCurriculumPdfRepo.getById(CUPDF_ID)).thenReturn(curriculumPdf);
        Mockito.when(iCurriculumPdfRepo.existsById(CUPDF_ID)).thenReturn(false);

        assertNull(curriculumPdfService.searchCurriculumPdf(CUPDF_ID));

    }

    @Test
    void curriculumPdfs() {

        CurriculumPdf curriculumPdf1 = new CurriculumPdf();
        curriculumPdf1.setCuPdfId(CUPDF_ID);
        curriculumPdf1.setCuPdfFile("curripath");

        CurriculumPdf curriculumPdf2 = new CurriculumPdf();
        curriculumPdf2.setCuPdfId(CUPDF_ID);
        curriculumPdf2.setCuPdfFile("curripath");

        List<CurriculumPdf> curriculumPdfList = new ArrayList<>();
        curriculumPdfList.add(curriculumPdf1);
        curriculumPdfList.add(curriculumPdf2);

        Mockito.when(iCurriculumPdfRepo.findAll()).thenReturn(curriculumPdfList);

        assertThat(curriculumPdfService.CurriculumPdfs())
                .isNotNull()
                .isEqualTo(curriculumPdfList);
        assertFalse(curriculumPdfList.isEmpty());

    }


    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- CurriculumPdfServiceTest Finished ---- ||");
    }


}
