package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICareerController {
    /**
     * Allow add new career in the system.
     *
     * @param career career to add.
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<Career> addCareer(Career career);

    /**
     * Allow update a career.
     *
     * @param careId id of career to update.
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<Career> updateCareer(long careId, Career career);

    /**
     * Allows to obtain a career through you id.
     *
     * @param careId id to search career
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<Career> getCareer(long careId);

    /**
     * Allows delete a career through you id
     *
     * @param careId id of the career that you want to delete
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<HttpStatus> deleteCareer(long careId);

    /**
     * Allows to obtain the list of careers saved in the database.
     *
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<List<Career>> getCareers();

}
