package test.main;

import junit.framework.TestCase;
import main.APIRequest;
import main.OpenWeatherRequest;
import main.WeatherReport;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;

/**
 * Created by admin on 14.09.2017.
 */
class OpenWeatherRequestTest {

    @Test
    void testAPIConnects() {
        fail();
    }

    @Test
    void testAPIKeyAccepted() {
        fail();
    }

    @Test
    void testAPICurrentWeatherResponseValid() {
        fail();
    }

    @Test
    void testAPIForecastResponseValid() {
        fail();
    }

    @Test
    void testGetWeatherNotExistingCityRequestReturnsNull() {
        Assert.assertTrue(new OpenWeatherRequest().getFullWeatherReport("aaepfokaefaef") == null);
    }

    @Test
    void testQueryGetsCorrectCoordinatesforTallinn() {
        APIRequest request = new OpenWeatherRequest();
        WeatherReport weatherReport = request.getFullWeatherReport("Tallinn");
        Assert.assertTrue(59 == weatherReport.getCoordinates().getLatitude()
                .intValue());
        Assert.assertTrue(24 == weatherReport.getCoordinates().getLongitude
                ().intValue());
    }

    @Test
    void testQueryGetsValidTemperaturesForTallinn() {
        APIRequest request = new OpenWeatherRequest();
        WeatherReport report = request.getFullWeatherReport("Tallinn");
        try {
            Validator.validateAllTemperatures(report);
        } catch (Exception e) {
            e.printStackTrace();
            TestCase.fail();
        }
    }

    @Test
    void testQueryGetsCorrectCoordinatesForCapeTown() {
        APIRequest request = new OpenWeatherRequest();
        WeatherReport weatherReport = request.getFullWeatherReport(
                "Cape Town");
        Assert.assertTrue(18 == weatherReport.getCoordinates().getLatitude()
                .intValue());
        Assert.assertTrue(-33 == weatherReport.getCoordinates().getLongitude
                ().intValue());
    }

    @Test
    void testQueryGetsValidTemperaturesForCapeTown() {
        APIRequest request = new OpenWeatherRequest();
        WeatherReport report = request.getFullWeatherReport("Cape Town");
        try {
            Validator.validateAllTemperatures(report);
        } catch (Exception e) {
            e.printStackTrace();
            TestCase.fail();
        }
    }
}
