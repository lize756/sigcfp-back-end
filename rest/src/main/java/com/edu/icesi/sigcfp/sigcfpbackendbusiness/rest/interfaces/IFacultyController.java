package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Faculty;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFacultyController {

    ResponseEntity<Faculty> addFaculty(Faculty faculty);

    ResponseEntity<Faculty> updateFaculty( long facuId, Faculty faculty);

    ResponseEntity<Faculty> getFaculty(long facuId);

    ResponseEntity<Faculty> deleteContact(long facuId);

    ResponseEntity<List<Faculty>> getFaculties();
}
