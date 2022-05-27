package com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.controlllers;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.model.ReportDTO;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.model.ReportTypeEnum;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.services.IAllReportsService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class AllReportsController implements IAllReportsController {

    @Autowired
    IAllReportsService iAllReportsService;

    @Override
    @GetMapping("/allCompaniesReport")
    public ResponseEntity<?> downloadAllCompaniesReport(@RequestParam Map<String, Object> params) {
        ReportDTO dto = null;
        MediaType mediaType = null;
        try {
            dto = iAllReportsService.generateAllCompaniesReport(params);
            InputStreamResource streamResource = new InputStreamResource(dto.getStream());
            if (params.get("type").toString().equalsIgnoreCase(ReportTypeEnum.EXCEL.name())) {
                mediaType = MediaType.APPLICATION_OCTET_STREAM;
            } else {
                mediaType = MediaType.APPLICATION_PDF;
            }
            return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                    .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    @GetMapping("/companyContactsReport")
    public ResponseEntity<?> downloadCompanyContactsReport(@RequestParam Map<String, Object> params) {

        ReportDTO dto = null;
        MediaType mediaType = null;
        try {
            dto = iAllReportsService.generateCompanyContactsReport(params);
            InputStreamResource streamResource = new InputStreamResource(dto.getStream());
            if (params.get("type").toString().equalsIgnoreCase(ReportTypeEnum.EXCEL.name())) {
                mediaType = MediaType.APPLICATION_OCTET_STREAM;
            } else {
                mediaType = MediaType.APPLICATION_PDF;
            }
            return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                    .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @GetMapping("/companyInternRequestsByCompanyIdReport")
    public ResponseEntity<?> downloadCompanyInternRequestsReport(@RequestParam Map<String, Object> params) {

        ReportDTO dto = null;
        MediaType mediaType = null;
        try {
            params.replace("compId", Long.valueOf(params.get("compId").toString()));
            dto = iAllReportsService.generateCompanyInternRequestsReport(params);
            InputStreamResource streamResource = new InputStreamResource(dto.getStream());
            if (params.get("type").toString().equalsIgnoreCase(ReportTypeEnum.EXCEL.name())) {
                mediaType = MediaType.APPLICATION_OCTET_STREAM;
            } else {
                mediaType = MediaType.APPLICATION_PDF;
            }
            return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                    .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    @GetMapping("/companyInternRequestsPersonGroupingByCareerReport")
    public ResponseEntity<?> downloadInternRequestsPersonGroupingByCareerReport(@RequestParam Map<String, Object> params) {
        ReportDTO dto = null;
        MediaType mediaType = null;
        try {
            params.replace("persId", Long.valueOf(params.get("persId").toString()));
            dto = iAllReportsService.generateInternRequestsPersonGroupingByCareerReport(params);
            InputStreamResource streamResource = new InputStreamResource(dto.getStream());
            if (params.get("type").toString().equalsIgnoreCase(ReportTypeEnum.EXCEL.name())) {
                mediaType = MediaType.APPLICATION_OCTET_STREAM;
            } else {
                mediaType = MediaType.APPLICATION_PDF;
            }
            return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                    .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
