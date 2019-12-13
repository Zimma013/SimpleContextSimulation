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

        cell = row.createCell(9);
        cell.setCellValue("AVG of Individuality");
        cell = row.createCell(10);
        cell.setCellValue("AVG of Time");
        cell = row.createCell(11);
        cell.setCellValue("AVG of Weather");
        cell = row.createCell(12);
        cell.setCellValue("AVG of Relation");
        cell = row.createCell(13);
        cell.setCellValue("AVG of Activity");
        cell = row.createCell(14);
        cell.setCellValue("AVG of Location");
        cell = row.createCell(15);
        cell.setCellValue("AVG of Situation");
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

        if (rowCount == 1) {
            cell = row.createCell(9);
            cell.setCellFormula("SUM(B2:B19) / " + 18);
            cell = row.createCell(10);
            cell.setCellFormula("SUM(C2:C19) / " + 18);
            cell = row.createCell(11);
            cell.setCellFormula("SUM(D2:D19) / " + 18);
            cell = row.createCell(12);
            cell.setCellFormula("SUM(E2:E19) / " + 18);
            cell = row.createCell(13);
            cell.setCellFormula("SUM(F2:F19) / " + 18);
            cell = row.createCell(14);
            cell.setCellFormula("SUM(G2:G19) / " + 18);
            cell = row.createCell(15);
            cell.setCellFormula("SUM(H2:H19) / " + 18);
        }
        if (rowCount == 2)
        {
            cell = row.createCell(9);
            cell.setCellFormula("SUM(B20:B27) / " + 8);
            cell = row.createCell(10);
            cell.setCellFormula("SUM(C20:C27) / " + 8);
            cell = row.createCell(11);
            cell.setCellFormula("SUM(D20:D27) / " + 8);
            cell = row.createCell(12);
            cell.setCellFormula("SUM(E20:E27) / " + 8);
            cell = row.createCell(13);
            cell.setCellFormula("SUM(F20:F27) / " + 8);
            cell = row.createCell(14);
            cell.setCellFormula("SUM(G20:G27) / " + 8);
            cell = row.createCell(15);
            cell.setCellFormula("SUM(H20:H27) / " + 8);
        }
        if (rowCount == 3)
        {
            cell = row.createCell(9);
            cell.setCellFormula("SUM(B28:B49) / " + 22);
            cell = row.createCell(10);
            cell.setCellFormula("SUM(C28:C49) / " + 22);
            cell = row.createCell(11);
            cell.setCellFormula("SUM(D28:D49) / " + 22);
            cell = row.createCell(12);
            cell.setCellFormula("SUM(E28:E49) / " + 22);
            cell = row.createCell(13);
            cell.setCellFormula("SUM(F28:F49) / " + 22);
            cell = row.createCell(14);
            cell.setCellFormula("SUM(G28:G49) / " + 22);
            cell = row.createCell(15);
            cell.setCellFormula("SUM(H28:H49) / " + 22);
        }
        if (rowCount == 4)
        {
            cell = row.createCell(9);
            cell.setCellFormula("SUM(B2:B49) / " + 48);
            cell = row.createCell(10);
            cell.setCellFormula("SUM(C2:C49) / " + 48);
            cell = row.createCell(11);
            cell.setCellFormula("SUM(D2:D49) / " + 48);
            cell = row.createCell(12);
            cell.setCellFormula("SUM(E2:E49) / " + 48);
            cell = row.createCell(13);
            cell.setCellFormula("SUM(F2:F49) / " + 48);
            cell = row.createCell(14);
            cell.setCellFormula("SUM(G2:G49) / " + 48);
            cell = row.createCell(15);
            cell.setCellFormula("SUM(H2:H49) / " + 48);
        }

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
