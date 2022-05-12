package com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.controlllers;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.model.ReportDTO;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface IAllReportsController {

    ResponseEntity<?> downloadAllCompaniesReport(Map<String, Object> params);

    ResponseEntity<?> downloadCompanyContactsReport(Map<String, Object> params);

    ResponseEntity<?> downloadCompanyInternRequestsReport(Map<String, Object> params);

    ResponseEntity<?> downloadInternRequestsPersonGroupingByCareerReport(Map<String, Object> params);



}
