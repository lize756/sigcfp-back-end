package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Faculty;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IFacultyService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IFacultyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService implements IFacultyService {

    IFacultyRepo iFacultyRepo;

    @Autowired
    public FacultyService(IFacultyRepo iFacultyRepo) {
        this.iFacultyRepo = iFacultyRepo;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        return null;
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        return null;
    }

    @Override
    public Faculty searchFaculty(long facuId) {
        return null;
    }

    @Override
    public Faculty deleteFaculty(long facuId) {
        return null;
    }

    @Override
    public List<Faculty> faculties() {
        return null;
    }
}
