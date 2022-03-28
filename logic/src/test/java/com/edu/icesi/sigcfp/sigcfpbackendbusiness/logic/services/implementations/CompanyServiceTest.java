package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Faculty;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICareerRepo;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICompanyRepo;
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
class CompanyServiceTest {

    final static long COMP_ID = 6936;

    @MockBean
    ICompanyRepo iCompanyRepo;
    @Autowired
    CompanyService companyService;

    @BeforeAll
    static void init(){
        System.out.println("|| ---- CompanyServiceTest Started ---- ||");
    }

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

        assertThat(companyService.addCompany(company))
                .isNotNull()
                .isEqualTo(company);
        assertEquals("Google", companyService.addCompany(company).getCompName());

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

        assertThat(companyService.updateCompany(company))
                .isNotNull()
                .isEqualTo(company);
        assertEquals("Apple", companyService.updateCompany(company).getCompName());
        assertEquals("apple@mail.com", companyService.updateCompany(company).getCompEmail());

    }

    @Test
    void searchCompany() {

        Company company = new Company();
        company.setCompId(COMP_ID);
        company.setCompName("Google");
        company.setCompEmail("google@mail.com");

        Mockito.when(iCompanyRepo.existsById(COMP_ID)).thenReturn(true);
        Mockito.when(iCompanyRepo.getById(COMP_ID)).thenReturn(company);

        assertThat(companyService.searchCompany(COMP_ID))
                .isNotNull()
                .isEqualTo(company);
        assertEquals("Google", companyService.searchCompany(COMP_ID).getCompName());

    }

    @Test
    void deleteCompany() {

        Company company = new Company();
        company.setCompId(COMP_ID);
        company.setCompName("Google");
        company.setCompEmail("google@mail.com");

        Mockito.when(iCompanyRepo.getById(COMP_ID)).thenReturn(company);
        Mockito.when(iCompanyRepo.existsById(COMP_ID)).thenReturn(false);

        assertNull(companyService.searchCompany(COMP_ID));

    }

    @Test
    void companies() {

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

        assertThat(companyService.companies())
                .isNotNull()
                .isEqualTo(companyList);
        assertFalse(companyList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- CompanyServiceTest Finished ---- ||");
    }

}
