package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Academicstudy;
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
        return null;
    }


    @Override
    public Academicstudy searchAcademicstudy(long acadStudId) {
        return null;
    }

    @Override
    public Academicstudy deleteAcademicstudy(long acadStudId) {
        return null;
    }

    @Override
    public List<Academicstudy> academicstudies() {
        return null;
    }

	@Override
	public Academicstudy updateAcademicstudy(Academicstudy academicstudy) {
		// TODO Auto-generated method stub
		return null;
	}

}
