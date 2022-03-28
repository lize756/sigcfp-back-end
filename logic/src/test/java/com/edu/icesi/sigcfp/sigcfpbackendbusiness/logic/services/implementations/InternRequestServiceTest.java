package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.InternRequest;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICareerRepo;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IInternRequestRepo;
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
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class InternRequestServiceTest {


    final static long INTE_REQU_ID = 8858;

    @MockBean
    IInternRequestRepo iInternRequestRepo;
    @Autowired
    InternRequestService internRequestService;

    @BeforeAll
    static void init() {
        System.out.println("|| ---- InternRequestServiceTest Started ---- ||");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void addInternRequest() {

        InternRequest internRequest = new InternRequest();
        internRequest.setInteRequId(INTE_REQU_ID);
        internRequest.setInteRequDuration("6 MESES");
        internRequest.setInteRequCreate(new Date());
        internRequest.setInteRequSalary(new BigDecimal(1500000));

        Mockito.when(iInternRequestRepo.save(internRequest)).thenReturn(internRequest);

        assertThat(internRequestService.addInternRequest(internRequest))
                .isNotNull()
                .isEqualTo(internRequest);
        assertEquals("6 MESES", internRequestService.addInternRequest(internRequest).getInteRequDuration());

    }

    @Test
    void updateInternRequest() {

        InternRequest internRequest = new InternRequest();
        internRequest.setInteRequId(INTE_REQU_ID);
        internRequest.setInteRequDuration("6 MESES");
        internRequest.setInteRequCreate(new Date());
        internRequest.setInteRequSalary(new BigDecimal(1500000));

        Mockito.when(iInternRequestRepo.getById(INTE_REQU_ID)).thenReturn(internRequest);

        internRequest.setInteRequSalary(new BigDecimal(2000000));
        Mockito.when(iInternRequestRepo.save(internRequest)).thenReturn(internRequest);

        assertThat(internRequestService.updateInternRequest(internRequest))
                .isNotNull()
                .isEqualTo(internRequest);
        assertEquals(new BigDecimal(2000000), internRequestService.updateInternRequest(internRequest).getInteRequSalary());

    }

    @Test
    void searchInternRequest() {

        InternRequest internRequest = new InternRequest();
        internRequest.setInteRequId(INTE_REQU_ID);
        internRequest.setInteRequDuration("6 MESES");
        internRequest.setInteRequCreate(new Date());
        internRequest.setInteRequSalary(new BigDecimal(1500000));

        Mockito.when(iInternRequestRepo.existsById(INTE_REQU_ID)).thenReturn(true);
        Mockito.when(iInternRequestRepo.getById(INTE_REQU_ID)).thenReturn(internRequest);

        assertThat(internRequestService.searchInternRequest(INTE_REQU_ID))
                .isNotNull()
                .isEqualTo(internRequest);
        assertEquals("6 MESES", internRequestService.searchInternRequest(INTE_REQU_ID).getInteRequDuration());

    }

    @Test
    void deleteInternRequest() {

        InternRequest internRequest = new InternRequest();
        internRequest.setInteRequId(INTE_REQU_ID);
        internRequest.setInteRequDuration("6 MESES");
        internRequest.setInteRequCreate(new Date());
        internRequest.setInteRequSalary(new BigDecimal(1500000));

        Mockito.when(iInternRequestRepo.getById(INTE_REQU_ID)).thenReturn(internRequest);
        Mockito.when(iInternRequestRepo.existsById(INTE_REQU_ID)).thenReturn(false);

        assertNull(internRequestService.searchInternRequest(INTE_REQU_ID));

    }

    @Test
    void internRequests() {

        InternRequest internRequest1 = new InternRequest();
        internRequest1.setInteRequId(INTE_REQU_ID);
        internRequest1.setInteRequDuration("6 MESES");
        internRequest1.setInteRequCreate(new Date());
        internRequest1.setInteRequSalary(new BigDecimal(1500000));

        InternRequest internRequest2 = new InternRequest();
        internRequest2.setInteRequId(INTE_REQU_ID);
        internRequest2.setInteRequDuration("6 MESES");
        internRequest2.setInteRequCreate(new Date());
        internRequest2.setInteRequSalary(new BigDecimal(1500000));

        List<InternRequest> requestArrayList = new ArrayList<>();
        requestArrayList.add(internRequest1);
        requestArrayList.add(internRequest2);

        Mockito.when(iInternRequestRepo.findAll()).thenReturn(requestArrayList);

        assertThat(internRequestService.internRequests())
                .isNotNull()
                .isEqualTo(requestArrayList);
        assertFalse(requestArrayList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- InternRequestServiceTest Started ---- ||");
    }


}
