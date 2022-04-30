package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Faculty;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IFacultyService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IFacultyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FacultyService implements IFacultyService {

    IFacultyRepo iFacultyRepo;

    @Autowired
    public FacultyService(IFacultyRepo iFacultyRepo) {
        this.iFacultyRepo = iFacultyRepo;
    }

    @Override
    @Transactional
    public Faculty addFaculty(Faculty faculty) {
        if (!iFacultyRepo.existsById(faculty.getFacuId())) {
            return iFacultyRepo.save(faculty);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Faculty updateFaculty(Faculty faculty) {
        return iFacultyRepo.save(faculty);
    }

    @Override
    @Transactional
    public Faculty searchFaculty(long facuId) {
        if (iFacultyRepo.existsById(facuId)) {
            return iFacultyRepo.getById(facuId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Faculty deleteFaculty(long facuId) {
        Faculty facultyToDelete = null;
        if (iFacultyRepo.existsById(facuId)) {
            facultyToDelete = iFacultyRepo.findById(facuId).get();
            iFacultyRepo.delete(iFacultyRepo.getById(facuId));
        } else {
            return null;
        }
        return facultyToDelete;
    }

    @Override
    @Transactional
    public List<Faculty> faculties() {
        return iFacultyRepo.findAll();
    }
}
