package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Triggerr;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICareerRepo;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ITriggerrRepo;
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
class TriggerrServiceTest {

    final static long TRIG_ID = 3275;

    @MockBean
    ITriggerrRepo iTriggerrRepo;
    @Autowired
    TriggerrService triggerrService;

    @BeforeAll
    static void init() {
        System.out.println("|| ---- TriggerrServiceTest Started ---- ||");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addTriggerr() {

        Triggerr triggerr = new Triggerr();
        triggerr.setTrigId(TRIG_ID);
        triggerr.setTrigName("Trig Name");
        triggerr.setTrigScope("50%");

        Mockito.when(iTriggerrRepo.save(triggerr)).thenReturn(triggerr);

        assertThat(triggerrService.addTriggerr(triggerr))
                .isNotNull()
                .isEqualTo(triggerr);
        assertEquals("Trig Name", triggerrService.addTriggerr(triggerr).getTrigName());

    }

    @Test
    void updateTriggerr() {

        Triggerr triggerr = new Triggerr();
        triggerr.setTrigId(TRIG_ID);
        triggerr.setTrigName("Trig Name");
        triggerr.setTrigScope("50%");

        Mockito.when(iTriggerrRepo.getById(TRIG_ID)).thenReturn(triggerr);

        triggerr.setTrigScope("80%");
        Mockito.when(iTriggerrRepo.save(triggerr)).thenReturn(triggerr);

        assertThat(triggerrService.updateTriggerr(triggerr))
                .isNotNull()
                .isEqualTo(triggerr);
        assertEquals("80%", triggerrService.updateTriggerr(triggerr).getTrigScope());

    }

    @Test
    void searchTriggerr() {

        Triggerr triggerr = new Triggerr();
        triggerr.setTrigId(TRIG_ID);
        triggerr.setTrigName("Trig Name");
        triggerr.setTrigScope("50%");

        Mockito.when(iTriggerrRepo.existsById(TRIG_ID)).thenReturn(true);
        Mockito.when(iTriggerrRepo.getById(TRIG_ID)).thenReturn(triggerr);

        assertThat(triggerrService.searchTriggerr(TRIG_ID))
                .isNotNull()
                .isEqualTo(triggerr);
        assertEquals("50%", triggerrService.searchTriggerr(TRIG_ID).getTrigScope());

    }

    @Test
    void deletePTriggerr() {

        Triggerr triggerr = new Triggerr();
        triggerr.setTrigId(TRIG_ID);
        triggerr.setTrigName("Trig Name");
        triggerr.setTrigScope("50%");

        Mockito.when(iTriggerrRepo.getById(TRIG_ID)).thenReturn(triggerr);
        Mockito.when(iTriggerrRepo.existsById(TRIG_ID)).thenReturn(false);

        assertNull(triggerrService.searchTriggerr(TRIG_ID));

    }

    @Test
    void triggerrs() {

        Triggerr triggerr1 = new Triggerr();
        triggerr1.setTrigId(TRIG_ID);
        triggerr1.setTrigName("Trig Name");
        triggerr1.setTrigScope("50%");

        Triggerr triggerr2 = new Triggerr();
        triggerr2.setTrigId(TRIG_ID);
        triggerr2.setTrigName("Trig Name");
        triggerr2.setTrigScope("50%");

        List<Triggerr> triggerrList = new ArrayList<>();
        triggerrList.add(triggerr1);
        triggerrList.add(triggerr2);

        Mockito.when(iTriggerrRepo.findAll()).thenReturn(triggerrList);

        assertThat(triggerrService.triggerrs())
                .isNotNull()
                .isEqualTo(triggerrList);
        assertFalse(triggerrList.isEmpty());
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- TriggerrServiceTest Finished ---- ||");
    }
}
