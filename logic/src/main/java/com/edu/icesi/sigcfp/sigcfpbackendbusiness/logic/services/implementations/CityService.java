package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
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
        if (!iCityRepo.existsById(city.getCityId())){
            return iCityRepo.save(city);
        }else {
            return null;
        }
    }

    @Override
    public City updateCity(long cityId, City city) {
        if (iCityRepo.existsById(cityId)){
            return iCityRepo.save(city);
        }else {
            return null;
        }
    }

    @Override
    public City searchCity(long cityId) {
        if (iCityRepo.existsById(cityId)){
            return iCityRepo.getById(cityId);
        }else {
            return null;
        }
    }

    @Override
    public City deleteCity(long cityId) {
        City cityToDelete = null;
        if (iCityRepo.existsById(cityId)){
            cityToDelete = iCityRepo.findById(cityId).get();
            iCityRepo.delete(iCityRepo.getById(cityId));
        }else{
            return null;
        }
        return cityToDelete;
    }

    @Override
    public List<City> cities() {
        return iCityRepo.findAll();
    }
}
