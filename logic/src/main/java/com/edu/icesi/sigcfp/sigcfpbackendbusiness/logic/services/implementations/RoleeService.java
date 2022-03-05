package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Rolee;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IRoleeService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IRoleeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleeService implements IRoleeService {

    IRoleeRepo iRoleeRepo;

    @Autowired
    public RoleeService(IRoleeRepo iRoleeRepo) {
        this.iRoleeRepo = iRoleeRepo;
    }

    @Override
    @Transactional
    public Rolee addRolee(Rolee rolee) {
        if (!iRoleeRepo.existsById(rolee.getRoleId())) {
            return iRoleeRepo.save(rolee);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Rolee updateRolee(long roleId, Rolee rolee) {
        if (iRoleeRepo.existsById(roleId)) {
            return iRoleeRepo.save(rolee);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Rolee searchRolee(long roled) {
        if (iRoleeRepo.existsById(roled)) {
            return iRoleeRepo.getById(roled);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Rolee deleteRolee(long roled) {
        Rolee roleeToDelete = null;
        if (iRoleeRepo.existsById(roled)) {
            roleeToDelete = iRoleeRepo.findById(roled).get();
            iRoleeRepo.delete(iRoleeRepo.getById(roled));
        } else {
            return null;
        }
        return roleeToDelete;
    }

    @Override
    @Transactional
    public List<Rolee> rolees() {
        return iRoleeRepo.findAll();
    }
}
