package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.CurriculumPdf;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICurriculumPdfController {

    ResponseEntity<CurriculumPdf> addCurriculumPdf(MultipartFile multipartFile);

    ResponseEntity<CurriculumPdf> updateCurriculumPdf(long cuPdfId, CurriculumPdf curriculumPdf);

    ResponseEntity<CurriculumPdf> getCurriculumPdf(long cuPdfId);

    ResponseEntity<HttpStatus> deleteCurriculumPdf(long cuPdfId);

    ResponseEntity<List<CurriculumPdf>> getCurriculumPdfs();
    
    /**
     * This method allow upload a file in the system and save the file name in the database
     * @param multipartFile correspond to file uploaded
     * @return respond 
     */
    ResponseEntity<String> uploadFile(MultipartFile multipartFile);
}
