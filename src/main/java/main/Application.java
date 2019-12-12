package main;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Application {

	public static ExcelWriter excelWriter = new ExcelWriter();

	// Simulation params
	private static final Double mean = 14D; // peak time of population in simulation
	private static final Double standardDeviation = 5D; // https://homepage.divms.uiowa.edu/~mbognar/applets/normal.html
	private static final Integer maxPopulation = 500;
	private static final Double iterationTimeOffset = 0.5D; // half hour
	private static final Integer iterationCount = new BigDecimal(24).divide(new BigDecimal(iterationTimeOffset), 0, RoundingMode.HALF_UP).intValue();


	public static void main(String[] args) throws IOException {
		Simulator simulator = new Simulator.Builder()
				.normalDistribution(new NormalDistribution(mean, standardDeviation))
				.maxPopulation(maxPopulation)
				.singleIterationTimeOffset(iterationTimeOffset)
				.startTime(5D)
				.peakTime(mean)
				.endTime(24D + 5D - iterationTimeOffset)
				.iterationCount(iterationCount)
				.timeRangeOneStart(5D)
				.timeRangeOneEnd(14D)
				.timeRangeOneModifier(1D)
				.timeRangeTwoStart(14D)
				.timeRangeTwoEnd(18D)
				.timeRangeTwoModifier(1.3D)
				.timeRangeThreeStart(18D)
				.timeRangeThreeEnd(5D)
				.timeRangeThreeModifier(2D)
				.build();


		String excelFilePath = "NiceContextData.xls";
		excelWriter.writeExcel(excelWriter.getIterationDataCounterList(), excelFilePath);

	}
}
