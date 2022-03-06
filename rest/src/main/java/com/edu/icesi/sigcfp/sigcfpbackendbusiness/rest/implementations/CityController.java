package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICityService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.ICityController;

@RestController()
@RequestMapping("/city")
@CrossOrigin(origins = "*")
//@PreAuthorize("")
public class CityController implements ICityController {

	private ICityService iCityService;

	@Autowired
	public CityController(ICityService iCityService) {
		this.iCityService = iCityService;
	}

	@Override
	@PostMapping("/add")
	public ResponseEntity<City> addCity(@RequestBody City city) {
		try {
			City _ciCity = iCityService.addCity(city);
			return new ResponseEntity<City>(_ciCity, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@PutMapping("/update/{cityId}")
	public ResponseEntity<City> updateCity(@PathVariable("cityId") long cityId) {
		Optional<City> cityOpt = Optional.of(iCityService.searchCity(cityId));
		if (cityOpt.isPresent()) {
			City _city = cityOpt.get();
			return new ResponseEntity<>(iCityService.updateCity(_city), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@GetMapping("/{cityId}")
	public ResponseEntity<City> getCity(@PathVariable("cityId") long cityId) {
		Optional<City> cityOpt = Optional.of(iCityService.searchCity(cityId));
		if (cityOpt.isPresent()) {
			return new ResponseEntity<>(cityOpt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@DeleteMapping("/{cityId}")
	public ResponseEntity<HttpStatus> deleteCity(@PathVariable("cityId") long cityId) {
		try {
			iCityService.deleteCity(cityId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/cities")
	public ResponseEntity<List<City>> getCities() {
		try {
			List<City> cities = iCityService.cities();
			if (cities.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(cities, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
