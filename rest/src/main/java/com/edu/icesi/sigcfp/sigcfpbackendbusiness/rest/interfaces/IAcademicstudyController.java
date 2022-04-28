package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Academicstudy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAcademicstudyController {


    /**
     * Allow add new academy study.
     *
     * @param academicstudy academy study to add.
     * @return a responseEntity that represent the whole HTTP response: status code, headers, and body.
     */
    ResponseEntity<Academicstudy> addAcademicstudy(Academicstudy academicstudy);

    /**
     * Allow update an academy study.
     *
     * @param academicstudy academy study to update.
     * @return a responseEntity that represent the whole HTTP response: status code, headers, and body.
     */
    ResponseEntity<Academicstudy> updateAcademicstudy(long acadStudId, Academicstudy academicstudy);

    /**
     * Allows to obtain an academy study through you id.
     *
     * @param acadStudId academy study id to search
     * @return a responseEntity that represent the whole HTTP response: status code, headers, and body.
     */
    ResponseEntity<Academicstudy> getAcademicstudy(long acadStudId);

    /**
     * Allows delete an academy study through you id
     *
     * @param acadStudId academy study id to search
     * @return a responseEntity that represent the whole HTTP response: status code, headers, and body.
     */
    ResponseEntity<HttpStatus> deleteAcademicstudy(long acadStudId);

    /**
     * Allows to obtain the list of academy studies saved in the database.
     *
     * @return a responseEntity that represent the whole HTTP response: status code, headers, and body.
     */
    ResponseEntity<List<Academicstudy>> getAcademicstudies();

}
