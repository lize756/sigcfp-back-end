package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.InternRequest;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICityRepo;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IInternRequestRepo;
import org.junit.jupiter.api.AfterEach;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InternRequestControllerTest {

    final static long INTE_REQU_ID = 8858;

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    @MockBean
    private IInternRequestRepo iInternRequestRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    //@Test
    void addInternRequest() {

        InternRequest internRequest = new InternRequest();
        internRequest.setInteRequId(INTE_REQU_ID);
        internRequest.setInteRequDuration("6 MESES");
        internRequest.setInteRequCreate(new Date());
        internRequest.setInteRequSalary(new BigDecimal(1500000));

        Mockito.when(iInternRequestRepo.save(internRequest)).thenReturn(internRequest);

        // When
        ResponseEntity<InternRequest> internRequestResponseEntity = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/internRequests/add", internRequest, InternRequest.class
        );

        // /then
        assertEquals(HttpStatus.CREATED, internRequestResponseEntity.getStatusCode());

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

        // then
        this.restTemplate.put(
                "http://localhost:" + port + "/internRequests/update/"+INTE_REQU_ID, internRequest
        );
    }

    @Test
    void getInternRequest() {

        InternRequest internRequest = new InternRequest();
        internRequest.setInteRequId(INTE_REQU_ID);
        internRequest.setInteRequDuration("6 MESES");
        internRequest.setInteRequCreate(new Date());
        internRequest.setInteRequSalary(new BigDecimal(1500000));

        Mockito.when(iInternRequestRepo.existsById(INTE_REQU_ID)).thenReturn(true);
        Mockito.when(iInternRequestRepo.getById(INTE_REQU_ID)).thenReturn(internRequest);

        // when
        ResponseEntity<InternRequest> internRequestResponseEntity = restTemplate.getForEntity("http://localhost:" + port +"/internRequests/"+INTE_REQU_ID, InternRequest.class);

        // then
        assertThat(internRequestResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);


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

        // when
        restTemplate.delete("http://localhost:" + port +"/internRequests/"+INTE_REQU_ID);
        ResponseEntity<InternRequest> internRequestResponseEntity = restTemplate.getForEntity("http://localhost:" + port +"/internRequests/"+INTE_REQU_ID, InternRequest.class);

        // then
        assertThat(internRequestResponseEntity.getStatusCode()).isNotEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void getInternRequests() {

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

        // when
        ResponseEntity<InternRequest[]> internRequestResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/internRequests", InternRequest[].class);

        // then
        assertThat(internRequestResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(Objects.requireNonNull(internRequestResponseEntity.getBody())[0].getInteRequName());


    }
}
