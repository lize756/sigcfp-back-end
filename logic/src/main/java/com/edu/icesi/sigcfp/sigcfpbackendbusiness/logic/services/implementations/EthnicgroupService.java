package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Ethnicgroup;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IEthnicgroupService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IEthnicgroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EthnicgroupService implements IEthnicgroupService {

    IEthnicgroupRepo iEthnicgroupRepo;

    @Autowired
    public EthnicgroupService(IEthnicgroupRepo iEthnicgroupRepo) {
        this.iEthnicgroupRepo = iEthnicgroupRepo;
    }

    @Override
    @Transactional
    public Ethnicgroup addEthnicgroup(Ethnicgroup ethnicgroup) {
        if (!iEthnicgroupRepo.existsById(ethnicgroup.getEtgrId())) {
            return iEthnicgroupRepo.save(ethnicgroup);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Ethnicgroup updateEthnicgroup(long etgrId, Ethnicgroup ethnicgroup) {
        if (iEthnicgroupRepo.existsById(etgrId)) {
            return iEthnicgroupRepo.save(ethnicgroup);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Ethnicgroup searchEthnicgroup(long etgrId) {
        if (iEthnicgroupRepo.existsById(etgrId)) {
            return iEthnicgroupRepo.getById(etgrId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Ethnicgroup deleteEthnicgroup(long etgrId) {
        Ethnicgroup ethnicgroupToDelete = null;
        if (iEthnicgroupRepo.existsById(etgrId)) {
            ethnicgroupToDelete = iEthnicgroupRepo.findById(etgrId).get();
            iEthnicgroupRepo.delete(iEthnicgroupRepo.getById(etgrId));
        } else {
            return null;
        }
        return ethnicgroupToDelete;
    }

    @Override
    @Transactional
    public List<Ethnicgroup> ethnicgroups() {
        return iEthnicgroupRepo.findAll();
    }
}
