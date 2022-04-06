package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Permmission;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Rolee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.management.relation.Role;
import java.util.List;

public interface IRoleeController {

    ResponseEntity<Rolee> addRolee(Rolee rolee);

    ResponseEntity<Rolee> updateRolee(long roleId, Rolee rolee);

    ResponseEntity<Rolee> getRolee(long roleId);

    ResponseEntity<HttpStatus> deleteRolee(long roleId);

    ResponseEntity<List<Rolee>> getRolees();

}
