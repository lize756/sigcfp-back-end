package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Triggerr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITriggerrRepo extends JpaRepository<Triggerr, Long> {
    @Override
    List<Triggerr> findAll();
}
