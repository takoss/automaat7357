package main;

import java.util.List;

/**
 * Created by admin on 28.09.2017.
 */
public class WeatherReport {

    private Coordinate coordinate;

    private Temperature currTemperature;

    private List<String> threeDayDateStrings;

    private List<Temperature> forecastThreeDayHighTemps;

    private List<Temperature> forecastThreeDayLowTemps;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Temperature getCurrTemperature() {
        return currTemperature;
    }

    public List<String> getThreeDayDateStrings() {
        return threeDayDateStrings;
    }

    public List<Temperature> getForecastThreeDayHighTemps() {
        return forecastThreeDayHighTemps;
    }

    public List<Temperature> getForecastThreeDayLowTemps() {
        return forecastThreeDayLowTemps;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setCurrTemperature(Temperature currTemperature) {
        this.currTemperature = currTemperature;
    }

    public void setThreeDayDateStrings(List<String> threeDayDateStrings) {
        this.threeDayDateStrings = threeDayDateStrings;
    }

    public void setForecastThreeDayHighTemps(List<Temperature> forecastThreeDayHighTemps) {
        this.forecastThreeDayHighTemps = forecastThreeDayHighTemps;
    }

    public void setForecastThreeDayLowTemps(List<Temperature> forecastThreeDayLowTemps) {
        this.forecastThreeDayLowTemps = forecastThreeDayLowTemps;
    }

    public static WeatherReport generateMergedCurrAndForecastWeatherReports(
            WeatherReport current, WeatherReport forecast) {
        WeatherReport newReport = new WeatherReport();

        newReport.coordinate = current.getCoordinate();
        newReport.currTemperature = current.getCurrTemperature();
        newReport.threeDayDateStrings = forecast.getThreeDayDateStrings();
        newReport.forecastThreeDayHighTemps = forecast.getForecastThreeDayHighTemps();
        newReport.forecastThreeDayLowTemps = forecast.getForecastThreeDayLowTemps();

        return newReport;
    }

}
