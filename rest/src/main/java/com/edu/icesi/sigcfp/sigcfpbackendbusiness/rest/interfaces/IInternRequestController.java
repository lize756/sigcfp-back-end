package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.InternRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IInternRequestController {

    ResponseEntity<String> addInternRequest(InternRequest internRequest);

    ResponseEntity<String> updateInternRequest(InternRequest internRequest, long inteRequId);

    ResponseEntity<String> getInternRequest(long inteRequId);

    ResponseEntity<String> deleteInternRequest(long inteRequId);

    ResponseEntity<List<InternRequest>> getInternRequests();

}
