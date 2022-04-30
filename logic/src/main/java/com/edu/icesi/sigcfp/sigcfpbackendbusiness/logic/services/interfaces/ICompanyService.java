package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;

import java.util.List;


public interface ICompanyService {

    Company addCompany(Company company);

    Company updateCompany(Company company);

    Company searchCompany(long compId);

    Company deleteCompany(long compId);

    List<Company> companies();

    List<Company> findCompaniesByCompIcesiStud(boolean hasIcesiStudent);

}
