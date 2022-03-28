package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Rolee;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IRoleeRepo;
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
class RoleeServiceTest {

    final static long ROLE_ID = 5283;

    @MockBean
    IRoleeRepo iRoleeRepo;
    @Autowired
    RoleeService roleeService;

    @BeforeAll
    static void init() {
        System.out.println("|| ---- RoleeServiceTest Started ---- ||");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addRolee() {

        Rolee rolee = new Rolee();
        rolee.setRoleId(ROLE_ID);
        rolee.setRoleName("COORD. TALENTOS A1");

        Mockito.when(iRoleeRepo.save(rolee)).thenReturn(rolee);

        assertThat(roleeService.addRolee(rolee))
                .isNotNull()
                .isEqualTo(rolee);
        assertEquals("COORD. TALENTOS A1", roleeService.addRolee(rolee).getRoleName());

    }

    @Test
    void updateRolee() {

        Rolee rolee = new Rolee();
        rolee.setRoleId(ROLE_ID);
        rolee.setRoleName("COORD. TALENTOS A1");

        Mockito.when(iRoleeRepo.getById(ROLE_ID)).thenReturn(rolee);

        rolee.setRoleName("EGRESADO");
        Mockito.when(iRoleeRepo.save(rolee)).thenReturn(rolee);

        assertThat(roleeService.updateRolee(rolee))
                .isNotNull()
                .isEqualTo(rolee);
        assertEquals("EGRESADO", roleeService.updateRolee(rolee).getRoleName());

    }

    @Test
    void searchRolee() {

        Rolee rolee = new Rolee();
        rolee.setRoleId(ROLE_ID);
        rolee.setRoleName("COORD. TALENTOS A1");

        Mockito.when(iRoleeRepo.existsById(ROLE_ID)).thenReturn(true);
        Mockito.when(iRoleeRepo.getById(ROLE_ID)).thenReturn(rolee);

        assertThat(roleeService.searchRolee(ROLE_ID))
                .isNotNull()
                .isEqualTo(rolee);
        assertEquals("COORD. TALENTOS A1", roleeService.searchRolee(ROLE_ID).getRoleName());

    }

    @Test
    void deleteRolee() {

        Rolee rolee = new Rolee();
        rolee.setRoleId(ROLE_ID);
        rolee.setRoleName("COORD. TALENTOS A1");

        Mockito.when(iRoleeRepo.getById(ROLE_ID)).thenReturn(rolee);
        Mockito.when(iRoleeRepo.existsById(ROLE_ID)).thenReturn(false);

        assertNull(roleeService.searchRolee(ROLE_ID));

    }

    @Test
    void rolees() {

        Rolee rolee1 = new Rolee();
        rolee1.setRoleId(53535);
        rolee1.setRoleName("COORD. TALENTOS A1");

        Rolee rolee2 = new Rolee();
        rolee2.setRoleId(555);
        rolee2.setRoleName("COORD. TALENTOS A1");

        List<Rolee> roleeList = new ArrayList<>();
        roleeList.add(rolee1);
        roleeList.add(rolee2);

        Mockito.when(iRoleeRepo.findAll()).thenReturn(roleeList);

        assertThat(roleeService.rolees())
                .isNotNull()
                .isEqualTo(roleeList);
        assertFalse(roleeList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- RoleeServiceTest Finished ---- ||");
    }

}
