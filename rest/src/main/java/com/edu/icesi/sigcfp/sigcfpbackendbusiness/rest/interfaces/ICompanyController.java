package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICompanyController {

    /**
     * Allow add new company in the system.
     *
     * @param company company to add.
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<Company> addCompany(Company company);

    /**
     * Allow update a company.
     *
     * @param compId id of company to update.
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<Company> updateCompany(long compId, Company company);

    /**
     * Allows to obtain a company through you id.
     *
     * @param id to search company
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<Company> getCompany(long compId);

    /**
     * Allows delete a company through you id
     *
     * @param compId id of the company that you want to delete
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<HttpStatus> deleteCompany(long compId);

    /**
     * Allows to obtain the list of companies saved in the database.
     *
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<List<Company>> getCompanies();

}
