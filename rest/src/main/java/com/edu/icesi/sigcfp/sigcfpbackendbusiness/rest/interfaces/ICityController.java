package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICityController {

	/**
	 * Allow add new city in the system.
	 * @param city city to add.
	 * @return a responseEntity that represent the whole HTTP response: status code, headers, and body. 
	 */
    ResponseEntity<City> addCity(City city);

    /**
	 * Allow update a city.
	 * @param cityId cityId to update.
	 * @return a responseEntity that represent the whole HTTP response: status code, headers, and body. 
	 */
    ResponseEntity<City> updateCity(long cityId,City city);

    /**
     * Allows to obtain a city through you id.
     * @param cityId id to search city
	 * @return a responseEntity that represent the whole HTTP response: status code, headers, and body. 
     */
    ResponseEntity<City> getCity(long cityId);

    /**
     * Allows delete a city through you id
     * @param cityId  id to search city
	 * @return a responseEntity that represent the whole HTTP response: status code, headers, and body. 
     */
    ResponseEntity<HttpStatus> deleteCity(long cityId);
    
    /**
     * Allows to obtain the list of cities saved in the database.
	 * @return a responseEntity that represent the whole HTTP response: status code, headers, and body. 
     */
    ResponseEntity<List<City>> getCities();
}
