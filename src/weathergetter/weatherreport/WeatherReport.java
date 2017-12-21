package weathergetter.weatherreport;

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

    Coordinate getCoordinate() {
        return coordinate;
    }

    Temperature getCurrTemperature() {
        return currTemperature;
    }

    List<String> getThreeDayDateStrings() {
        return threeDayDateStrings;
    }

    List<Temperature> getForecastThreeDayHighTemps() {
        return forecastThreeDayHighTemps;
    }

    List<Temperature> getForecastThreeDayLowTemps() {
        return forecastThreeDayLowTemps;
    }

    void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    void setCurrTemperature(Temperature currTemperature) {
        this.currTemperature = currTemperature;
    }

    void setThreeDayDateStrings(List<String> threeDayDateStrings) {
        this.threeDayDateStrings = threeDayDateStrings;
    }

    void setForecastThreeDayHighTemps(List<Temperature> forecastThreeDayHighTemps) {
        this.forecastThreeDayHighTemps = forecastThreeDayHighTemps;
    }

    void setForecastThreeDayLowTemps(List<Temperature> forecastThreeDayLowTemps) {
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

    void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return this.cityName;
    }

    @Override
    public String toString() {
        String str = this.cityName + "\n"
                + "Coordinates (N:E): " + this.getCoordinate().getFormattedCoordinates() + "\n"
                + "Current temperature: " + this.getCurrTemperature().getCelsius() + " Celsius\n";

        for (int i = 0; i < 3; ++i) {
            str += this.getThreeDayDateStrings().get(i) + "  " +
                   this.getForecastThreeDayLowTemps().get(i).getCelsius() + "  " +
                   this.getForecastThreeDayHighTemps().get(i).getCelsius() + "\n";
        }

        return str + "\n";
    }
}
