package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICityService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityService implements ICityService {

    ICityRepo iCityRepo;

    @Autowired
    public CityService(ICityRepo iCityRepo) {
        this.iCityRepo = iCityRepo;
    }

    @Override
    @Transactional
    public City addCity(City city) {
        if (!iCityRepo.existsById(city.getCityId())) {
            return iCityRepo.save(city);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public City updateCity(City city) {
        return iCityRepo.save(city);
    }

    @Override
    @Transactional
    public City searchCity(long cityId) {
        if (iCityRepo.existsById(cityId)) {
            return iCityRepo.getById(cityId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteCity(long cityId) {
        if (iCityRepo.existsById(cityId)) {
            iCityRepo.deleteById(cityId);
        }
    }

    @Override
    @Transactional
    public List<City> cities() {
        return iCityRepo.findAll();
    }
}
