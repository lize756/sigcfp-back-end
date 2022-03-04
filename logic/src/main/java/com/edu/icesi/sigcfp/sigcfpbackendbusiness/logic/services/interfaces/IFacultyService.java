package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Faculty;

import java.util.List;

public interface IFacultyService {

    Faculty addFaculty(Faculty faculty);

    Faculty updateFaculty(Faculty faculty);

    Faculty searchFaculty(long facuId);

    Faculty deleteFaculty(long facuId);

    List<Faculty> faculties();
}
