package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICareerController {

    ResponseEntity<String> addCareer(Career career);

    ResponseEntity<String> updateCareer(Career career, long careId);

    ResponseEntity<String> getCareer(long careId);

    ResponseEntity<String> deleteCareer(long careId);

    ResponseEntity<List<Career>> getCareers();

}
