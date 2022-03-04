package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Academicstudy;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IAcademicstudyService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IAcademicstudyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicstudyService implements IAcademicstudyService {

    IAcademicstudyRepo iAcademicstudyRepo;

    @Autowired
    public AcademicstudyService(IAcademicstudyRepo iAcademicstudyRepo) {
        this.iAcademicstudyRepo = iAcademicstudyRepo;
    }

    @Override
    public Academicstudy addAcademicstudy(Academicstudy academicstudy) {
        if (!iAcademicstudyRepo.existsById(academicstudy.getAcadStudId())){
            return iAcademicstudyRepo.save(academicstudy);
        }else {
            return null;
        }
    }

    @Override
    public Academicstudy updateAcademicstudy(long acadStudId, Academicstudy academicstudy) {
        if (iAcademicstudyRepo.existsById(acadStudId)){
            return  iAcademicstudyRepo.save(academicstudy);
        }else {
            return null;
        }
    }

    @Override
    public Academicstudy searchAcademicstudy(long acadStudId) {
        if (iAcademicstudyRepo.existsById(acadStudId)){
            return iAcademicstudyRepo.getById(acadStudId);
        }else {
            return null;
        }
    }

    @Override
    public Academicstudy deleteAcademicstudy(long acadStudId) {
        Academicstudy academicstudyToDelete = null;
        if (iAcademicstudyRepo.existsById(acadStudId)){
            academicstudyToDelete = iAcademicstudyRepo.findById(acadStudId).get();
            iAcademicstudyRepo.delete(iAcademicstudyRepo.getById(acadStudId));
        }else{
            return null;
        }
        return academicstudyToDelete;
    }

    @Override
    public List<Academicstudy> academicstudies() {
        return iAcademicstudyRepo.findAll();
    }
}
