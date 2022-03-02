package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Triggerr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ITriggerrRepo extends JpaRepository<Triggerr, Long> {
    @Override
    List<Triggerr> findAll();
}
