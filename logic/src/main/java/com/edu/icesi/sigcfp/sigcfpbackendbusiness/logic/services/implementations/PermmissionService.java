package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Permmission;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IPermmissionService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IPermmissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PermmissionService implements IPermmissionService {

    IPermmissionRepo iPermmissionRepo;

    @Autowired
    public PermmissionService(IPermmissionRepo iPermmissionRepo) {
        this.iPermmissionRepo = iPermmissionRepo;
    }


    @Override
    @Transactional
    public Permmission addPermmission(Permmission permmission) {
        if (!iPermmissionRepo.existsById(permmission.getPermId())) {
            return iPermmissionRepo.save(permmission);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Permmission updatePermmission(Permmission permmission) {
            return iPermmissionRepo.save(permmission);
    }

    @Override
    @Transactional
    public Permmission searchPermmission(long permId) {
        if (iPermmissionRepo.existsById(permId)) {
            return iPermmissionRepo.getById(permId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Permmission deletePermmission(long permId) {
        Permmission permmissionToDelete = null;
        if (iPermmissionRepo.existsById(permId)) {
            permmissionToDelete = iPermmissionRepo.findById(permId).get();
            iPermmissionRepo.delete(iPermmissionRepo.getById(permId));
        } else {
            return null;
        }
        return permmissionToDelete;
    }

    @Override
    @Transactional
    public List<Permmission> permmissions() {
        return iPermmissionRepo.findAll();
    }
}
