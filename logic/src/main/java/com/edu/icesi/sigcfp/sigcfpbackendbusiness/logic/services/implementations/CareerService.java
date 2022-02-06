package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICareerService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICareerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerService implements ICareerService {

    ICareerRepo iCareerRepo;

    @Autowired
    public CareerService(ICareerRepo iCareerRepo) {
        this.iCareerRepo = iCareerRepo;
    }

    @Override
    public Career addCareer(Career career) {
        return null;
    }

    @Override
    public Career updateCareer(Career career) {
        return null;
    }

    @Override
    public Career searchCareer(long careId) {
        return null;
    }

    @Override
    public Career deleteCareer(long careId) {
        return null;
    }

    @Override
    public List<Career> careers() {
        return null;
    }
}
