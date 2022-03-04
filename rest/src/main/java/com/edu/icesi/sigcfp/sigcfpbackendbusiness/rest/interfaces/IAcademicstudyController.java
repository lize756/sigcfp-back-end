package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Academicstudy;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAcademicstudyController {

    ResponseEntity<Academicstudy> addAcademicstudy(Academicstudy academicstudy);

    ResponseEntity<Academicstudy> updateAcademicstudy(long acadStudId, Academicstudy academicstudy);

    ResponseEntity<Academicstudy> getAcademicstudy(long acadStudId);

    ResponseEntity<Academicstudy> deleteAcademicstudy(long acadStudId);

    ResponseEntity<List<Academicstudy>> getAcademicstudies();
}
