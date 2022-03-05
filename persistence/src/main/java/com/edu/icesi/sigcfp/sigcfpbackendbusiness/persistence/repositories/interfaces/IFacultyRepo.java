package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFacultyRepo extends JpaRepository<Faculty, Long> {
    @Override
    List<Faculty> findAll();
}
