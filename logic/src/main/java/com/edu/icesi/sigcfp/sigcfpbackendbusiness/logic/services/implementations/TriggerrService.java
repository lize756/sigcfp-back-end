package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Triggerr;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ITriggerrService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ITriggerrRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TriggerrService implements ITriggerrService {

    ITriggerrRepo iTriggerrRepo;

    @Autowired
    public TriggerrService(ITriggerrRepo iTriggerrRepo) {
        this.iTriggerrRepo = iTriggerrRepo;
    }

    @Override
    @Transactional
    public Triggerr addTriggerr(Triggerr triggerr) {
        if (!iTriggerrRepo.existsById(triggerr.getTrigId())) {
            return iTriggerrRepo.save(triggerr);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Triggerr updateTriggerr(Triggerr triggerr) {
            return iTriggerrRepo.save(triggerr);
    }

    @Override
    @Transactional
    public Triggerr searchTriggerr(long trigId) {
        if (iTriggerrRepo.existsById(trigId)) {
            return iTriggerrRepo.getById(trigId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Triggerr deletePTriggerr(long trigId) {
        Triggerr triggerrToDelete = null;
        if (iTriggerrRepo.existsById(trigId)) {
            triggerrToDelete = iTriggerrRepo.findById(trigId).get();
            iTriggerrRepo.delete(iTriggerrRepo.getById(trigId));
        } else {
            return null;
        }
        return triggerrToDelete;
    }

    @Override
    @Transactional
    public List<Triggerr> triggerrs() {
        return iTriggerrRepo.findAll();
    }
}
