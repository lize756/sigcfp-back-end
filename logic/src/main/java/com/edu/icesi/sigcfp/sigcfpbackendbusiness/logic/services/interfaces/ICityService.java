package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;

import java.util.List;

public interface ICityService {

    City addCity(City city);

    City updateCity(City city);

    City searchCity(long cityId);

    City deleteCity(long cityId);

    List<City> cities();
}
