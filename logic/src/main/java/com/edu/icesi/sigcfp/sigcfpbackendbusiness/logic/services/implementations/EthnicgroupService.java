package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Ethnicgroup;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IEthnicgroupService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IEthnicgroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EthnicgroupService implements IEthnicgroupService {

    IEthnicgroupRepo iEthnicgroupRepo;

    @Autowired
    public EthnicgroupService(IEthnicgroupRepo iEthnicgroupRepo) {
        this.iEthnicgroupRepo = iEthnicgroupRepo;
    }

    @Override
    public Ethnicgroup addEthnicgroup(Ethnicgroup ethnicgroup) {
        return null;
    }

    @Override
    public Ethnicgroup updateEthnicgroup(Ethnicgroup ethnicgroup) {
        return null;
    }

    @Override
    public Ethnicgroup searchEthnicgroup(long etgrId) {
        return null;
    }

    @Override
    public Ethnicgroup deleteEthnicgroup(long etgrId) {
        return null;
    }

    @Override
    public List<Ethnicgroup> ethnicgroups() {
        return null;
    }
}
