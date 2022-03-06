package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.InternRequest;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IInternRequestService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IInternRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InternRequestService implements IInternRequestService {

	IInternRequestRepo iInternRequestRepo;

	@Autowired
	public InternRequestService(IInternRequestRepo iInternRequestRepo) {
		this.iInternRequestRepo = iInternRequestRepo;
	}

	@Override
	@Transactional
	public InternRequest addInternRequest(InternRequest internRequest) {
		if (!iInternRequestRepo.existsById(internRequest.getInteRequId())) {
			return iInternRequestRepo.save(internRequest);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public InternRequest updateInternRequest(InternRequest internRequest) {
		return iInternRequestRepo.save(internRequest);
	}

	@Override
	@Transactional
	public InternRequest searchInternRequest(long inteRequId) {
		if (iInternRequestRepo.existsById(inteRequId)) {
			return iInternRequestRepo.getById(inteRequId);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public InternRequest deleteInternRequest(long inteRequId) {
		InternRequest internRequestToDelete = null;
		if (iInternRequestRepo.existsById(inteRequId)) {
			internRequestToDelete = iInternRequestRepo.findById(inteRequId).get();
			iInternRequestRepo.delete(iInternRequestRepo.getById(inteRequId));
		} else {
			return null;
		}
		return internRequestToDelete;
	}

	@Override
	@Transactional
	public List<InternRequest> internRequests() {
		return iInternRequestRepo.findAll();
	}
}
