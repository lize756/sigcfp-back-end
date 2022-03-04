package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.InternRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IInternRequestRepo extends JpaRepository<InternRequest, Long> {

    @Override
    List<InternRequest> findAll();
    List<InternRequest> findInternRequestsByInteRequIsinprocess(String isInProcess);
}
