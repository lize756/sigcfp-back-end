package com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.services;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.JasperReportManager;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.model.ReportDTO;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.model.ReportTypeEnum;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Service
public class AllReportsService implements IAllReportsService {

    @Autowired
    private JasperReportManager reportManager;
    @Autowired
    private DataSource dataSource;


    @Override
    public ReportDTO generateCompanyContactsReport(Map<String, Object> params) throws SQLException, JRException, IOException {

        String fileName = "Company_Contacts";
        ReportDTO dto = new ReportDTO();
        String extension = params.get("type").toString().equalsIgnoreCase(ReportTypeEnum.EXCEL.name()) ? ".xlsx"
                : ".pdf";
        dto.setFileName(fileName + extension);

        ByteArrayOutputStream stream = reportManager.export(fileName, params.get("type").toString(), params,
                dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));
        dto.setLength(bs.length);

        return dto;
    }

    @Override
    public ReportDTO generateAllCompaniesReport(Map<String, Object> params) throws SQLException, JRException, IOException {

        String fileName = "All_Info_Company";
        ReportDTO dto = new ReportDTO();
        String extension = params.get("type").toString().equalsIgnoreCase(ReportTypeEnum.EXCEL.name()) ? ".xlsx"
                : ".pdf";
        dto.setFileName(fileName + extension);

        ByteArrayOutputStream stream = reportManager.export(fileName, params.get("type").toString(), params,
                dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));
        dto.setLength(bs.length);

        return dto;
    }

    @Override
    public ReportDTO generateCompanyInternRequestsReport(Map<String, Object> params) throws SQLException, JRException, IOException {

        String fileName = "Company_InternRequests";
        ReportDTO dto = new ReportDTO();
        String extension = params.get("type").toString().equalsIgnoreCase(ReportTypeEnum.EXCEL.name()) ? ".xlsx"
                : ".pdf";
        dto.setFileName(fileName + extension);

        ByteArrayOutputStream stream = reportManager.export(fileName, params.get("type").toString(), params,
                dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));
        dto.setLength(bs.length);

        return dto;
    }

    @Override
    public ReportDTO generateInternRequestsPersonGroupingByCareerReport(Map<String, Object> params) throws SQLException, JRException, IOException {

        String fileName = "InternRequestsByCareerAndPerson";
        ReportDTO dto = new ReportDTO();
        String extension = params.get("type").toString().equalsIgnoreCase(ReportTypeEnum.EXCEL.name()) ? ".xlsx"
                : ".pdf";
        dto.setFileName(fileName + extension);

        ByteArrayOutputStream stream = reportManager.export(fileName, params.get("type").toString(), params,
                dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));
        dto.setLength(bs.length);

        return dto;

    }
}
