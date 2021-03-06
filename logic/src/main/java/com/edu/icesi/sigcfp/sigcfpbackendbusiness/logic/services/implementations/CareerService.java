package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICareerService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICareerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CareerService implements ICareerService {

    ICareerRepo iCareerRepo;

    @Autowired
    public CareerService(ICareerRepo iCareerRepo) {
        this.iCareerRepo = iCareerRepo;
    }

    @Override
    @Transactional
    public Career addCareer(Career career) {
        if (!iCareerRepo.existsById(career.getCareId())) {
            return iCareerRepo.save(career);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Career updateCareer(Career career) {
        return iCareerRepo.save(career);
    }

    @Override
    @Transactional
    public Career searchCareer(long careId) {
        if (iCareerRepo.existsById(careId)) {
            return iCareerRepo.getById(careId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Career deleteCareer(long careId) {
        Career careerToDelete = null;
        if (iCareerRepo.existsById(careId)) {
            careerToDelete = iCareerRepo.findById(careId).get();
            iCareerRepo.delete(iCareerRepo.getById(careId));
        } else {
            return null;
        }
        return careerToDelete;
    }

    @Override
    @Transactional
    public List<Career> careers() {
        return iCareerRepo.findAll();
    }

    @Override
    public List<Career> findCareersByFacultyFacuId(Long facuId) {
        return iCareerRepo.findCareersByFacultyFacuId(facuId);
    }
}
