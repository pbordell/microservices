package com.pbs.report.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.pbs.report.service.DefaultReportService;
import com.pbs.report.service.ReportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReportAction extends ActionSupport implements Preparable {

    private ReportService reportService = new DefaultReportService();

    public String generateReport(String token) {
        HSSFWorkbook workbook = reportService.generateReport(token);

        try {
            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            workbook.write(boas);
            InputStream excelStream = new ByteArrayInputStream(boas.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @Override
    public void prepare() throws Exception {

    }
}

