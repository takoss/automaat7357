package test.main;

import junit.framework.TestCase;
import main.APIRequest;
import main.OpenWeatherRequest;
import main.WeatherReport;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.UnknownHostException;

import static org.junit.Assert.fail;

/**
 * Created by admin on 14.09.2017.
 */
class OpenWeatherRequestTest {

    @Test
    void testAPIIPAddressDetermined() {
        try {
            OpenWeatherRequest request = new OpenWeatherRequest();
            request.getCurrentWeatherReport("Tallinn", OpenWeatherRequest.getKey());
        } catch (IOException e) {
            if (e instanceof UnknownHostException) {
                fail();
            }
        } catch (JSONException e) {}
    }

    @Test
    void testAPIJSONProcessingSuccessful() {
        try {
            new OpenWeatherRequest().getCurrentWeatherReport("Tallinn", OpenWeatherRequest.getKey());
        } catch (JSONException e) {
            fail();
        } catch (IOException e) {}
    }

    @Test
    void testAPIFalseKeyRejected() {
        try {
            new OpenWeatherRequest().getCurrentWeatherReport("Tallinn", "ooooooo");
        } catch (IOException e) {
            return;
        } catch (JSONException e) {}

        fail();
    }

    @Test
    void testAPIKeyAccepted() {
        try {
            new OpenWeatherRequest().getCurrentWeatherReport("Tallinn", OpenWeatherRequest.getKey());
        } catch (IOException e) {
            fail();
        } catch (JSONException e) {}
    }

    @Test
    void testGetWeatherNotExistingCityRequestReturnsNull() {
        Assert.assertTrue(new OpenWeatherRequest().getFullWeatherReport("aaepfokaefaef") == null);
    }

    @Test
    void testQueryGetsCorrectUnroundedCoordinatesforTallinn() {
        APIRequest request = new OpenWeatherRequest();
        WeatherReport weatherReport = request.getFullWeatherReport("Tallinn");
        System.out.println(weatherReport.getCoordinate().getLatitude());
        System.out.println(weatherReport.getCoordinate().getLongitude());
        Assert.assertTrue(59 == weatherReport.getCoordinate().getLatitude()
                .intValue());
        Assert.assertTrue(24 == weatherReport.getCoordinate().getLongitude
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
            fail();
        }
    }

    @Test
    void testQueryGetsCorrectUnroundedCoordinatesForCapeTown() {
        APIRequest request = new OpenWeatherRequest();
        WeatherReport weatherReport = request.getFullWeatherReport(
                "Cape Town");
        System.out.println(weatherReport.getCoordinate().getLatitude());
        System.out.println(weatherReport.getCoordinate().getLongitude());
        Assert.assertTrue(-33 == weatherReport.getCoordinate().getLatitude()
                .intValue());
        Assert.assertTrue(18 == weatherReport.getCoordinate().getLongitude
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
