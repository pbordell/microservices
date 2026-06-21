package com.pbs.report.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.pbs.report.service.DefaultReportService;
import com.pbs.report.service.ReportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ReportAction extends ActionSupport implements Preparable {

    private ReportService reportService = new DefaultReportService();
    private InputStream inputStream;

    @SuppressWarnings("unchecked")
    public String exportInExcel() {
        ActionContext context = ActionContext.getContext();
        String token = null;

        if (context != null) {
            Map<String, Object> requestMap = (Map<String, Object>) context.get("request");

            if (requestMap != null) {
                Object authObj = requestMap.get("Authorization");
                if (authObj == null) {
                    authObj = context.get("requestHeaders");
                }

                token = authObj.toString();
            }
        }

        if (token == null) {
            return ERROR;
        }

        HSSFWorkbook workbook = reportService.generateReport(token);

        try {
            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            workbook.write(boas);
            this.inputStream = new ByteArrayInputStream(boas.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public void prepare() throws Exception {
    }
}
