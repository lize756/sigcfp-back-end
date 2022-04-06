package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.NotiType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INotiTypeController {

    ResponseEntity<NotiType> addNotiType(NotiType notiType);

    ResponseEntity<NotiType> updateNotiType(long notiTypeId, NotiType notiType);

    ResponseEntity<NotiType> getNotiType(long notiTypeId);

    ResponseEntity<HttpStatus> deleteNotiType(long notiTypeId);

    ResponseEntity<List<NotiType>> getNotiTypes();

}
