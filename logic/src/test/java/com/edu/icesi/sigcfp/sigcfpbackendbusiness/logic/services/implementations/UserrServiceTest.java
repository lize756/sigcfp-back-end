package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IUserrRepo;
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
class UserrServiceTest {

    final static long USER_ID = 5283;

    @MockBean
    IUserrRepo iUserrRepo;
    @Autowired
    UserrService userrService;

    @BeforeAll
    static void init() {
        System.out.println("|| ---- UserrServiceTest Started ---- ||");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addUserr() {

        Userr userr = new Userr();
        userr.setUserId(USER_ID);
        userr.setUserName("carlo34");
        userr.setUserPassword("Pass#$$%RRE");

        Mockito.when(iUserrRepo.save(userr)).thenReturn(userr);

        assertThat(userrService.addUserr(userr))
                .isNotNull()
                .isEqualTo(userr);
        assertEquals("carlo34", userrService.addUserr(userr).getUserName());

    }

    @Test
    void updateUserr() {

        Userr userr = new Userr();
        userr.setUserId(USER_ID);
        userr.setUserName("carlo34");
        userr.setUserPassword("Pass#$$%RRE");

        Mockito.when(iUserrRepo.getById(USER_ID)).thenReturn(userr);

        userr.setUserPassword("PassN453");
        Mockito.when(iUserrRepo.save(userr)).thenReturn(userr);

        assertThat(userrService.updateUserr(userr))
                .isNotNull()
                .isEqualTo(userr);
        assertEquals("PassN453", userrService.updateUserr(userr).getUserPassword());

    }

    @Test
    void searchUserr() {

        Userr userr = new Userr();
        userr.setUserId(USER_ID);
        userr.setUserName("carlo34");
        userr.setUserPassword("Pass#$$%RRE");

        Mockito.when(iUserrRepo.existsById(USER_ID)).thenReturn(true);
        Mockito.when(iUserrRepo.getById(USER_ID)).thenReturn(userr);

        assertThat(userrService.searchUserr(USER_ID))
                .isNotNull()
                .isEqualTo(userr);
        assertEquals("carlo34", userrService.searchUserr(USER_ID).getUserName());

    }

    @Test
    void deleteUserr() {

        Userr userr = new Userr();
        userr.setUserId(USER_ID);
        userr.setUserName("carlo34");
        userr.setUserPassword("Pass#$$%RRE");

        Mockito.when(iUserrRepo.getById(USER_ID)).thenReturn(userr);
        Mockito.when(iUserrRepo.existsById(USER_ID)).thenReturn(false);

        assertNull(userrService.searchUserr(USER_ID));

    }

    @Test
    void userrs() {

        Userr userr1 = new Userr();
        userr1.setUserId(555);
        userr1.setUserName("carlo34");
        userr1.setUserPassword("Pass#$$%RRE");

        Userr userr2 = new Userr();
        userr2.setUserId(4544);
        userr2.setUserName("carlo34");
        userr2.setUserPassword("Pass#$$%RRE");

        List<Userr> userrList = new ArrayList<>();
        userrList.add(userr1);
        userrList.add(userr2);

        Mockito.when(iUserrRepo.findAll()).thenReturn(userrList);

        assertThat(userrService.userrs())
                .isNotNull()
                .isEqualTo(userrList);
        assertFalse(userrList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- UserrServiceTest Finished ---- ||");
    }

}
