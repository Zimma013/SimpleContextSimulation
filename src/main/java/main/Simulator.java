package main;

import org.apache.commons.math3.distribution.NormalDistribution;

public class Simulator {
	private NormalDistribution normalDistribution;
	private int maxPopulation;
	private Double startTime;
	private Double endTime;
	private Double peakTime;
	private Double singleIterationTimeOffset;
	private int iterationCount;

	private void getSampleIteration(int iterationNumber) {
		int currentPopulationCount = 0;
		if (iterationNumber <= (iterationCount/2)) {
			currentPopulationCount = (int) (normalDistribution.cumulativeProbability(iterationNumber * singleIterationTimeOffset /* iteration time */) * maxPopulation);
		} else {
			currentPopulationCount = (int) (normalDistribution.inverseCumulativeProbability(iterationNumber * singleIterationTimeOffset /* iteration time */) * maxPopulation);
		}

		for (int pop = 0; pop < currentPopulationCount; pop++) {
			// weather alerts

			// situation alerts
		}
	}


	private Simulator(Builder builder) {
		normalDistribution = builder.normalDistribution;
		maxPopulation = builder.maxPopulation;
		startTime = builder.startTime;
		endTime = builder.endTime;
		peakTime = builder.peakTime;
		singleIterationTimeOffset = builder.singleIterationTimeOffset;
		iterationCount = builder.iterationCount;
	}

	public static final class Builder {
		private NormalDistribution normalDistribution;
		private int maxPopulation;
		private Double startTime;
		private Double endTime;
		private Double peakTime;
		private Double singleIterationTimeOffset;
		private int iterationCount;

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

		public Simulator build() {
			return new Simulator(this);
		}
	}
}
