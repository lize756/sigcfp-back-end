package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICityRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CityControllerTest {

    final static long CITY_ID = 1L;
    TestRestTemplate restTemplate = new TestRestTemplate();
    @LocalServerPort
    private int port;
    @MockBean
    private ICityRepo iCityRepo;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCity() throws Exception {

        // Given
        City city = new City();
        city.setCityId(CITY_ID);
        city.setCityName("Medellín");

        when(iCityRepo.save(city)).thenReturn(city);

        // When
        ResponseEntity<City> cityResponseEntity = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/cities/add", city, City.class
        );

        // /then
        assertEquals(201, cityResponseEntity.getStatusCodeValue());

    }

    @Test
    void updateCity() {

        // given
        City city = new City();
        city.setCityId(CITY_ID);
        city.setCityName("Cali");
        when(iCityRepo.save(city)).thenReturn(city);

        // then
        this.restTemplate.put(
                "http://localhost:" + port + "/cities/update/" + CITY_ID, city
        );

    }

    @Test
    void getCity() {
        // given
        City city = new City();
        city.setCityId(CITY_ID);
        city.setCityName("Medellín");

        Mockito.when(iCityRepo.existsById(CITY_ID)).thenReturn(true);
        Mockito.when(iCityRepo.getById(CITY_ID)).thenReturn(city);

        // when
        ResponseEntity<City> cityResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/cities/" + CITY_ID, City.class);

        // then
        assertThat(cityResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void deleteCity() {

        // given
        City city = new City();
        city.setCityId(CITY_ID);
        city.setCityName("Medellín");

        Mockito.when(iCityRepo.getById(CITY_ID)).thenReturn(city);
        Mockito.when(iCityRepo.existsById(CITY_ID)).thenReturn(false);

        // when
        restTemplate.delete("http://localhost:" + port + "/cities/" + CITY_ID);
        ResponseEntity<City> cityResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/cities/" + CITY_ID, City.class);

        // then
        assertThat(cityResponseEntity.getStatusCode()).isNotEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void getCities() {

        // given
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

        // when
        ResponseEntity<City[]> cityResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/cities", City[].class);

        // then
        assertThat(cityResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(Objects.requireNonNull(cityResponseEntity.getBody())[0].getCityName())
                .isNotNull();

    }
}
