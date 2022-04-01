package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CityControllerTest {

    final static long CITY_ID = 3434;

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void addCity() throws Exception {

        City city = new City();
        city.setCityId(CITY_ID);
        city.setCityName("Medellín");

        ResponseEntity<City> cityResponseEntity = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/cities/add", city, City.class
        );
        assertEquals(201, cityResponseEntity.getStatusCodeValue());
        assertEquals("Medellín",cityResponseEntity.getBody().getCityName());

    }

    @Test
    void updateCity() {


    }

    @Test
    void getCity() {
    }

    @Test
    void deleteCity() {
    }

    @Test
    void getCities() {
    }
}
