package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Triggerr;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITriggerrController {

    ResponseEntity<String> addTriggerr(Triggerr triggerr);

    ResponseEntity<String> updateTriggerr(Triggerr triggerr, long trigId);

    ResponseEntity<String> getTriggerr(long trigId);

    ResponseEntity<String> deleteTriggerr(long trigId);

    ResponseEntity<List<Triggerr>> getTriggerrs();

}
