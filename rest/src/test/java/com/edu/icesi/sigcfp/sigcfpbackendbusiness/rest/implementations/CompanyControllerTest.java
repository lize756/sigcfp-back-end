package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICompanyRepo;
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
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompanyControllerTest {

    final static long COMP_ID = 6936;
    TestRestTemplate restTemplate = new TestRestTemplate();
    @LocalServerPort
    private int port;
    @MockBean
    private ICompanyRepo iCompanyRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addCompany() {

        Company company = new Company();
        company.setCompId(COMP_ID);
        company.setCompName("Google");
        company.setCompEmail("google@mail.com");

        Mockito.when(iCompanyRepo.save(company)).thenReturn(company);

        // When
        ResponseEntity<Company> companyResponseEntity = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/companies/add", company, Company.class
        );

        // /then
        assertEquals(HttpStatus.CREATED, companyResponseEntity.getStatusCode());

    }

    @Test
    void updateCompany() {

        Company company = new Company();
        company.setCompId(COMP_ID);
        company.setCompName("Google");
        company.setCompEmail("google@mail.com");

        Mockito.when(iCompanyRepo.getById(COMP_ID)).thenReturn(company);

        company.setCompName("Apple");
        company.setCompEmail("apple@mail.com");
        Mockito.when(iCompanyRepo.save(company)).thenReturn(company);

        // then
        this.restTemplate.put(
                "http://localhost:" + port + "/companies/update/" + COMP_ID, company
        );


    }

    @Test
    void getCompany() {

        Company company = new Company();
        company.setCompId(COMP_ID);
        company.setCompName("Google");
        company.setCompEmail("google@mail.com");

        Mockito.when(iCompanyRepo.existsById(COMP_ID)).thenReturn(true);
        Mockito.when(iCompanyRepo.getById(COMP_ID)).thenReturn(company);

        // when
        ResponseEntity<Company> companyResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/companies/" + COMP_ID, Company.class);

        // then
        assertThat(companyResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);


    }

    @Test
    void deleteCompany() {

        Company company = new Company();
        company.setCompId(COMP_ID);
        company.setCompName("Google");
        company.setCompEmail("google@mail.com");

        Mockito.when(iCompanyRepo.getById(COMP_ID)).thenReturn(company);
        Mockito.when(iCompanyRepo.existsById(COMP_ID)).thenReturn(false);

        // when
        restTemplate.delete("http://localhost:" + port + "/companies/" + COMP_ID);
        ResponseEntity<Company> companyResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/companies/" + COMP_ID, Company.class);

        // then
        assertThat(companyResponseEntity.getStatusCode()).isNotEqualTo(HttpStatus.NO_CONTENT);


    }

    @Test
    void getCompanies() {

        Company company1 = new Company();
        company1.setCompId(COMP_ID);
        company1.setCompName("Google");
        company1.setCompEmail("google@mail.com");

        Company company2 = new Company();
        company2.setCompId(COMP_ID);
        company2.setCompName("holam");
        company2.setCompEmail("holam@mail.com");

        List<Company> companyList = new ArrayList<>();
        companyList.add(company1);
        companyList.add(company2);

        Mockito.when(iCompanyRepo.findAll()).thenReturn(companyList);

        // when
        ResponseEntity<Company[]> companyResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/companies", Company[].class);

        // then
        assertThat(companyResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(Objects.requireNonNull(companyResponseEntity.getBody())[0].getCompName())
                .isNotNull();


    }
}
