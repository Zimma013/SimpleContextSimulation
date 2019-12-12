package main;

import java.io.IOException;

public class Application {
	public static IterationDataCounter iterationDataCounter = new IterationDataCounter();
	public static ExcelWriter excelWriter = new ExcelWriter();
	public static void main(String[] args) throws IOException {





		String excelFilePath = "NiceContextData.xls";
		excelWriter.writeExcel(excelWriter.getIterationDataCounterList(), excelFilePath);

	}
}
