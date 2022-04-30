package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Person;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IUserrService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IUserrRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserrService implements IUserrService {

    IUserrRepo iUserrRepo;

    @Autowired
    public UserrService(IUserrRepo iUserrRepo) {
        this.iUserrRepo = iUserrRepo;
    }

    @Override
    @Transactional
    public Userr addUserr(Userr userr) {
        if (!iUserrRepo.existsById(userr.getUserId())) {
            return iUserrRepo.save(userr);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Userr updateUserr(Userr userr) {
        return iUserrRepo.save(userr);
    }

    @Override
    @Transactional
    public Userr searchUserr(long userId) {
        return iUserrRepo.getById(userId);
    }

    @Override
    @Transactional
    public void deleteUserr(long userId) {
        iUserrRepo.deleteById(userId);
    }

    @Override
    @Transactional
    public List<Userr> userrs() {
        return iUserrRepo.findAll();
    }

    @Override
    @Transactional()
    public Userr findUserrByUserName(String userName) {
        return iUserrRepo.findUserrByUserName(userName);
    }

    @Override
    @Transactional()
    public Company findCompanyByUserName(String userName) {
        return iUserrRepo.findCompanyByUserName(userName);
    }

    @Override
    @Transactional()
    public Person findPersonByUserName(String userName) {
        return iUserrRepo.findPersonByUserName(userName);
    }

}
