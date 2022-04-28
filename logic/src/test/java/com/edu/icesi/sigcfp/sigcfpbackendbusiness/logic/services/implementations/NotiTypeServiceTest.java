package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.NotiType;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.INotiTypeRepo;
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
class NotiTypeServiceTest {

    final static long NO_TY_ID = 2587;

    @MockBean
    INotiTypeRepo iNotiTypeRepo;
    @Autowired
    NotiTypeService notiTypeService;

    @BeforeAll
    static void init() {
        System.out.println("|| ---- NotiTypeServiceTest Started ---- ||");
    }

    @AfterAll
    static void finish() {
        System.out.println("|| ---- NotiTypeServiceTest Finished ---- ||");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addNotiType() {

        NotiType notiType = new NotiType();
        notiType.setNotiTypeId(NO_TY_ID);
        notiType.setNotiTypeName("APERTURA");

        Mockito.when(iNotiTypeRepo.save(notiType)).thenReturn(notiType);

        assertThat(notiTypeService.addNotiType(notiType))
                .isNotNull()
                .isEqualTo(notiType);
        assertEquals("APERTURA", notiTypeService.addNotiType(notiType).getNotiTypeName());

    }

    @Test
    void updateNotiType() {

        NotiType notiType = new NotiType();
        notiType.setNotiTypeId(NO_TY_ID);
        notiType.setNotiTypeName("APERTURA");

        Mockito.when(iNotiTypeRepo.getById(NO_TY_ID)).thenReturn(notiType);

        notiType.setNotiTypeName("CIERRE");
        Mockito.when(iNotiTypeRepo.save(notiType)).thenReturn(notiType);

        assertThat(notiTypeService.updateNotiType(notiType))
                .isNotNull()
                .isEqualTo(notiType);
        assertEquals("CIERRE", notiTypeService.updateNotiType(notiType).getNotiTypeName());

    }

    @Test
    void searchNotiType() {
        NotiType notiType = new NotiType();
        notiType.setNotiTypeId(NO_TY_ID);
        notiType.setNotiTypeName("APERTURA");

        Mockito.when(iNotiTypeRepo.existsById(NO_TY_ID)).thenReturn(true);
        Mockito.when(iNotiTypeRepo.getById(NO_TY_ID)).thenReturn(notiType);

        assertThat(notiTypeService.searchNotiType(NO_TY_ID))
                .isNotNull()
                .isEqualTo(notiType);
        assertEquals("APERTURA", notiTypeService.searchNotiType(NO_TY_ID).getNotiTypeName());

    }

    @Test
    void deleteNotiType() {

        NotiType notiType = new NotiType();
        notiType.setNotiTypeId(NO_TY_ID);
        notiType.setNotiTypeName("APERTURA");

        Mockito.when(iNotiTypeRepo.getById(NO_TY_ID)).thenReturn(notiType);
        Mockito.when(iNotiTypeRepo.existsById(NO_TY_ID)).thenReturn(false);

        assertNull(notiTypeService.searchNotiType(NO_TY_ID));

    }

    @Test
    void notiTypes() {

        NotiType notiType1 = new NotiType();
        notiType1.setNotiTypeId(NO_TY_ID);
        notiType1.setNotiTypeName("APERTURA");

        NotiType notiType2 = new NotiType();
        notiType2.setNotiTypeId(NO_TY_ID);
        notiType2.setNotiTypeName("APERTURA");

        List<NotiType> notiTypeList = new ArrayList<>();
        notiTypeList.add(notiType1);
        notiTypeList.add(notiType2);

        Mockito.when(iNotiTypeRepo.findAll()).thenReturn(notiTypeList);

        assertThat(notiTypeService.notiTypes())
                .isNotNull()
                .isEqualTo(notiTypeList);
        assertFalse(notiTypeList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }


}
