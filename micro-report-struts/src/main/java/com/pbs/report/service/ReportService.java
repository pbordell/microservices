package com.pbs.report.service;

import com.pbs.report.dto.CrudDTO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface ReportService {

    HSSFWorkbook generateReport();
}