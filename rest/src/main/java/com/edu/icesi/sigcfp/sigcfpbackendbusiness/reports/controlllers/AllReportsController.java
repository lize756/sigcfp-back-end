package com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.controlllers;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.ReportDTO;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.reports.ReportTypeEnum;
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

    @Autowired IAllReportsService iAllReportsService;

    @Override
    @GetMapping("/download/allCompaniesReport")
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
}
