package com.pbs.report.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.pbs.report.service.DefaultReportService;
import com.pbs.report.service.ReportService;

public class ReportAction extends ActionSupport implements Preparable {

    private ReportService reportService = new DefaultReportService();

    public void generateReport() {
        reportService.generateReport();
    }

    @Override
    public void prepare() throws Exception {

    }
}

