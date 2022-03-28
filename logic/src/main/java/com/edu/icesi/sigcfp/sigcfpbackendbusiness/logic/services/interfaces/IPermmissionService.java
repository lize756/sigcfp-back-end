package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Permmission;

import java.util.List;

public interface IPermmissionService {

    Permmission addPermmission(Permmission permmission);

    Permmission updatePermmission(Permmission permmission);

    Permmission searchPermmission(long permId);

    Permmission deletePermmission(long permId);

    List<Permmission> permmissions();
}
