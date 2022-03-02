package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICareerController {

    ResponseEntity<Career> addCareer(Career career);

    ResponseEntity<Career> updateCareer(long careId, Career career);

    ResponseEntity<Career> getCareer(long careId);

    ResponseEntity<Career> deleteCareer(long careId);

    ResponseEntity<List<Career>> getCareers();

}
