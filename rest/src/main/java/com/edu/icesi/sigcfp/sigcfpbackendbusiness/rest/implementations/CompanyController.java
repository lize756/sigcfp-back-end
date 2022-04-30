package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICompanyService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.ICompanyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@PreAuthorize("")
@RestController()
@RequestMapping("/api/companies")
@CrossOrigin(origins = "*")
public class CompanyController implements ICompanyController {

    private ICompanyService iCompanyService;

    @Autowired
    public CompanyController(ICompanyService iCompanyService) {
        this.iCompanyService = iCompanyService;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        try {
            Company _company = iCompanyService.addCompany(company);
            return new ResponseEntity<Company>(_company, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/update/{compId}")
    public ResponseEntity<Company> updateCompany(@PathVariable("compId") long compId, @RequestBody Company company) {
        Optional<Company> companyOpt = Optional.of(iCompanyService.searchCompany(compId));
        if (companyOpt.isPresent()) {
            return new ResponseEntity<>(iCompanyService.updateCompany(company), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping("/{compId}")
    public ResponseEntity<Company> getCompany(@PathVariable("compId") long compId) {
        Optional<Company> companyOpt = Optional.of(iCompanyService.searchCompany(compId));
        if (companyOpt.isPresent()) {
            return new ResponseEntity<>(companyOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/{compId}")
    public ResponseEntity<HttpStatus> deleteCompany(@PathVariable("compId") long compId) {
        try {
            iCompanyService.deleteCompany(compId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<Company>> getCompanies() {
        try {
            List<Company> companies = iCompanyService.companies();
            if (companies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(companies, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
