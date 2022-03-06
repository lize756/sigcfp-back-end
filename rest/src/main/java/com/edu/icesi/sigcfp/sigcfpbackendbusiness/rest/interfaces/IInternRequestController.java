package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.InternRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IInternRequestController {
	/**
	 * Allow add new intern request in the system.
	 * 
	 * @param internRequest intern request to add.
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<InternRequest> addInternRequest(InternRequest internRequest);

    /**
	 * Allow update a intern request.
	 * 
	 * @param inteRequId id of the intern request to update.
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<InternRequest> updateInternRequest(long inteRequId,InternRequest internRequest);

    /**
	 * Allows to obtain a intern request you id.
	 * 
	 * @param inteRequId id to search a one intern request
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<InternRequest> getInternRequest(long inteRequId);

    /**
	 * Allows delete a intern request through you id
	 * 
	 * @param inteRequId id of the intern request that you want to delete
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<HttpStatus> deleteInternRequest(long inteRequId);

    /**
	 * Allows to obtain the list of intern requests saved in the database.
	 * 
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<List<InternRequest>> getInternRequests();

}
