package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;

import java.util.List;

public interface IUserrService {

    Userr addUserr(Userr userr);

    Userr updateUserr(long userId, Userr userr);

    Userr searchUserr(long userId);

    Userr deleteUserr(long userId);

    List<Userr> userrs();
}
