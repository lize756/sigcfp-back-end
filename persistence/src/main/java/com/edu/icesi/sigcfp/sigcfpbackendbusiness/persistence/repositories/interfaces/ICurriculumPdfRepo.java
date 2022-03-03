package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.CurriculumPdf;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ICurriculumPdfRepo extends JpaRepository<CurriculumPdf, Long> {
    @Override
    List<CurriculumPdf> findAll();
}
