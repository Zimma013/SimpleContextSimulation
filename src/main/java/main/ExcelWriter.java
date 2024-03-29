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

    private int iterationCount;
    private int firstRangeIterationCount;
    private int secondRangeIterationCount;
    private int thirdRangeIterationCount;

    private String[] dataColumnSymbols = {"C","D","E","F","G","H","I"};

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
        cell.setCellValue("Iteration Time");

        cell = row.createCell(1);
        cell.setCellValue("Population");

        cell = row.createCell(2);
        cell.setCellValue("Individuality");

        cell = row.createCell(3);
        cell.setCellValue("Weather");

        cell = row.createCell(4);
        cell.setCellValue("Time");

        cell = row.createCell(5);
        cell.setCellValue("Location");

        cell = row.createCell(6);
        cell.setCellValue("Activity");

        cell = row.createCell(7);
        cell.setCellValue("Situation");

        cell = row.createCell(8);
        cell.setCellValue("Relation");

        cell = row.createCell(10);
        cell.setCellValue("AVG of Individuality");
        cell = row.createCell(11);
        cell.setCellValue("AVG of Weather (P)");
        cell = row.createCell(12);
        cell.setCellValue("AVG of Time");
        cell = row.createCell(13);
        cell.setCellValue("AVG of Location");
        cell = row.createCell(14);
        cell.setCellValue("AVG of Activity");
        cell = row.createCell(15);
        cell.setCellValue("AVG of Situation (S)");
        cell = row.createCell(16);
        cell.setCellValue("AVG of Relation");
    }
    private void writeData(IterationDataCounter aIterationDataCounter, Row row) {
        this.rowCount++;
        Cell cell = row.createCell(0);
        cell.setCellValue(aIterationDataCounter.getIterationTime() <= 24D ? aIterationDataCounter.getIterationTime() : aIterationDataCounter.getIterationTime() - 24D);

        cell = row.createCell(1);
        cell.setCellValue(aIterationDataCounter.getPopulationCount());

        cell = row.createCell(2);
        cell.setCellValue(aIterationDataCounter.getIndividualityEventCounter());

        cell = row.createCell(3);
        cell.setCellValue(aIterationDataCounter.getWeatherAlertCounter());

        cell = row.createCell(4);
        cell.setCellValue(aIterationDataCounter.getTimeEventCounter());

        cell = row.createCell(5);
        cell.setCellValue(aIterationDataCounter.getLocationEventCounter());

        cell = row.createCell(6);
        cell.setCellValue(aIterationDataCounter.getActivityEventCounter());

        cell = row.createCell(7);
        cell.setCellValue(aIterationDataCounter.getSituationAlertCounter());

        cell = row.createCell(8);
        cell.setCellValue(aIterationDataCounter.getRelationEventCounter());

        int dataFromSimulationStartRowNumber = 7;
        int dataRowNumber = 2;
        int columnInRowStartNumber = 10;

        createAvgColumns(row, columnInRowStartNumber, dataRowNumber, dataFromSimulationStartRowNumber);

    }

    private void createAvgColumns(Row row, int columnInRowStartNumber, int dataRowNumber, int dataFromSimulationStartRowNumber) {
        Cell cell;
        switch (this.rowCount) {
            case 1:
                for (int i = 0; i < dataColumnSymbols.length; i++) {
                    cell = row.createCell(i + columnInRowStartNumber);
                    cell.setCellFormula("SUM(" +
                            dataColumnSymbols[i] + "" + (dataFromSimulationStartRowNumber + 1) + ":" + dataColumnSymbols[i] + "" +
                            (dataFromSimulationStartRowNumber + firstRangeIterationCount) + ") / " + firstRangeIterationCount);
                }
                break;
            case 2:
                for (int i = 0; i < dataColumnSymbols.length; i++) {
                    cell = row.createCell(i + columnInRowStartNumber);
                    cell.setCellFormula("SUM(" +
                            dataColumnSymbols[i] + "" + (dataFromSimulationStartRowNumber + firstRangeIterationCount + 1) + ":" + dataColumnSymbols[i] + "" +
                            (dataFromSimulationStartRowNumber + firstRangeIterationCount + secondRangeIterationCount) + ") / " + secondRangeIterationCount);
                }
                break;
            case 3:
                for (int i = 0; i < dataColumnSymbols.length; i++) {
                    cell = row.createCell(i + columnInRowStartNumber);
                    cell.setCellFormula("(SUM(" +
                            dataColumnSymbols[i] + "" + (dataFromSimulationStartRowNumber + firstRangeIterationCount + secondRangeIterationCount + 1) + ":" + dataColumnSymbols[i] + "" +
                            (dataFromSimulationStartRowNumber + firstRangeIterationCount + secondRangeIterationCount + thirdRangeIterationCount - (dataFromSimulationStartRowNumber - dataRowNumber - 1)) + ") + SUM(" +
                            dataColumnSymbols[i] + "" + dataRowNumber + ":" + dataColumnSymbols[i] + "" + (dataFromSimulationStartRowNumber) +
                            ")) / " + thirdRangeIterationCount);
                }
                break;
            case 4:
                for (int i = 0; i < dataColumnSymbols.length; i++) {
                    cell = row.createCell(i + columnInRowStartNumber);
                    cell.setCellFormula("SUM(" +
                            dataColumnSymbols[i] + "" + (dataRowNumber) + ":" + dataColumnSymbols[i] + "" +
                            (dataRowNumber + firstRangeIterationCount + secondRangeIterationCount + thirdRangeIterationCount - 1) + ") / " + iterationCount);
                }
                break;
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

    public void setIterationCount(int iterationCount) {
        this.iterationCount = iterationCount;
    }

    public void setFirstRangeIterationCount(int firstRangeIterationCount) {
        this.firstRangeIterationCount = firstRangeIterationCount;
    }

    public void setSecondRangeIterationCount(int secondRangeIterationCount) {
        this.secondRangeIterationCount = secondRangeIterationCount;
    }

    public void setThirdRangeIterationCount(int thirdRangeIterationCount) {
        this.thirdRangeIterationCount = thirdRangeIterationCount;
    }
}
