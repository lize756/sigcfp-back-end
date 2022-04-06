package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Triggerr;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITriggerrController {

    ResponseEntity<Triggerr> addTriggerr(Triggerr triggerr);

    ResponseEntity<Triggerr> updateTriggerr(Triggerr triggerr, long trigId);

    ResponseEntity<Triggerr> getTriggerr(long trigId);

    ResponseEntity<HttpStatus> deleteTriggerr(long trigId);

    ResponseEntity<List<Triggerr>> getTriggerrs();

}
