package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Precondition;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPreconditionController {

    ResponseEntity<String> addPrecondition(Precondition precondition);

    ResponseEntity<String> updatePrecondition(Precondition precondition, long precondId);

    ResponseEntity<String> getPrecondition(long precondId);

    ResponseEntity<String> deletePrecondition(long precondId);

    ResponseEntity<List<Precondition>> getPreconditions();

}
