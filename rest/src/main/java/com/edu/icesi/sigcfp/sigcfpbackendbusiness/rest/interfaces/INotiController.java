package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Noti;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INotiController {

    ResponseEntity<String> addNoti(Noti noti);

    ResponseEntity<String> updateNoti(Noti noti, long notiId);

    ResponseEntity<String> getNoti(long notiId);

    ResponseEntity<String> deleteNoti(long notiId);

    ResponseEntity<List<Noti>> getNotis();

}
