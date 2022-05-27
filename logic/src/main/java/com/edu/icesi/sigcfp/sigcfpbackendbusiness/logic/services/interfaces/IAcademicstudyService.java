package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Academicstudy;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAcademicstudyService {

	Academicstudy addAcademicstudy(Academicstudy academicstudy);

	Academicstudy updateAcademicstudy(Academicstudy academicstudy);

	Academicstudy searchAcademicstudy(long acadStudId);

	Academicstudy deleteAcademicstudy(long acadStudId);

	List<Academicstudy> academicstudies();

	List<Academicstudy> addAcademicStudies( List<Academicstudy> academicstudies);
}
