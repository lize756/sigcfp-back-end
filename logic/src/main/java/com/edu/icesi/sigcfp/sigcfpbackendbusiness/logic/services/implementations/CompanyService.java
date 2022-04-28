package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICompanyService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ICompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyService implements ICompanyService {

    ICompanyRepo iCompanyRepo;

    @Autowired
    public CompanyService(ICompanyRepo iCompanyRepo) {
        this.iCompanyRepo = iCompanyRepo;
    }

    @Override
    @Transactional
    public Company addCompany(Company company) {
        return iCompanyRepo.save(company);
    }

    @Override
    @Transactional
    public Company updateCompany(Company company) {
        return iCompanyRepo.save(company);
    }

    @Override
    @Transactional
    public Company searchCompany(long compId) {
        if (iCompanyRepo.existsById(compId)) {
            return iCompanyRepo.getById(compId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Company deleteCompany(long compId) {
        Company companyToDelete = null;
        if (iCompanyRepo.existsById(compId)) {
            companyToDelete = iCompanyRepo.findById(compId).get();
            iCompanyRepo.delete(iCompanyRepo.getById(compId));
        } else {
            return null;
        }
        return companyToDelete;
    }

    @Override
    @Transactional
    public List<Company> companies() {
        return iCompanyRepo.findAll();
    }

    @Override
    public List<Company> findCompaniesByCompIcesiStud(boolean hasIcesiStudent) {
        return iCompanyRepo.findCompaniesByCompIcesiStud(hasIcesiStudent);
    }
}
