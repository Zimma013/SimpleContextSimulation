package main;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
	private ArrayList<Integer> personsInGroup;

	private double weatherPercentage;
	private double animalPercentage;
	private double leaderPercentage;
	private double noMovementPercentage;
	private double offTrailPercentage;

	public void simulate() {
		personsInGroup = new ArrayList<>();
		for (int k = 0; k< maxPopulation*0.3; k++)
		{
			while (true) {
				int randomNum = ThreadLocalRandom.current().nextInt(1, maxPopulation);
				if (!personsInGroup.contains(randomNum))
				{
					personsInGroup.add(randomNum);
					break;
				}
			}

		}
		for (int i = 0; i < iterationCount; i++) {
			getSampleIteration(i);
		}
	}

	private void getSampleIteration(int iterationNumber) {
		iterationDataCounter = new IterationDataCounter();
		int currentPopulationCount;
		double iterationTime = iterationNumber * singleIterationTimeOffset + startTime;

		iterationDataCounter.setIterationTime(iterationTime);

			double normalDistributionValue = normalDistribution.density(iterationTime) * 10;
			currentPopulationCount = (int) (normalDistributionValue * maxPopulation);
			System.out.println("Population factor is " + normalDistributionValue);
			System.out.println("Population in " + iterationTime + ": " + currentPopulationCount);

		iterationDataCounter.setPopulationCount(currentPopulationCount);

		// one iteration is checking one person from population
		for (int pop = 0; pop < currentPopulationCount; pop++) {
			// weather alerts
			Random rnd = new Random();

			iterationDataCounter.incrementIndividualityCounter(1);
			iterationDataCounter.incrementTimeCounter(1);
			if(iterationTime >= this.timeRangeOneStart && iterationTime < this.timeRangeOneEnd)
			{
				if(rnd.nextDouble() <= weatherPercentage * this.timeRangeOneModifier)
				{
					iterationDataCounter.incrementWeatherAlertCounter(1);
				}
			} else if(iterationTime >= this.timeRangeTwoStart && iterationTime < this.timeRangeTwoEnd)
			{
				if(rnd.nextDouble() <= weatherPercentage * this.timeRangeTwoModifier)
				{
					iterationDataCounter.incrementWeatherAlertCounter(1);
				}
			}
			else {
				if(rnd.nextDouble() <= weatherPercentage * this.timeRangeThreeModifier)
				{
					iterationDataCounter.incrementWeatherAlertCounter(1);
				}
			}

			// situation alerts
			if(rnd.nextDouble() <= animalPercentage)
			{
				iterationDataCounter.incrementRelationCounter(1);
				iterationDataCounter.incrementSituationAlertCounter(1);
			}
			else if((personsInGroup.contains(pop)) && (rnd.nextDouble() <= leaderPercentage))
			{
				iterationDataCounter.incrementRelationCounter(1);
				iterationDataCounter.incrementSituationAlertCounter(1);
			}
			else if(rnd.nextDouble() <= noMovementPercentage)
			{
				iterationDataCounter.incrementActivityCounter(1);
				iterationDataCounter.incrementSituationAlertCounter(1);
			}
			else if(rnd.nextDouble() <= offTrailPercentage)
			{
				iterationDataCounter.incrementLocationCounter(1);
				iterationDataCounter.incrementSituationAlertCounter(1);
			}
		}

		Application.excelWriter.addToDataList(iterationDataCounter);
	}

	private Simulator(Builder builder) {
		iterationDataCounter = builder.iterationDataCounter;
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
		personsInGroup = builder.personsInGroup;
		weatherPercentage = builder.weatherPercentage;
		animalPercentage = builder.animalPercentage;
		leaderPercentage = builder.leaderPercentage;
		noMovementPercentage = builder.noMovementPercentage;
		offTrailPercentage = builder.offTrailPercentage;
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
		private ArrayList<Integer> personsInGroup;
		private double weatherPercentage;
		private double animalPercentage;
		private double leaderPercentage;
		private double noMovementPercentage;
		private double offTrailPercentage;
		private IterationDataCounter iterationDataCounter;

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

		public Builder personsInGroup(ArrayList<Integer> val) {
			personsInGroup = val;
			return this;
		}

		public Builder weatherPercentage(double val) {
			weatherPercentage = val;
			return this;
		}

		public Builder animalPercentage(double val) {
			animalPercentage = val;
			return this;
		}

		public Builder leaderPercentage(double val) {
			leaderPercentage = val;
			return this;
		}

		public Builder noMovementPercentage(double val) {
			noMovementPercentage = val;
			return this;
		}

		public Builder offTrailPercentage(double val) {
			offTrailPercentage = val;
			return this;
		}

		public Simulator build() {
			return new Simulator(this);
		}

		public Builder iterationDataCounter(IterationDataCounter val) {
			iterationDataCounter = val;
			return this;
		}
	}
}
