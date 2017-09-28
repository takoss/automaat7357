package main;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by admin on 28.09.2017.
 */
public class WeatherReport {
    private Coordinate coordinates;

    private Temperature currTemperature;

    private List<Temperature> forecastThreeDayHighTemps;

    private List<Temperature> forecastThreeDayLowTemps;

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public Temperature getCurrTemperature() {
        return currTemperature;
    }

    public List<Temperature> getForecastThreeDayHighTemps() {
        return forecastThreeDayHighTemps;
    }

    public List<Temperature> getForecastThreeDayLowTemps() {
        return forecastThreeDayLowTemps;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public void setCurrTemperature(Temperature currTemperature) {
        this.currTemperature = currTemperature;
    }

    public void setForecastThreeDayHighTemps(List<Temperature> forecastThreeDayHighTemps) {
        this.forecastThreeDayHighTemps = forecastThreeDayHighTemps;
    }

    public void setForecastThreeDayLowTemps(List<Temperature> forecastThreeDayLowTemps) {
        this.forecastThreeDayLowTemps = forecastThreeDayLowTemps;
    }

    public static Temperature getAverageTemperature(List<Temperature> temps) {
        return new Temperature(new BigDecimal(50), Temperature.CELSIUS);
    }

    public Temperature getThreeDayAverageLowTemp() {
        return new Temperature(new BigDecimal(-5), Temperature.CELSIUS);
    }

    public Temperature getThreeDayAverageHighTemp() {
        return new Temperature(new BigDecimal(100), Temperature.CELSIUS);
    }

}
