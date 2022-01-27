package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.InternRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInternRequestRepo extends CrudRepository<InternRequest, Long> {

    List<InternRequest> findAll();

    List<InternRequest> findInternRequestsByInteRequIsinprocess(String isInProcess);
}
