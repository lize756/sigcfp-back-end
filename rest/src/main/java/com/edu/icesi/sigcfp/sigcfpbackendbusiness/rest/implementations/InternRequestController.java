package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.InternRequest;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IInternRequestService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.IInternRequestController;


@RestController()
@RequestMapping("/api/internRequests")
@CrossOrigin(origins = "*")
//@PreAuthorize("")
public class InternRequestController implements IInternRequestController {

	private IInternRequestService iInternRequestService;

	@Autowired
	public InternRequestController(IInternRequestService iInternRequestService) {
		this.iInternRequestService = iInternRequestService;
	}

	@Override
	@PostMapping("/add")
	public ResponseEntity<InternRequest> addInternRequest(@RequestBody InternRequest internRequest) {
		try {
			InternRequest _inteRequest = iInternRequestService.addInternRequest(internRequest);
			List<Career> careers =internRequest.getCareers();
			System.out.println("careers"+"#################################");
			System.out.println("Identificador: "+ internRequest.getInteRequId());
			for (Career career : careers) {
				System.out.println(career.getCareName());
				
			}
			_inteRequest.setCareers(careers);
			return new ResponseEntity<InternRequest>(_inteRequest, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@PutMapping("/update/{inteRequId}")
	public ResponseEntity<InternRequest> updateInternRequest(@PathVariable("inteRequeId") long inteRequId, @RequestBody InternRequest internRequest) {
		Optional<InternRequest> inteRequestOptional = Optional
				.of(iInternRequestService.searchInternRequest(inteRequId));
		if (inteRequestOptional.isPresent()) {
			return new ResponseEntity<>(iInternRequestService.updateInternRequest(internRequest), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@GetMapping("/{inteRequeId}")
	public ResponseEntity<InternRequest> getInternRequest(@PathVariable("inteRequeId") long inteRequId) {

		Optional<InternRequest> inteRequestOptional = Optional
				.of(iInternRequestService.searchInternRequest(inteRequId));
		if (inteRequestOptional.isPresent()) {
			return new ResponseEntity<>(inteRequestOptional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	@DeleteMapping("/{inteRequeId}")
	public ResponseEntity<HttpStatus> deleteInternRequest(@PathVariable("inteRequeId") long inteRequId) {
		try {
			iInternRequestService.deleteInternRequest(inteRequId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping()
	public ResponseEntity<List<InternRequest>> getInternRequests() {
		try {
			List<InternRequest> internRequests = iInternRequestService.internRequests();
			if (internRequests.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(internRequests, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@Override
	@GetMapping("/comp/{compId}")
	public ResponseEntity<List<InternRequest>> getInternRequestsAssociatedCompany(@PathVariable("compId") long compId) {
		try {
			List<InternRequest> internRequests = iInternRequestService.findInternRequestsByCompany(compId);
			System.out.println(internRequests);
			if (internRequests.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(internRequests, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	}

}
