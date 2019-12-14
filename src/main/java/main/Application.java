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

	private static final Double timeRangeOneStart = 5D;
	private static final Double timeRangeOneEnd = 11D;
	private static final Double timeRangeTwoStart = 11D;
	private static final Double timeRangeTwoEnd = 17D;
	private static final Double timeRangeThreeStart = 17D;
	private static final Double timeRangeThreeEnd = 5D;


	public static void main(String[] args) throws IOException {
		Simulator simulator = new Simulator.Builder()
				.normalDistribution(new NormalDistribution(mean, standardDeviation))
				.maxPopulation(maxPopulation)
				.singleIterationTimeOffset(iterationTimeOffset)
				.peakTime(mean)
				.startTime(2.5) // to make 14:00 the middle of normal distribution
				.endTime(24D + 2.5 - iterationTimeOffset)
				.iterationCount(iterationCount)
				.timeRangeOneStart(timeRangeOneStart)
				.timeRangeOneEnd(timeRangeOneEnd)
				.timeRangeOneModifier(1D)
				.timeRangeTwoStart(timeRangeTwoStart)
				.timeRangeTwoEnd(timeRangeTwoEnd)
				.timeRangeTwoModifier(1.3D)
				.timeRangeThreeStart(timeRangeThreeStart)
				.timeRangeThreeEnd(timeRangeThreeEnd)
				.timeRangeThreeModifier(2D)
				.iterationCount((int) (1/iterationTimeOffset * 24))
				.animalPercentage(0.05)
				.leaderPercentage(0.05)
				.noMovementPercentage(0.05)
				.offTrailPercentage(0.10)
				.weatherPercentage(0.25)
				.build();

		simulator.simulate();


		String excelFilePath = "NiceContextData.xls";

		excelWriter.setIterationCount(iterationCount);
		excelWriter.setFirstRangeIterationCount(new BigDecimal(timeRangeOneEnd - timeRangeOneStart).abs().divide(new BigDecimal(iterationTimeOffset), 0, RoundingMode.HALF_UP).intValue());
		excelWriter.setSecondRangeIterationCount(new BigDecimal(timeRangeTwoEnd - timeRangeTwoStart).abs().divide(new BigDecimal(iterationTimeOffset), 0, RoundingMode.HALF_UP).intValue());
		excelWriter.setThirdRangeIterationCount(new BigDecimal(timeRangeThreeEnd - timeRangeThreeStart).abs().divide(new BigDecimal(iterationTimeOffset), 0, RoundingMode.HALF_UP).intValue());


		excelWriter.writeExcel(excelWriter.getIterationDataCounterList(), excelFilePath);

	}
}
