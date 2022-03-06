package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Faculty;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFacultyController {
	/**
	 * Allow add new faculty in the system.
	 * 
	 * @param faculty faculty to add.
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
	ResponseEntity<Faculty> addFaculty(Faculty faculty);

	/**
	 * Allow update a faculty.
	 * 
	 * @param facuId id of the faculty to update.
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
	ResponseEntity<Faculty> updateFaculty(long facuId,Faculty faculty);

	/**
	 * Allows to obtain a faculty you id.
	 * 
	 * @param facuId id to search faculty
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
	ResponseEntity<Faculty> getFaculty(long facuId);

	/**
	 * Allows delete a faculty through you id
	 * 
	 * @param facuId id of the faculty that you want to delete
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
	ResponseEntity<HttpStatus> deleteContact(long facuId);

	/**
	 * Allows to obtain the list of faculty saved in the database.
	 * 
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
	ResponseEntity<List<Faculty>> getFaculties();
}
