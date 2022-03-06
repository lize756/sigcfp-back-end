package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Academicstudy;

import java.util.List;

public interface IAcademicstudyService {

    Academicstudy addAcademicstudy(Academicstudy academicstudy);

    Academicstudy updateAcademicstudy(long acadStudId, Academicstudy academicstudy);

    Academicstudy searchAcademicstudy(long acadStudId);

    Academicstudy deleteAcademicstudy(long acadStudId);

    List<Academicstudy> academicstudies();
}
