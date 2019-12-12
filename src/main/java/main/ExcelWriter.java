package main;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelWriter {
    private List<IterationDataCounter> iterationDataCounterList;
    private int rowCount=0;
    public ExcelWriter()
    {
        this.iterationDataCounterList = new ArrayList<>();
    }
    public void writeExcel(List<IterationDataCounter> listData, String excelFilePath) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowCount = 0;
        for (IterationDataCounter aIterationDataCounter : listData) {
            Row row1 = sheet.createRow(0);
            Row row2 = sheet.createRow(++rowCount);
            writeHeader(row1);
            writeData(aIterationDataCounter, row2);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }
    private void writeHeader(Row row)
    {
        Cell cell = row.createCell(0);
        cell.setCellValue("Iteration");

        cell = row.createCell(1);
        cell.setCellValue("Individuality");

        cell = row.createCell(2);
        cell.setCellValue("Time");

        cell = row.createCell(3);
        cell.setCellValue("Weather");

        cell = row.createCell(4);
        cell.setCellValue("Relation");

        cell = row.createCell(5);
        cell.setCellValue("Activity");
        cell = row.createCell(6);
        cell.setCellValue("Location");
        cell = row.createCell(7);
        cell.setCellValue("Situation");

//        cell = row.createCell(7);
//        cell.setCellValue("AVG of Relations Data");
//        cell = row.createCell(8);
//        cell.setCellValue("AVG of Activity Data");
//        cell = row.createCell(9);
//        cell.setCellValue("AVG of Location Data");
//        cell = row.createCell(10);
//        cell.setCellValue("AVG of Weather Data");
//        cell = row.createCell(11);
//        cell.setCellValue("AVG of Time");
    }
    private void writeData(IterationDataCounter aIterationDataCounter, Row row) {
        this.rowCount++;
        Cell cell = row.createCell(0);
        cell.setCellValue(rowCount);
        cell = row.createCell(1);
        cell.setCellValue(aIterationDataCounter.getIndividualityEventCounter());

        cell = row.createCell(2);
        cell.setCellValue(aIterationDataCounter.getTimeEventCounter());

        cell = row.createCell(3);
        cell.setCellValue(aIterationDataCounter.getWeatherAlertCounter());

        cell = row.createCell(4);
        cell.setCellValue(aIterationDataCounter.getRelationEventCounter());

        cell = row.createCell(5);
        cell.setCellValue(aIterationDataCounter.getActivityEventCounter());
        cell = row.createCell(6);
        cell.setCellValue(aIterationDataCounter.getLocationEventCounter());
        cell = row.createCell(7);
        cell.setCellValue(aIterationDataCounter.getSituationAlertCounter());
/*
        if (rowCount == 1) {
            cell = row.createCell(7);
            cell.setCellFormula("SUM(B2:B201) / " + 200);
            cell = row.createCell(8);
            cell.setCellFormula("SUM(C2:C201) / " + 200);
            cell = row.createCell(9);
            cell.setCellFormula("SUM(D2:D201) / " + 200);
            cell = row.createCell(10);
            cell.setCellFormula("SUM(E2:E201) / " + 200);
            cell = row.createCell(11);
            cell.setCellFormula("SUM(F2:F201) / " + 200);
        }*/

    }

    public List<IterationDataCounter> getIterationDataCounterList() {
        return iterationDataCounterList;
    }

    public void setIterationDataCounterList(List<IterationDataCounter> iterationDataCounterList) {
        this.iterationDataCounterList = iterationDataCounterList;
    }
    public void addToDataList(IterationDataCounter iterationDataCounter)
    {
        this.iterationDataCounterList.add(iterationDataCounter);
    }
}
