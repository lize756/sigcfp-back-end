package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.InternRequest;

import java.util.List;

public interface IInternRequestService {

    InternRequest addInternRequest(InternRequest internRequest);

    InternRequest updateInternRequest(InternRequest internRequest);

    InternRequest searchInternRequest(long inteRequId);

    InternRequest deleteInternRequest(long inteRequId);

    List<InternRequest> internRequests();


    List<InternRequest> findInternRequestsByCompany(long compId);

    int countInternRequestByCompanyId(long compId);

    List<InternRequest> findInternRequestsByCompanyCompId(long compId);

    List<InternRequest> findInternRequestsByCareId(long careId);


}
