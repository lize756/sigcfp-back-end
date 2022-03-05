package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Rolee;

import java.util.List;

public interface IRoleeService {

    Rolee addRolee(Rolee rolee);

    Rolee updateRolee(long roleId, Rolee rolee);

    Rolee searchRolee(long roled);

    Rolee deleteRolee(long roled);

    List<Rolee> rolees();
}
