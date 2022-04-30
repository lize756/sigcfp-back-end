package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Triggerr;

import java.util.List;

public interface ITriggerrService {
    Triggerr addTriggerr(Triggerr triggerr);

    Triggerr updateTriggerr(Triggerr triggerr);

    Triggerr searchTriggerr(long trigId);

    Triggerr deletePTriggerr(long trigId);

    List<Triggerr> triggerrs();
}
