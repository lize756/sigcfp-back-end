package com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.services;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.ReportDTO;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface IAllReportsService {

    ReportDTO generateCompanyContactsReport(Map<String, Object> params) throws SQLException, JRException, IOException;

    ReportDTO generateAllCompaniesReport(Map<String, Object> params) throws SQLException, JRException, IOException;

}
