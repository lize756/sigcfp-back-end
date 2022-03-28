package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Ethnicgroup;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICareerRepo;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IEthnicgroupRepo;
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
class EthnicgroupServiceTest {

    final static long ETGR_ID = 2154;

    @MockBean
    IEthnicgroupRepo iEthnicgroupRepo;
    @Autowired
    EthnicgroupService ethnicgroupService;

    @BeforeAll
    static void init(){
        System.out.println("|| ---- EthnicgroupServiceTest Started ---- ||");
    }

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

        assertThat(ethnicgroupService.addEthnicgroup(ethnicgroup))
                .isNotNull()
                .isEqualTo(ethnicgroup);
        assertEquals("Afrodecesciente", ethnicgroupService.addEthnicgroup(ethnicgroup).getEtgrName());
        assertFalse(ethnicgroupService.addEthnicgroup(ethnicgroup).getEtgrDescription().isEmpty());

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

        assertThat(ethnicgroupService.updateEthnicgroup(ethnicgroup))
                .isNotNull()
                .isEqualTo(ethnicgroup);
        assertEquals("Indígena", ethnicgroupService.updateEthnicgroup(ethnicgroup).getEtgrName());

    }

    @Test
    void searchEthnicgroup() {

        Ethnicgroup ethnicgroup = new Ethnicgroup();
        ethnicgroup.setEtgrId(ETGR_ID);
        ethnicgroup.setEtgrName("Afrodecesciente");
        ethnicgroup.setEtgrDescription("Este es un afrodescendiente");

        Mockito.when(iEthnicgroupRepo.existsById(ETGR_ID)).thenReturn(true);
        Mockito.when(iEthnicgroupRepo.getById(ETGR_ID)).thenReturn(ethnicgroup);

        assertThat(ethnicgroupService.searchEthnicgroup(ETGR_ID))
                .isNotNull()
                .isEqualTo(ethnicgroup);
        assertEquals("Afrodecesciente", ethnicgroupService.searchEthnicgroup(ETGR_ID).getEtgrName());

    }

    @Test
    void deleteEthnicgroup() {

        Ethnicgroup ethnicgroup = new Ethnicgroup();
        ethnicgroup.setEtgrId(ETGR_ID);
        ethnicgroup.setEtgrName("Afrodecesciente");
        ethnicgroup.setEtgrDescription("Este es un afrodescendiente");

        Mockito.when(iEthnicgroupRepo.getById(ETGR_ID)).thenReturn(ethnicgroup);
        Mockito.when(iEthnicgroupRepo.existsById(ETGR_ID)).thenReturn(false);

        assertNull(ethnicgroupService.searchEthnicgroup(ETGR_ID));

    }

    @Test
    void ethnicgroups() {

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

        assertThat(ethnicgroupService.ethnicgroups())
                .isNotNull()
                .isEqualTo(ethnicgroupList);
        assertFalse(ethnicgroupList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- EthnicgroupServiceTest Started ---- ||");
    }



}
