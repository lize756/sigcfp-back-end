package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Ethnicgroup;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICityRepo;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IEthnicgroupRepo;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EthnicgroupControllerTest {

    final static long ETGR_ID = 2154;

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    @MockBean
    private IEthnicgroupRepo iEthnicgroupRepo;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addEthnicgroup() {

        Ethnicgroup ethnicgroup = new Ethnicgroup();
        ethnicgroup.setEtgrId(ETGR_ID);
        ethnicgroup.setEtgrName("Afrodecesciente");
        ethnicgroup.setEtgrDescription("Este es un afrodescendiente");

        Mockito.when(iEthnicgroupRepo.save(ethnicgroup)).thenReturn(ethnicgroup);

        // When
        ResponseEntity<Ethnicgroup> ethnicgroupResponseEntity = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/ethnicGroups/add", ethnicgroup, Ethnicgroup.class
        );

        // /then
        assertEquals(HttpStatus.CREATED, ethnicgroupResponseEntity.getStatusCode());


    }

    @Test
    void updateEthnicgroup() {

        Ethnicgroup ethnicgroup = new Ethnicgroup();
        ethnicgroup.setEtgrId(ETGR_ID);
        ethnicgroup.setEtgrName("Afrodecesciente");
        ethnicgroup.setEtgrDescription("Este es un afrodescendiente");

        Mockito.when(iEthnicgroupRepo.getById(ETGR_ID)).thenReturn(ethnicgroup);

        ethnicgroup.setEtgrName("Indígena");
        Mockito.when(iEthnicgroupRepo.save(ethnicgroup)).thenReturn(ethnicgroup);

        // then
        this.restTemplate.put(
                "http://localhost:" + port + "/ethnicGroups/update/"+ETGR_ID, ethnicgroup
        );



    }

    @Test
    void getEthnicgroup() {

        Ethnicgroup ethnicgroup = new Ethnicgroup();
        ethnicgroup.setEtgrId(ETGR_ID);
        ethnicgroup.setEtgrName("Afrodecesciente");
        ethnicgroup.setEtgrDescription("Este es un afrodescendiente");

        Mockito.when(iEthnicgroupRepo.existsById(ETGR_ID)).thenReturn(true);
        Mockito.when(iEthnicgroupRepo.getById(ETGR_ID)).thenReturn(ethnicgroup);

        // when
        ResponseEntity<Ethnicgroup> cityResponseEntity = restTemplate.getForEntity("http://localhost:" + port +"/ethnicGroups/"+ETGR_ID, Ethnicgroup.class);

        // then
        assertThat(cityResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);


    }

    @Test
    void deleteEthnicgroup() {

        Ethnicgroup ethnicgroup = new Ethnicgroup();
        ethnicgroup.setEtgrId(ETGR_ID);
        ethnicgroup.setEtgrName("Afrodecesciente");
        ethnicgroup.setEtgrDescription("Este es un afrodescendiente");

        Mockito.when(iEthnicgroupRepo.getById(ETGR_ID)).thenReturn(ethnicgroup);
        Mockito.when(iEthnicgroupRepo.existsById(ETGR_ID)).thenReturn(false);

        // when
        restTemplate.delete("http://localhost:" + port +"/ethnicGroups/"+ETGR_ID);
        ResponseEntity<Ethnicgroup> ethnicgroupResponseEntity = restTemplate.getForEntity("http://localhost:" + port +"/ethnicGroups/"+ETGR_ID, Ethnicgroup.class);

        // then
        assertThat(ethnicgroupResponseEntity.getStatusCode()).isNotEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void getEthnicgroups() {

        Ethnicgroup ethnicgroup1 = new Ethnicgroup();
        ethnicgroup1.setEtgrId(586);
        ethnicgroup1.setEtgrName("Afrodecesciente");
        ethnicgroup1.setEtgrDescription("Este es un afrodescendiente");

        Ethnicgroup ethnicgroup2 = new Ethnicgroup();
        ethnicgroup2.setEtgrId(69955);
        ethnicgroup2.setEtgrName("Indígena");
        ethnicgroup2.setEtgrDescription("Este es un indígena");

        List<Ethnicgroup> ethnicgroupList = new ArrayList<>();
        ethnicgroupList.add(ethnicgroup1);
        ethnicgroupList.add(ethnicgroup2);

        Mockito.when(iEthnicgroupRepo.findAll()).thenReturn(ethnicgroupList);

        // when
        ResponseEntity<Ethnicgroup[]> ethnicgroupResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/ethnicGroups", Ethnicgroup[].class);

        // then
        assertThat(ethnicgroupResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(Objects.requireNonNull(ethnicgroupResponseEntity.getBody())[0].getEtgrName())
                .isNotNull();

    }
}
