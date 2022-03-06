package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.CurriculumPdf;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICurriculumPdfService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICurriculumPdfRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CurriculumPdfService implements ICurriculumPdfService {

    ICurriculumPdfRepo iCurriculumPdfRepo;

    @Autowired
    public CurriculumPdfService(ICurriculumPdfRepo iCurriculumPdfRepo) {
        this.iCurriculumPdfRepo = iCurriculumPdfRepo;
    }


    @Override
    @Transactional
    public CurriculumPdf addCurriculumPdf(CurriculumPdf curriculumPdf) {
        if (!iCurriculumPdfRepo.existsById(curriculumPdf.getCuPdfId())) {
            return iCurriculumPdfRepo.save(curriculumPdf);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public CurriculumPdf updateCurriculumPdf(long cuPdfId, CurriculumPdf curriculumPdf) {
        if (iCurriculumPdfRepo.existsById(cuPdfId)) {
            return iCurriculumPdfRepo.save(curriculumPdf);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public CurriculumPdf searchCurriculumPdf(long cuPdfId) {
        if (iCurriculumPdfRepo.existsById(cuPdfId)) {
            return iCurriculumPdfRepo.getById(cuPdfId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public CurriculumPdf deleteCurriculumPdf(long cuPdfId) {
        CurriculumPdf curriculumPdfToDelete = null;
        if (iCurriculumPdfRepo.existsById(cuPdfId)) {
            curriculumPdfToDelete = iCurriculumPdfRepo.findById(cuPdfId).get();
            iCurriculumPdfRepo.delete(iCurriculumPdfRepo.getById(cuPdfId));
        } else {
            return null;
        }
        return curriculumPdfToDelete;
    }

    @Override
    @Transactional
    public List<CurriculumPdf> CurriculumPdfs() {
        return iCurriculumPdfRepo.findAll();
    }
}
