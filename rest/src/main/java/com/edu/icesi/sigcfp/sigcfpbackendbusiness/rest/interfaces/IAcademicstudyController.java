package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Academicstudy;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAcademicstudyController {
	
	
	/**
	 * Allow add new academy study.
	 * @param academicstudy academy study to add.
	 * @return a responseEntity that represent the whole HTTP response: status code, headers, and body. 
	 */
    ResponseEntity<Academicstudy> addAcademicstudy(Academicstudy academicstudy);
	/**
	 * Allow update an academy study.
	 * @param academicstudy academy study to add.
	 * @return a responseEntity that represent the whole HTTP response: status code, headers, and body. 
	 */
    ResponseEntity<Academicstudy> updateAcademicstudy(long acadStudId, Academicstudy academicstudy);
    
    ResponseEntity<Academicstudy> getAcademicstudy(long acadStudId);

    ResponseEntity<Academicstudy> deleteAcademicstudy(long acadStudId);

    ResponseEntity<List<Academicstudy>> getAcademicstudies();

}
