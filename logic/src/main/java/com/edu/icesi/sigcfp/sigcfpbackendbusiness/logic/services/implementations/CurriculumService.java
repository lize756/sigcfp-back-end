package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Curriculum;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICurriculumService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICurriculumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CurriculumService implements ICurriculumService {

    ICurriculumRepo iCurriculumRepo;

    @Autowired
    public CurriculumService(ICurriculumRepo iCurriculumRepo) {
        this.iCurriculumRepo = iCurriculumRepo;
    }


    @Override
    @Transactional
    public Curriculum addCurriculum(Curriculum curriculum) {
        if (!iCurriculumRepo.existsById(curriculum.getCurrId())) {
            return iCurriculumRepo.save(curriculum);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Curriculum updateCurriculum(Curriculum curriculum) {
        return iCurriculumRepo.save(curriculum);
    }

    @Override
    @Transactional
    public Curriculum searchCurriculum(long currId) {
        if (iCurriculumRepo.existsById(currId)) {
            return iCurriculumRepo.getById(currId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Curriculum deleteCurriculum(long currId) {
        Curriculum curriculumToDelete = null;
        if (iCurriculumRepo.existsById(currId)) {
            curriculumToDelete = iCurriculumRepo.findById(currId).get();
            iCurriculumRepo.delete(iCurriculumRepo.getById(currId));
        } else {
            return null;
        }
        return curriculumToDelete;
    }

    @Override
    @Transactional
    public List<Curriculum> Curriculums() {
        return iCurriculumRepo.findAll();
    }
}
