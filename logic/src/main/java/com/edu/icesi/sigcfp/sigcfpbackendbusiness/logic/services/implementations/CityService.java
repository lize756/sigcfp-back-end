package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICityService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService implements ICityService {

    ICityRepo iCityRepo;

    @Autowired
    public CityService(ICityRepo iCityRepo){
        this.iCityRepo = iCityRepo;
    }

    @Override
    public City addCity(City city) {
        return null;
    }

    @Override
    public City updateCity(City city) {
        return null;
    }

    @Override
    public City searchCity(long cityId) {
        return null;
    }

    @Override
    public City deleteCity(long cityId) {
        return null;
    }

    @Override
    public List<City> cities() {
        return null;
    }
}
