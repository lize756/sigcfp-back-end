package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.NotiType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INotiTypeController {

    ResponseEntity<String> addNotiType(NotiType notiType);

    ResponseEntity<String> updateNotiType(NotiType notiType, long notiTypeId);

    ResponseEntity<String> getNotiType(long notiTypeId);

    ResponseEntity<String> deleteNotiType(long notiTypeId);

    ResponseEntity<List<NotiType>> getNotiTypes();

}
