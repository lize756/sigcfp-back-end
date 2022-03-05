package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Ethnicgroup;

import java.util.List;

public interface IEthnicgroupService {

    Ethnicgroup addEthnicgroup(Ethnicgroup ethnicgroup);

    Ethnicgroup updateEthnicgroup(long etgrId, Ethnicgroup ethnicgroup);

    Ethnicgroup searchEthnicgroup(long etgrId);

    Ethnicgroup deleteEthnicgroup(long etgrId);

    List<Ethnicgroup> ethnicgroups();
}
