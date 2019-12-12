package main;

public class IterationDataCounter {
    private int RelationEventCounter;
    private int ActivityEventCounter;
    private int LocationEventCounter;
    private int SituationAlertCounter; // licznik S
    private int IndividualityEventCounter;
    private int TimeEventCounter;
    private int WeatherAlertCounter; // licznik P

    public IterationDataCounter() {
        RelationEventCounter = 0;
        ActivityEventCounter = 0;
        LocationEventCounter = 0;
        SituationAlertCounter = 0;
        IndividualityEventCounter = 0;
        TimeEventCounter = 0;
        WeatherAlertCounter = 0;
    }

    public int getRelationEventCounter() {
        return RelationEventCounter;
    }

    public void setRelationEventCounter(int relationEventCounter) {
        RelationEventCounter = relationEventCounter;
    }

    public int getActivityEventCounter() {
        return ActivityEventCounter;
    }

    public void setActivityEventCounter(int activityEventCounter) {
        ActivityEventCounter = activityEventCounter;
    }

    public int getLocationEventCounter() {
        return LocationEventCounter;
    }

    public void setLocationEventCounter(int locationEventCounter) {
        LocationEventCounter = locationEventCounter;
    }

    public int getSituationAlertCounter() {
        return SituationAlertCounter;
    }

    public void setSituationAlertCounter(int situationAlertCounter) {
        SituationAlertCounter = situationAlertCounter;
    }

    public int getIndividualityEventCounter() {
        return IndividualityEventCounter;
    }

    public void setIndividualityEventCounter(int individualityEventCounter) {
        IndividualityEventCounter = individualityEventCounter;
    }

    public int getTimeEventCounter() {
        return TimeEventCounter;
    }

    public void setTimeEventCounter(int timeEventCounter) {
        TimeEventCounter = timeEventCounter;
    }

    public int getWeatherAlertCounter() {
        return WeatherAlertCounter;
    }

    public void setWeatherAlertCounter(int weatherAlertCounter) {
        WeatherAlertCounter = weatherAlertCounter;
    }
}
