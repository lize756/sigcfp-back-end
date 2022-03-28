package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Language;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ILanguageRepo;
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
class LanguageServiceTest {

    final static long LANG_ID = 5595;

    @MockBean
    ILanguageRepo iLanguageRepo;
    @Autowired
    LanguageService languageService;

    @BeforeAll
    static void init() {
        System.out.println("|| ---- LanguageServiceTest Started ---- ||");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addLanguage() {

        Language language = new Language();
        language.setLanguId(LANG_ID);
        language.setLanguName("Español");
        language.setLanguLevel("AVANZADO");

        Mockito.when(iLanguageRepo.save(language)).thenReturn(language);

        assertThat(languageService.addLanguage(language))
                .isNotNull()
                .isEqualTo(language);
        assertEquals("Español", languageService.addLanguage(language).getLanguName());
        assertEquals("AVANZADO", languageService.addLanguage(language).getLanguLevel());

    }

    @Test
    void updateLanguage() {

        Language language = new Language();
        language.setLanguId(LANG_ID);
        language.setLanguName("Español");
        language.setLanguLevel("AVANZADO");

        Mockito.when(iLanguageRepo.getById(LANG_ID)).thenReturn(language);

        language.setLanguLevel("INTERMEDIO");
        Mockito.when(iLanguageRepo.save(language)).thenReturn(language);

        assertThat(languageService.updateLanguage(language))
                .isNotNull()
                .isEqualTo(language);
        assertEquals("INTERMEDIO", languageService.updateLanguage(language).getLanguLevel());

    }

    @Test
    void searchLanguage() {

        Language language = new Language();
        language.setLanguId(LANG_ID);
        language.setLanguName("Español");
        language.setLanguLevel("AVANZADO");

        Mockito.when(iLanguageRepo.existsById(LANG_ID)).thenReturn(true);
        Mockito.when(iLanguageRepo.getById(LANG_ID)).thenReturn(language);

        assertThat(languageService.searchLanguage(LANG_ID))
                .isNotNull()
                .isEqualTo(language);
        assertEquals("AVANZADO", languageService.searchLanguage(LANG_ID).getLanguLevel());

    }

    @Test
    void deleteLanguage() {

        Language language = new Language();
        language.setLanguId(LANG_ID);
        language.setLanguName("Español");
        language.setLanguLevel("AVANZADO");

        Mockito.when(iLanguageRepo.getById(LANG_ID)).thenReturn(language);
        Mockito.when(iLanguageRepo.existsById(LANG_ID)).thenReturn(false);

        assertNull(languageService.searchLanguage(LANG_ID));


    }

    @Test
    void languages() {

        Language language1 = new Language();
        language1.setLanguId(693);
        language1.setLanguName("Inglés");
        language1.setLanguLevel("INTERMEDIO");

        Language language2 = new Language();
        language2.setLanguId(582);
        language2.setLanguName("Español");
        language2.setLanguLevel("AVANZADO");

        List<Language> languageList = new ArrayList<>();
        languageList.add(language1);
        languageList.add(language2);

        Mockito.when(iLanguageRepo.findAll()).thenReturn(languageList);

        assertThat(languageService.languages())
                .isNotNull()
                .isEqualTo(languageList);
        assertFalse(languageList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- LanguageServiceTest Finished ---- ||");
    }

}
