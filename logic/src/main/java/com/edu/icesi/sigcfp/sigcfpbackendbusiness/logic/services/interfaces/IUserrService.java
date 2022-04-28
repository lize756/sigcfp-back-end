package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Person;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;

import java.util.List;

public interface IUserrService {

    Userr addUserr(Userr userr);

    Userr updateUserr(Userr userr);

    Userr searchUserr(long userId);

    void deleteUserr(long userId);

    List<Userr> userrs();

    Userr findUserrByUserName(String userName);

    Company findCompanyByUserName(String userName);

    Person findPersonByUserName(String userName);
}
