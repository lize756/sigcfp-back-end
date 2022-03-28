package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.CurriculumPdf;

import java.util.List;

public interface ICurriculumPdfService {

    CurriculumPdf addCurriculumPdf(CurriculumPdf curriculumPdf);

    CurriculumPdf updateCurriculumPdf(CurriculumPdf curriculumPdf);

    CurriculumPdf searchCurriculumPdf(long cuPdfId);

    CurriculumPdf deleteCurriculumPdf(long cuPdfId);

    List<CurriculumPdf> CurriculumPdfs();
}
