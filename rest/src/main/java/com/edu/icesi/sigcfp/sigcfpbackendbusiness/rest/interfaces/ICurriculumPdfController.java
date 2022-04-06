package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Curriculum;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.CurriculumPdf;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICurriculumPdfController {

    ResponseEntity<CurriculumPdf> addCurriculumPdf(CurriculumPdf curriculumPdf);

    ResponseEntity<CurriculumPdf> updateCurriculumPdf(long cuPdfId, CurriculumPdf curriculumPdf);

    ResponseEntity<CurriculumPdf> getCurriculumPdf(long cuPdfId);

    ResponseEntity<HttpStatus> deleteCurriculumPdf(long cuPdfId);

    ResponseEntity<List<CurriculumPdf>> getCurriculumPdfs();

}
