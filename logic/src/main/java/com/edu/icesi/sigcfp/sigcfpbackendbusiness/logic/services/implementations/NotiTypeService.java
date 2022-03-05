package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.NotiType;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.INotiTypeService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.INotiTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NotiTypeService implements INotiTypeService {

    INotiTypeRepo iNotiTypeRepo;

    @Autowired
    public NotiTypeService(INotiTypeRepo iNotiTypeRepo) {
        this.iNotiTypeRepo = iNotiTypeRepo;
    }

    @Override
    @Transactional
    public NotiType addNotiType(NotiType notiType) {
        if (!iNotiTypeRepo.existsById(notiType.getNotiTypeId())) {
            return iNotiTypeRepo.save(notiType);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public NotiType updateNotiType(long notiTypeId, NotiType notiType) {
        if (iNotiTypeRepo.existsById(notiTypeId)) {
            return iNotiTypeRepo.save(notiType);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public NotiType searchNotiType(long notiTypeId) {
        if (iNotiTypeRepo.existsById(notiTypeId)) {
            return iNotiTypeRepo.getById(notiTypeId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public NotiType deleteNotiType(long notiTypeId) {
        NotiType notiTypeToDelete = null;
        if (iNotiTypeRepo.existsById(notiTypeId)) {
            notiTypeToDelete = iNotiTypeRepo.findById(notiTypeId).get();
            iNotiTypeRepo.delete(iNotiTypeRepo.getById(notiTypeId));
        } else {
            return null;
        }
        return notiTypeToDelete;
    }

    @Override
    @Transactional
    public List<NotiType> notiTypes() {
        return iNotiTypeRepo.findAll();
    }
}
