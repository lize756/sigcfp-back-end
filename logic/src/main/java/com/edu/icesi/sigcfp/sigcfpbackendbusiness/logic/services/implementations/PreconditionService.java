package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Precondition;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IPreconditionService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IPreconditionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PreconditionService implements IPreconditionService {

    IPreconditionRepo iPreconditionRepo;

    @Autowired
    public PreconditionService(IPreconditionRepo iPreconditionRepo) {
        this.iPreconditionRepo = iPreconditionRepo;
    }

    @Override
    @Transactional
    public Precondition addPrecondition(Precondition precondition) {
        if (!iPreconditionRepo.existsById(precondition.getPrecondId())) {
            return iPreconditionRepo.save(precondition);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Precondition updatePrecondition(Precondition precondition) {
        return iPreconditionRepo.save(precondition);
    }

    @Override
    @Transactional
    public Precondition searchPrecondition(long precondId) {
        if (iPreconditionRepo.existsById(precondId)) {
            return iPreconditionRepo.getById(precondId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Precondition deletePrecondition(long precondId) {
        Precondition preconditionToDelete = null;
        if (iPreconditionRepo.existsById(precondId)) {
            preconditionToDelete = iPreconditionRepo.findById(precondId).get();
            iPreconditionRepo.delete(iPreconditionRepo.getById(precondId));
        } else {
            return null;
        }
        return preconditionToDelete;
    }

    @Override
    @Transactional
    public List<Precondition> preconditions() {
        return iPreconditionRepo.findAll();
    }
}
