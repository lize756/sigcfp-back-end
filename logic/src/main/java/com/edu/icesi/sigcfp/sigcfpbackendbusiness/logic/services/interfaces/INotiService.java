package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Noti;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface INotiService {

    Noti addNoti(Noti noti);

    Noti updateNoti(long notiId, Noti noti);

    Noti searchNoti(long notiId);

    Noti deleteNoti(long notiId);

    List<Noti> notis();
}
