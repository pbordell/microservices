package com.pbs.report.service;

import com.pbs.report.client.CoreCrudsClient;

public class DefaultReportService implements ReportService {

    private CoreCrudsClient coreCrudsClient = new CoreCrudsClient();

    public DefaultReportService() {
    }

    @Override
    public void generateReport() {
        coreCrudsClient.getCrusAll();
    }

}
