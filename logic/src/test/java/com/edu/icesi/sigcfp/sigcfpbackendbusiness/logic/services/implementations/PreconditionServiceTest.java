package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Precondition;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICareerRepo;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IPreconditionRepo;
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
class PreconditionServiceTest {

    final static long PREC_ID = 3275;

    @MockBean
    IPreconditionRepo iPreconditionRepo;
    @Autowired
    PreconditionService preconditionService;

    @BeforeAll
    static void init() {
        System.out.println("|| ---- PreconditionServiceTest Started ---- ||");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addPrecondition() {

        Precondition precondition = new Precondition();
        precondition.setPrecondId(PREC_ID);
        precondition.setPrecondLogicaloperand(">");

        Mockito.when(iPreconditionRepo.save(precondition)).thenReturn(precondition);

        assertThat(preconditionService.addPrecondition(precondition))
                .isNotNull()
                .isEqualTo(precondition);
        assertEquals(">", preconditionService.addPrecondition(precondition).getPrecondLogicaloperand());

    }

    @Test
    void updatePrecondition() {

        Precondition precondition = new Precondition();
        precondition.setPrecondId(PREC_ID);
        precondition.setPrecondLogicaloperand(">");

        Mockito.when(iPreconditionRepo.getById(PREC_ID)).thenReturn(precondition);

        precondition.setPrecondLogicaloperand("=>");
        Mockito.when(iPreconditionRepo.save(precondition)).thenReturn(precondition);

        assertThat(preconditionService.updatePrecondition(precondition))
                .isNotNull()
                .isEqualTo(precondition);
        assertEquals("=>", preconditionService.updatePrecondition(precondition).getPrecondLogicaloperand());

    }

    @Test
    void searchPrecondition() {

        Precondition precondition = new Precondition();
        precondition.setPrecondId(PREC_ID);
        precondition.setPrecondLogicaloperand(">");

        Mockito.when(iPreconditionRepo.existsById(PREC_ID)).thenReturn(true);
        Mockito.when(iPreconditionRepo.getById(PREC_ID)).thenReturn(precondition);

        assertThat(preconditionService.searchPrecondition(PREC_ID))
                .isNotNull()
                .isEqualTo(precondition);
        assertEquals(">", preconditionService.searchPrecondition(PREC_ID).getPrecondLogicaloperand());

    }

    @Test
    void deletePrecondition() {

        Precondition precondition = new Precondition();
        precondition.setPrecondId(PREC_ID);
        precondition.setPrecondLogicaloperand(">");

        Mockito.when(iPreconditionRepo.getById(PREC_ID)).thenReturn(precondition);
        Mockito.when(iPreconditionRepo.existsById(PREC_ID)).thenReturn(false);

        assertNull(preconditionService.searchPrecondition(PREC_ID));

    }

    @Test
    void preconditions() {

        Precondition precondition1 = new Precondition();
        precondition1.setPrecondId(PREC_ID);
        precondition1.setPrecondLogicaloperand(">");

        Precondition precondition2 = new Precondition();
        precondition2.setPrecondId(PREC_ID);
        precondition2.setPrecondLogicaloperand("<");

        List<Precondition> preconditionList = new ArrayList<>();
        preconditionList.add(precondition1);
        preconditionList.add(precondition2);

        Mockito.when(iPreconditionRepo.findAll()).thenReturn(preconditionList);

        assertThat(preconditionService.preconditions())
                .isNotNull()
                .isEqualTo(preconditionList);
        assertFalse(preconditionList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- PreconditionServiceTest Finished ---- ||");
    }


}
