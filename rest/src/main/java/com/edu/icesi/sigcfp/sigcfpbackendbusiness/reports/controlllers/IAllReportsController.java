package com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.controlllers;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IAllReportsController {

    ResponseEntity<?> downloadAllCompaniesReport(Map<String, Object> params);

    ResponseEntity<?> downloadCompanyContactsReport(Map<String, Object> params);

}
