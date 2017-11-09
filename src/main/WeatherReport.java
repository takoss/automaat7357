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

    private String cityName;

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

        newReport.cityName = current.getCityName();
        newReport.coordinate = current.getCoordinate();
        newReport.currTemperature = current.getCurrTemperature();
        newReport.threeDayDateStrings = forecast.getThreeDayDateStrings();
        newReport.forecastThreeDayHighTemps = forecast.getForecastThreeDayHighTemps();
        newReport.forecastThreeDayLowTemps = forecast.getForecastThreeDayLowTemps();

        return newReport;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
        System.out.println(this.cityName);
    }

    public String getCityName() {
        return this.cityName;
    }

    public String toString() {
        System.out.println(this.cityName);
        String str = this.cityName + "\n"
                + "Coordinates (N:E): " + this.getCoordinate().getFormattedCoordinates() + "\n"
                + "Current temperature: " + this.getCurrTemperature().getCelsius() + " Celsius\n";

        for (int i = 0; i < 3; ++i) {
            str += this.getThreeDayDateStrings().get(i) + "  " +
                   this.getForecastThreeDayLowTemps().get(i).getCelsius() + "  " +
                   this.getForecastThreeDayHighTemps().get(i).getCelsius() + "\n";
        }

        return str;
    }
}
