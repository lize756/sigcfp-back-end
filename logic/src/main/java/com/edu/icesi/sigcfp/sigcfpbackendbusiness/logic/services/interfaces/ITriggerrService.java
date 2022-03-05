package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Triggerr;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITriggerrService {
    Triggerr addTriggerr(Triggerr triggerr);

    Triggerr updateTriggerr(long trigId, Triggerr triggerr);

    Triggerr searchTriggerr(long trigId);

    Triggerr deletePTriggerr(long trigId);

    List<Triggerr> triggerrs();
}
