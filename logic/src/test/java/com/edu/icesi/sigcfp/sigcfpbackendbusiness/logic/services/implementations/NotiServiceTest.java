package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Noti;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICareerRepo;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.INotiRepo;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class NotiServiceTest {

    final static long NOTI_ID = 2147;

    @MockBean
    INotiRepo iNotiRepo;
    @Autowired
    NotiService notiService;

    @BeforeAll
    static void init() {
        System.out.println("|| ---- NotiServiceTest Started ---- ||");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void addNoti() {

        Noti noti = new Noti();
        noti.setNotiId(NOTI_ID);
        noti.setNotiSubject("Apertura Periodo de Práctica");
        noti.setNotiDate(new Date());

        Mockito.when(iNotiRepo.save(noti)).thenReturn(noti);

        assertThat(notiService.addNoti(noti))
                .isNotNull()
                .isEqualTo(noti);
        assertEquals("Apertura Periodo de Práctica", notiService.addNoti(noti).getNotiSubject());

    }

    @Test
    void updateNoti() {

        Noti noti = new Noti();
        noti.setNotiId(NOTI_ID);
        noti.setNotiSubject("Apertura Periodo de Práctica");

        Mockito.when(iNotiRepo.getById(NOTI_ID)).thenReturn(noti);

        noti.setNotiSubject("Cierre Periodo de Práctica");
        Mockito.when(iNotiRepo.save(noti)).thenReturn(noti);

        assertThat(notiService.updateNoti(noti))
                .isNotNull()
                .isEqualTo(noti);
        assertEquals("Cierre Periodo de Práctica", notiService.updateNoti(noti).getNotiSubject());

    }

    @Test
    void searchNoti() {

        Noti noti = new Noti();
        noti.setNotiId(NOTI_ID);
        noti.setNotiSubject("Apertura Periodo de Práctica");
        noti.setNotiDate(new Date());

        Mockito.when(iNotiRepo.existsById(NOTI_ID)).thenReturn(true);
        Mockito.when(iNotiRepo.getById(NOTI_ID)).thenReturn(noti);

        assertThat(notiService.searchNoti(NOTI_ID))
                .isNotNull()
                .isEqualTo(noti);
        assertEquals("Apertura Periodo de Práctica", notiService.searchNoti(NOTI_ID).getNotiSubject());

    }

    @Test
    void deleteNoti() {

        Noti noti = new Noti();
        noti.setNotiId(NOTI_ID);
        noti.setNotiSubject("Apertura Periodo de Práctica");
        noti.setNotiDate(new Date());

        Mockito.when(iNotiRepo.getById(NOTI_ID)).thenReturn(noti);
        Mockito.when(iNotiRepo.existsById(NOTI_ID)).thenReturn(false);

        assertNull(notiService.searchNoti(NOTI_ID));

    }

    @Test
    void notis() {

        Noti noti1 = new Noti();
        noti1.setNotiId(NOTI_ID);
        noti1.setNotiSubject("Apertura Periodo de Práctica");
        noti1.setNotiDate(new Date());

        Noti noti2 = new Noti();
        noti2.setNotiId(NOTI_ID);
        noti2.setNotiSubject("Apertura Periodo de Práctica");
        noti2.setNotiDate(new Date());

        List<Noti> notiList = new ArrayList<>();
        notiList.add(noti1);
        notiList.add(noti2);

        Mockito.when(iNotiRepo.findAll()).thenReturn(notiList);

        assertThat(notiService.notis())
                .isNotNull()
                .isEqualTo(notiList);
        assertFalse(notiList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- NotiServiceTest Finished ---- ||");
    }

}
