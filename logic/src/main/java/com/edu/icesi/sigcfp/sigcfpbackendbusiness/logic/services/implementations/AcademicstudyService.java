package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Academicstudy;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IAcademicstudyService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IAcademicstudyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AcademicstudyService implements IAcademicstudyService {

    IAcademicstudyRepo iAcademicstudyRepo;

    @Autowired
    public AcademicstudyService(IAcademicstudyRepo iAcademicstudyRepo) {
        this.iAcademicstudyRepo = iAcademicstudyRepo;
    }


    @Override
    @Transactional
    public Academicstudy addAcademicstudy(Academicstudy academicstudy) {
        return iAcademicstudyRepo.save(academicstudy);
    }

    @Override
    @Transactional
    public Academicstudy updateAcademicstudy(Academicstudy academicstudy) {
        return iAcademicstudyRepo.save(academicstudy);
    }


    @Override
    @Transactional
    public Academicstudy searchAcademicstudy(long acadStudId) {
        if (iAcademicstudyRepo.existsById(acadStudId)) {
            return iAcademicstudyRepo.getById(acadStudId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Academicstudy deleteAcademicstudy(long acadStudId) {
        Academicstudy academicstudyToDelete = null;
        if (iAcademicstudyRepo.existsById(acadStudId)) {
            academicstudyToDelete = iAcademicstudyRepo.findById(acadStudId).get();
            iAcademicstudyRepo.delete(iAcademicstudyRepo.getById(acadStudId));
        } else {
            return null;
        }
        return academicstudyToDelete;
    }

    @Override
    @Transactional
    public List<Academicstudy> academicstudies() {
        return iAcademicstudyRepo.findAll();
    }


	@Override
	public List<Academicstudy> addAcademicStudies(List<Academicstudy> academicstudies) {
		return iAcademicstudyRepo.saveAll(academicstudies);
	}


}
