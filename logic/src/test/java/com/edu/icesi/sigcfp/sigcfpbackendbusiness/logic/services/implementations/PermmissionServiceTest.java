package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Permmission;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Rolee;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICareerRepo;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IPermmissionRepo;
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
class PermmissionServiceTest {

    final static long PERM_ID = 3275;

    @MockBean
    IPermmissionRepo iPermmissionRepo;
    @Autowired
    PermmissionService permmissionService;

    @BeforeAll
    static void init() {
        System.out.println("|| ---- PermmissionServiceTest Started ---- ||");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addPermmission() {

        Permmission permmission = new Permmission();
        permmission.setPermId(PERM_ID);
        permmission.setPermName("LISTA COMPANIES");
        permmission.setPermIsactive("YES");
        Rolee rolee = new Rolee();
        rolee.setRoleId(4435);
        rolee.setRoleName("COORD. UBICACIÓN");
        permmission.setRolee(rolee);

        Mockito.when(iPermmissionRepo.save(permmission)).thenReturn(permmission);

        assertThat(permmissionService.addPermmission(permmission))
                .isNotNull()
                .isEqualTo(permmission);
        assertEquals("LISTA COMPANIES", permmissionService.addPermmission(permmission).getPermName());

    }

    @Test
    void updatePermmission() {

        Permmission permmission = new Permmission();
        permmission.setPermId(PERM_ID);
        permmission.setPermName("LISTA COMPANIES");
        permmission.setPermIsactive("YES");
        Rolee rolee = new Rolee();
        rolee.setRoleId(4435);
        rolee.setRoleName("COORD. UBICACIÓN");
        permmission.setRolee(rolee);

        Mockito.when(iPermmissionRepo.getById(PERM_ID)).thenReturn(permmission);

        permmission.setPermIsactive("NO");
        Mockito.when(iPermmissionRepo.save(permmission)).thenReturn(permmission);

        assertThat(permmissionService.updatePermmission(permmission))
                .isNotNull()
                .isEqualTo(permmission);
        assertEquals("NO", permmissionService.updatePermmission(permmission).getPermIsactive());

    }

    @Test
    void searchPermmission() {

        Permmission permmission = new Permmission();
        permmission.setPermId(PERM_ID);
        permmission.setPermName("LISTA COMPANIES");
        permmission.setPermIsactive("YES");
        Rolee rolee = new Rolee();
        rolee.setRoleId(4435);
        rolee.setRoleName("COORD. UBICACIÓN");
        permmission.setRolee(rolee);

        Mockito.when(iPermmissionRepo.existsById(PERM_ID)).thenReturn(true);
        Mockito.when(iPermmissionRepo.getById(PERM_ID)).thenReturn(permmission);

        assertThat(permmissionService.searchPermmission(PERM_ID))
                .isNotNull()
                .isEqualTo(permmission);
        assertEquals("LISTA COMPANIES", permmissionService.searchPermmission(PERM_ID).getPermName());


    }

    @Test
    void deletePermmission() {

        Permmission permmission = new Permmission();
        permmission.setPermId(PERM_ID);
        permmission.setPermName("LISTA COMPANIES");
        permmission.setPermIsactive("YES");
        Rolee rolee = new Rolee();
        rolee.setRoleId(4435);
        rolee.setRoleName("COORD. UBICACIÓN");
        permmission.setRolee(rolee);

        Mockito.when(iPermmissionRepo.getById(PERM_ID)).thenReturn(permmission);
        Mockito.when(iPermmissionRepo.existsById(PERM_ID)).thenReturn(false);

        assertNull(permmissionService.searchPermmission(PERM_ID));

    }

    @Test
    void permmissions() {

        Permmission permmission1 = new Permmission();
        permmission1.setPermId(PERM_ID);
        permmission1.setPermName("LISTA COMPANIES");
        permmission1.setPermIsactive("YES");
        Rolee rolee1 = new Rolee();
        rolee1.setRoleId(4435);
        rolee1.setRoleName("COORD. UBICACIÓN");
        permmission1.setRolee(rolee1);

        Permmission permmission2 = new Permmission();
        permmission2.setPermId(PERM_ID);
        permmission2.setPermName("LISTA INTERN REQUEST");
        permmission2.setPermIsactive("NO");
        Rolee rolee2 = new Rolee();
        rolee2.setRoleId(4435);
        rolee2.setRoleName("COORD. PROMOCION");
        permmission2.setRolee(rolee2);

        List<Permmission> permmissionList = new ArrayList<>();
        permmissionList.add(permmission1);
        permmissionList.add(permmission2);

        Mockito.when(iPermmissionRepo.findAll()).thenReturn(permmissionList);

        assertThat(permmissionService.permmissions())
                .isNotNull()
                .isEqualTo(permmissionList);
        assertFalse(permmissionList.isEmpty());

    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void finish(){
        System.out.println("|| ---- PermmissionServiceTest Finished ---- ||");
    }


}
