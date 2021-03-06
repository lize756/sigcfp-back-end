package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.NotiType;

import java.util.List;


public interface INotiTypeService {

    NotiType addNotiType(NotiType notiType);

    NotiType updateNotiType(NotiType notiType);

    NotiType searchNotiType(long notiTypeId);

    NotiType deleteNotiType(long notiTypeId);

    List<NotiType> notiTypes();
}
