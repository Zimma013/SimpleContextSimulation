package main;

import org.apache.commons.math3.distribution.NormalDistribution;

public class Simulator {

	public IterationDataCounter iterationDataCounter;

	private NormalDistribution normalDistribution;
	private int maxPopulation;
	private Double startTime;
	private Double endTime;
	private Double peakTime;
	private Double singleIterationTimeOffset;
	private int iterationCount;

	private Double timeRangeOneStart;
	private Double timeRangeOneEnd;
	private Double timeRangeTwoStart;
	private Double timeRangeTwoEnd;
	private Double timeRangeThreeStart;
	private Double timeRangeThreeEnd;
	private Double timeRangeOneModifier;
	private Double timeRangeTwoModifier;
	private Double timeRangeThreeModifier;

	public void simulate() {
		for (int i = 0; i < iterationCount; i++) {
			getSampleIteration(i);
		}
	}

	private void getSampleIteration(int iterationNumber) {
		iterationDataCounter = new IterationDataCounter();
		int currentPopulationCount = 0;
		double iterationTime = iterationNumber * singleIterationTimeOffset + startTime; // should range from startTime to 24 + startTime (29)
		if (iterationNumber <= (iterationCount/2)) { // means that iterationTime is less or equal (?) to mean of normal distribution
			currentPopulationCount = (int) (normalDistribution.cumulativeProbability(iterationTime) * maxPopulation);
		} else {
			currentPopulationCount = (int) (normalDistribution.inverseCumulativeProbability(iterationTime/(24+startTime)) * maxPopulation);
		}

		// one iteration is checking one person from population
		for (int pop = 0; pop < currentPopulationCount; pop++) {
			// weather alerts
			//TODO:: generate weather alerts

			// situation alerts
			//TODO:: generate situation alerts
		}

		Application.excelWriter.addToDataList(iterationDataCounter);
	}


	private Simulator(Builder builder) {
		normalDistribution = builder.normalDistribution;
		maxPopulation = builder.maxPopulation;
		startTime = builder.startTime;
		endTime = builder.endTime;
		peakTime = builder.peakTime;
		singleIterationTimeOffset = builder.singleIterationTimeOffset;
		iterationCount = builder.iterationCount;
		timeRangeOneStart = builder.timeRangeOneStart;
		timeRangeOneEnd = builder.timeRangeOneEnd;
		timeRangeTwoStart = builder.timeRangeTwoStart;
		timeRangeTwoEnd = builder.timeRangeTwoEnd;
		timeRangeThreeStart = builder.timeRangeThreeStart;
		timeRangeThreeEnd = builder.timeRangeThreeEnd;
		timeRangeOneModifier = builder.timeRangeOneModifier;
		timeRangeTwoModifier = builder.timeRangeTwoModifier;
		timeRangeThreeModifier = builder.timeRangeThreeModifier;
	}

	public static final class Builder {
		private NormalDistribution normalDistribution;
		private int maxPopulation;
		private Double startTime;
		private Double endTime;
		private Double peakTime;
		private Double singleIterationTimeOffset;
		private int iterationCount;
		private Double timeRangeOneStart;
		private Double timeRangeOneEnd;
		private Double timeRangeTwoStart;
		private Double timeRangeTwoEnd;
		private Double timeRangeThreeStart;
		private Double timeRangeThreeEnd;
		private Double timeRangeOneModifier;
		private Double timeRangeTwoModifier;
		private Double timeRangeThreeModifier;

		public Builder() {
		}

		public Builder normalDistribution(NormalDistribution val) {
			normalDistribution = val;
			return this;
		}

		public Builder maxPopulation(int val) {
			maxPopulation = val;
			return this;
		}

		public Builder startTime(Double val) {
			startTime = val;
			return this;
		}

		public Builder endTime(Double val) {
			endTime = val;
			return this;
		}

		public Builder peakTime(Double val) {
			peakTime = val;
			return this;
		}

		public Builder singleIterationTimeOffset(Double val) {
			singleIterationTimeOffset = val;
			return this;
		}

		public Builder iterationCount(int val) {
			iterationCount = val;
			return this;
		}

		public Builder timeRangeOneStart(Double val) {
			timeRangeOneStart = val;
			return this;
		}

		public Builder timeRangeOneEnd(Double val) {
			timeRangeOneEnd = val;
			return this;
		}

		public Builder timeRangeTwoStart(Double val) {
			timeRangeTwoStart = val;
			return this;
		}

		public Builder timeRangeTwoEnd(Double val) {
			timeRangeTwoEnd = val;
			return this;
		}

		public Builder timeRangeThreeStart(Double val) {
			timeRangeThreeStart = val;
			return this;
		}

		public Builder timeRangeThreeEnd(Double val) {
			timeRangeThreeEnd = val;
			return this;
		}

		public Builder timeRangeOneModifier(Double val) {
			timeRangeOneModifier = val;
			return this;
		}

		public Builder timeRangeTwoModifier(Double val) {
			timeRangeTwoModifier = val;
			return this;
		}

		public Builder timeRangeThreeModifier(Double val) {
			timeRangeThreeModifier = val;
			return this;
		}

		public Simulator build() {
			return new Simulator(this);
		}
	}
}
