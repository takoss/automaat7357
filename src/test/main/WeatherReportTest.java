package test.main;

import main.Temperature;
import main.WeatherReport;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.Temperature.CELSIUS;
import static main.Temperature.KELVIN;

/**
 * Created by admin on 28.09.2017.
 */
public class WeatherReportTest {

    @Test
    void testGetAverageTemperatureReturnsCorrectAverageWithCelsiusTemps() {
        List<Temperature> temps = new ArrayList<>();
        List<String> strings = Arrays.asList("-30", "31", "29.9", "29.89", "25.5", "0", "-30.0001");

        for (String str : strings) {
            temps.add(new Temperature(new BigDecimal(str), CELSIUS));
        }

        Assert.assertTrue(8 == WeatherReport.getAverageTemperature(temps).getCelsius().intValue());
    }

    @Test
    void testGetAverageTemperatureReturnsSameIfAllTempsTheSameValue() {
        List<Temperature> temps = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            temps.add(new Temperature(new BigDecimal(500), CELSIUS));
        }

        Assert.assertTrue(new BigDecimal(500).equals(WeatherReport.getAverageTemperature(temps).getCelsius()));
    }

    @Test
    void testGetAverageLowAndAverageHighReturnSameIfListsAreSame() {
        List<Temperature> temps = new ArrayList<>();
        List<String> strings = Arrays.asList("-5", "2", "17", "2000", "-203", "0", "-30.0501");

        for (String str : strings) {
            temps.add(new Temperature(new BigDecimal(str), CELSIUS));
        }

        WeatherReport report = new WeatherReport();
        report.setForecastThreeDayLowTemps(temps);
        report.setForecastThreeDayHighTemps(temps);

        Assert.assertTrue(report.getThreeDayAverageHighTemp().equals(report.getThreeDayAverageLowTemp()));
    }

    @Test
    void testGetAverageLowTemperatureReturnsCorrectAverageForCelsius() {
        List<Temperature> temps = new ArrayList<>();
        List<String> strings = Arrays.asList("-30", "31", "29.9", "29.89", "25.5", "0", "-30.0001");

        for (String str : strings) {
            temps.add(new Temperature(new BigDecimal(str), CELSIUS));
        }

        WeatherReport weatherReport = new WeatherReport();
        weatherReport.setForecastThreeDayLowTemps(temps);

        Assert.assertTrue(8 == weatherReport.getThreeDayAverageLowTemp().getCelsius().intValue());
    }

    @Test
    void testGetAverageHighTemperatureReturnsCorrectAverageForCelsius() {
        List<Temperature> temps = new ArrayList<>();
        List<String> strings = Arrays.asList("-5", "20", "255.8", "29.3", "-214", "0", "-30.0001");

        for (String str : strings) {
            temps.add(new Temperature(new BigDecimal(str), CELSIUS));
        }

        WeatherReport weatherReport = new WeatherReport();
        weatherReport.setForecastThreeDayHighTemps(temps);

        Assert.assertTrue(8 == weatherReport.getThreeDayAverageHighTemp().getCelsius().intValue());
    }

    @Test
    void testGetAverageTemperaturesMixedUnitsReturnsCorrectAverage() {
        Temperature t1 = new Temperature(new BigDecimal("30"), CELSIUS);
        Temperature t2 = new Temperature(new BigDecimal("273.15"), KELVIN);
        List<Temperature> temps = Arrays.asList(t1, t2);

        Assert.assertTrue(new BigDecimal("30").compareTo(WeatherReport.getAverageTemperature(temps)
                .getCelsius()) == 0);
    }
}
