package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Noti;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.INotiService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.INotiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NotiService implements INotiService {

    INotiRepo iNotiRepo;

    @Autowired
    public NotiService(INotiRepo iNotiRepo) {
        this.iNotiRepo = iNotiRepo;
    }


    @Override
    @Transactional
    public Noti addNoti(Noti noti) {
        if (!iNotiRepo.existsById(noti.getNotiId())) {
            return iNotiRepo.save(noti);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Noti updateNoti(Noti noti) {
        return iNotiRepo.save(noti);
    }

    @Override
    @Transactional
    public Noti searchNoti(long notiId) {
        if (iNotiRepo.existsById(notiId)) {
            return iNotiRepo.getById(notiId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Noti deleteNoti(long notiId) {
        Noti notiToDelete = null;
        if (iNotiRepo.existsById(notiId)) {
            notiToDelete = iNotiRepo.findById(notiId).get();
            iNotiRepo.delete(iNotiRepo.getById(notiId));
        } else {
            return null;
        }
        return notiToDelete;
    }

    @Override
    @Transactional
    public List<Noti> notis() {
        return iNotiRepo.findAll();
    }
}
