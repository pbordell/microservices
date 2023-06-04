package com.pbs.report.service;

import com.pbs.report.dto.CrudDTO;

import java.util.List;

public interface ReportService {

    List<CrudDTO> generateReport();
}