package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

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
        if (iUserrRepo.existsById(userId)) {
            return iUserrRepo.getById(userId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Userr deleteUserr(long userId) {
        Userr userrToDelete = null;
        if (iUserrRepo.existsById(userId)) {
            userrToDelete = iUserrRepo.findById(userId).get();
            iUserrRepo.delete(iUserrRepo.getById(userId));
        } else {
            return null;
        }
        return userrToDelete;
    }

    @Override
    @Transactional
    public List<Userr> userrs() {
        return iUserrRepo.findAll();
    }


    @Override
    public Userr findUserrByUserEmail(String userEmail) {
        return iUserrRepo.findUserrByUserEmail( userEmail);
    }

    @Override
    public Userr findUserrByUserName(String userName) {
        return iUserrRepo.findUserrByUserName( userName);
    }
}
