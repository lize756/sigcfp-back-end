package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Permmission;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPermmissionController {

    ResponseEntity<Permmission> addPermmission(Permmission permmission);

    ResponseEntity<Permmission> updatePermmission(long permId, Permmission permmission);

    ResponseEntity<Permmission> getPermmission(long permId);

    ResponseEntity<HttpStatus> deletePermmissión(long permId);

    ResponseEntity<List<Permmission>> getPermmissions();

}
