package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICityController {

    ResponseEntity<City> addCity(City city);

    ResponseEntity<City> updateCity(long cityId, City city);

    ResponseEntity<City> getCity(long cityId);

    ResponseEntity<City> deleteCity(long cityId);

    ResponseEntity<List<City>> getCities();
}
