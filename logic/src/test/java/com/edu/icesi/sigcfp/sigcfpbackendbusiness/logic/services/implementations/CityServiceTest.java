package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICityRepo;
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
class CityServiceTest {

    final static long CITY_ID = 3434;

    @MockBean
    ICityRepo iCityRepo;
    @Autowired
    CityService cityService;
    //City city;

    @BeforeAll
    static void init(){
        System.out.println("|| ---- CityServiceTest Started ---- ||");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCity() {

        City city = new City();
        city.setCityId(CITY_ID);
        city.setCityName("Cali");

        Mockito.when(iCityRepo.save(city)).thenReturn(city);

        assertThat(cityService.addCity(city))
                .isNotNull()
                .isEqualTo(city);
    }

    @Test
    void updateCity() {

        City city = new City();
        city.setCityId(CITY_ID);
        city.setCityName("Medellín");

        Mockito.when(iCityRepo.getById(CITY_ID)).thenReturn(city);

        city.setCityName("Pasto");
        Mockito.when(iCityRepo.save(city)).thenReturn(city);

        assertThat(cityService.updateCity(city))
                .isNotNull()
                .isEqualTo(city);
        assertEquals("Pasto", cityService.updateCity(city).getCityName());
    }

    @Test
    void searchCity() {

        City city = new City();
        city.setCityId(CITY_ID);
        city.setCityName("Cartagena");

        Mockito.when(iCityRepo.existsById(CITY_ID)).thenReturn(true);
        Mockito.when(iCityRepo.getById(CITY_ID)).thenReturn(city);

        assertThat(cityService.searchCity(CITY_ID))
                .isNotNull()
                .isEqualTo(city);
        assertEquals("Cartagena", cityService.searchCity(CITY_ID).getCityName());
    }

    @Test
    void deleteCity() {

        City city = new City();
        city.setCityId(CITY_ID);
        city.setCityName("Bogotá");

        Mockito.when(iCityRepo.getById(CITY_ID)).thenReturn(city);
        Mockito.when(iCityRepo.existsById(CITY_ID)).thenReturn(false);

        assertNull(cityService.searchCity(CITY_ID));
    }

    @Test
    void cities() {

        City city1 = new City();
        city1.setCityId(CITY_ID);
        city1.setCityName("Cali");

        City city2 = new City();
        city2.setCityId(CITY_ID);
        city2.setCityName("Cartagena");

        List<City> cityList = new ArrayList<>();
        cityList.add(city1);
        cityList.add(city2);

        Mockito.when(iCityRepo.findAll()).thenReturn(cityList);

        assertThat(cityService.cities())
                .isNotNull()
                .isEqualTo(cityList);
    }

    @AfterEach
    void tearDown() {}

    @AfterAll
    static void finish(){
        System.out.println("|| ---- CityServiceTest Finished ---- ||");
    }


}
