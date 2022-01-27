package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICompanyController {

    ResponseEntity<String> addCompany(Company company);

    ResponseEntity<String> updateCompany(Company company, long compId);

    ResponseEntity<String> getCompany(long compId);

    ResponseEntity<String> deleteCompany(long compId);

    ResponseEntity<List<Company>> getCompanies();

}
