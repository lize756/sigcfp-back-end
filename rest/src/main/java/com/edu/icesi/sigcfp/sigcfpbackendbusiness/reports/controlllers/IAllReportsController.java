package com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.controlllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface IAllReportsController {

    ResponseEntity<?> downloadAllCompaniesReport(Map<String, Object> params);
}
