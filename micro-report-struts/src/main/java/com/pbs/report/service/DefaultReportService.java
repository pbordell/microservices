package com.pbs.report.service;

import com.pbs.report.client.CoreCrudsClient;
import com.pbs.report.dto.CrudDTO;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;

public class DefaultReportService implements ReportService {

    private CoreCrudsClient coreCrudsClient = new CoreCrudsClient();

    public DefaultReportService() {

    }

    @Override
    public HSSFWorkbook generateReport() {
        List<CrudDTO> crudsDTO = coreCrudsClient.getCrusAll();

        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Crud List");

            Row rowHeading = sheet.createRow(0);
            rowHeading.createCell(0).setCellValue("Name");

            for (int i = 0; i < 4; i++) {
                CellStyle stylerowHeading = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                font.setFontName(HSSFFont.FONT_ARIAL);
                font.setFontHeightInPoints((short) 11);
                stylerowHeading.setFont(font);
                rowHeading.getCell(i).setCellStyle(stylerowHeading);
            }

            int r = 1;
            for (CrudDTO t : crudsDTO) {
                Row row = sheet.createRow(r);

                Cell cellMobileNumber = row.createCell(0);
                cellMobileNumber.setCellValue(t.getName());

                r++;
            }

            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }

}
