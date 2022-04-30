package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Language;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ILanguageRepo;
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
class LanguageControllerTest {

    final static long LANG_ID = 5595;
    TestRestTemplate restTemplate = new TestRestTemplate();
    @LocalServerPort
    private int port;
    @MockBean
    private ILanguageRepo iLanguageRepo;

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

        // When
        ResponseEntity<Language> languageResponseEntity = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/languages/add", language, Language.class
        );

        // /then
        assertEquals(HttpStatus.CREATED, languageResponseEntity.getStatusCode());

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

        // then
        this.restTemplate.put(
                "http://localhost:" + port + "/languages/update/" + LANG_ID, language
        );

    }

    @Test
    void getLanguage() {

        Language language = new Language();
        language.setLanguId(LANG_ID);
        language.setLanguName("Español");
        language.setLanguLevel("AVANZADO");

        Mockito.when(iLanguageRepo.existsById(LANG_ID)).thenReturn(true);
        Mockito.when(iLanguageRepo.getById(LANG_ID)).thenReturn(language);

        // when
        ResponseEntity<Language> languageResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/languages/" + LANG_ID, Language.class);

        // then
        assertThat(languageResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);


    }

    @Test
    void deleteLanguage() {

        Language language = new Language();
        language.setLanguId(LANG_ID);
        language.setLanguName("Español");
        language.setLanguLevel("AVANZADO");

        Mockito.when(iLanguageRepo.getById(LANG_ID)).thenReturn(language);
        Mockito.when(iLanguageRepo.existsById(LANG_ID)).thenReturn(false);

        // when
        restTemplate.delete("http://localhost:" + port + "/languages/" + LANG_ID);
        ResponseEntity<Language> languageResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/languages/" + LANG_ID, Language.class);

        // then
        assertThat(languageResponseEntity.getStatusCode()).isNotEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void getLanguages() {

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

        // when
        ResponseEntity<Language[]> languageResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/languages", Language[].class);

        // then
        assertThat(languageResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(Objects.requireNonNull(languageResponseEntity.getBody())[0].getLanguName())
                .isNotNull();


    }
}
