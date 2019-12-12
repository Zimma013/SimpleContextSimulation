package main;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Application {
	public static IterationDataCounter iterationDataCounter = new IterationDataCounter();
	public static ExcelWriter excelWriter = new ExcelWriter();

	// Simulation params
	private static final Double mean = 14.0; // peak time of population in simulation
	private static final Double standardDeviation = 5.0; // https://homepage.divms.uiowa.edu/~mbognar/applets/normal.html
	private static final Integer maxPopulation = 500;
	private static final Double iterationTimeOffset = 0.5; // half hour
	private static final Integer iterationCount = new BigDecimal(24).divide(new BigDecimal(iterationTimeOffset), 0, RoundingMode.HALF_UP).intValue();


	public static void main(String[] args) throws IOException {
		Simulator simulator = new Simulator.Builder()
				.normalDistribution(new NormalDistribution(mean, standardDeviation))
				.maxPopulation(maxPopulation)
				.singleIterationTimeOffset(iterationTimeOffset)
				.startTime(5D)
				.peakTime(mean)
//				.endTime(24D + 5D - iterationTimeOffset)
				.iterationCount(iterationCount)
				.build();


		String excelFilePath = "NiceContextData.xls";
		excelWriter.writeExcel(excelWriter.getIterationDataCounterList(), excelFilePath);

	}
}
