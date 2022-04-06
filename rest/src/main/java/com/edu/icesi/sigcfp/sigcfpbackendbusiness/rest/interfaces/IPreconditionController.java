package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Precondition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPreconditionController {

    ResponseEntity<Precondition> addPrecondition(Precondition precondition);

    ResponseEntity<Precondition> updatePrecondition(long precondId, Precondition precondition);

    ResponseEntity<Precondition> getPrecondition(long precId);

    ResponseEntity<HttpStatus> deletePrecondition(long precId);

    ResponseEntity<List<Precondition>> getPreconditions();

}
