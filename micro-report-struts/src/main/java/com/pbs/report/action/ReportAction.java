package com.pbs.report.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.pbs.report.service.DefaultReportService;
import com.pbs.report.service.ReportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReportAction extends ActionSupport implements Preparable {

    private ReportService reportService = new DefaultReportService();
    private InputStream inputStream;

    public String exportInExcel() {
        // 1. Capturamos la petición HTTP directa
        HttpServletRequest request = ServletActionContext.getRequest();
        String token = null;

        if (request != null) {
            // 2. Extraemos el string de autorización tal y como lo entrega la red
            token = request.getHeader("Authorization");
        }

        // 3. Fallback de control para asegurar que el servicio POI siempre reciba una cadena
        if (token == null || token.isEmpty()) {
            token = "token-laboratorio-defecto";
        }

        // 4. Invocación directa a tu lógica de negocio
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
