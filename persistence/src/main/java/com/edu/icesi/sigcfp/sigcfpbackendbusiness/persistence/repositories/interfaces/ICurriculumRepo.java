package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICurriculumRepo extends JpaRepository<Curriculum, Long> {
    @Override
    List<Curriculum> findAll();
}
