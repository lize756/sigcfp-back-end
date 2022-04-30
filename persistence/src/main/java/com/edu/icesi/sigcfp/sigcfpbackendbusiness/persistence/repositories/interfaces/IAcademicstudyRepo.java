package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Academicstudy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAcademicstudyRepo extends JpaRepository<Academicstudy, Long> {

    @Override
    List<Academicstudy> findAll();
}
