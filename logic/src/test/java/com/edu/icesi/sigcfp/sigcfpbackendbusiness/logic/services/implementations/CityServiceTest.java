package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICityRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    final static long CITY_ID = 3434;

    @Mock
    ICityRepo iCityRepo;
    @InjectMocks
    CityService cityService;
    City city;

    @BeforeAll
    static void init(){
        System.out.println("|| ---- CityServiceTest Started ---- ||");
    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.cityService = new CityService(this.iCityRepo);
    }


    @Test
    void addCity() {

        city = new City();
        city.setCityId(CITY_ID);
        city.setCityName("Cali");

        Mockito.when(iCityRepo.save(city)).thenReturn(city);
        City cityAdded = cityService.addCity(city);
        Mockito.verify(iCityRepo).save(city);

        assertNotNull(cityAdded);
        assertEquals("Cali", cityAdded.getCityName());

    }

    @Test
    void updateCity() {

        city = new City();
        city.setCityId(CITY_ID);
        city.setCityName("Medellín");

        Mockito.when(iCityRepo.save(city)).thenReturn(city);
        City cityAdded = cityService.updateCity(city);
        Mockito.verify(iCityRepo).save(city);

        assertNotNull(cityAdded);
        assertEquals("Medellín", cityAdded.getCityName());
    }

    @Test
    void searchCity() {
        city = new City();
        city.setCityId(CITY_ID);
        city.setCityName("Cartagena");

        City cityAdded = cityService.addCity(city);

        Mockito.when(iCityRepo.getById(cityAdded.getCityId())).thenReturn(cityAdded);
        City cityUpdated = cityService.searchCity(cityAdded.getCityId());
        Mockito.verify(iCityRepo).getById(cityAdded.getCityId());

        assertNotNull(cityUpdated);
        assertEquals("Cartagena", cityUpdated.getCityName());


    }

    @Test
    void deleteCity() {

        city = new City();
        city.setCityId(CITY_ID);
        city.setCityName("Bogotá");

        cityService.deleteCity(CITY_ID);
        Mockito.verify(iCityRepo, Mockito.times(1)).deleteById(CITY_ID);

        assertNull(cityService.searchCity(CITY_ID));


    }

    @Test
    void cities() {
    }
}
