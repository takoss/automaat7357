package main;

import junit.framework.TestCase;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.UnknownHostException;

import static org.junit.Assert.fail;

/**
 * Created by admin on 14.09.2017.
 */
public class OpenWeatherRequestTest {

    @Test
    public void testAPIIPAddressDetermined() {
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
    public void testAPIJSONProcessingSuccessful() {
        try {
            new OpenWeatherRequest().getCurrentWeatherReport("Tallinn", OpenWeatherRequest.getKey());
        } catch (JSONException e) {
            fail();
        } catch (IOException e) {}
    }

    @Test
    public void testAPIFalseKeyRejected() {
        try {
            new OpenWeatherRequest().getCurrentWeatherReport("Tallinn", "ooooooo");
        } catch (IOException e) {
            return;
        } catch (JSONException e) {}

        fail();
    }

    @Test
    public void testAPIKeyAccepted() {
        try {
            new OpenWeatherRequest().getCurrentWeatherReport("Tallinn", OpenWeatherRequest.getKey());
        } catch (IOException e) {
            fail();
        } catch (JSONException e) {}
    }

    @Test
    public void testGetWeatherNotExistingCityRequestReturnsNull() {
        Assert.assertTrue(new OpenWeatherRequest().getFullWeatherReport("aaepfokaefaef") == null);
    }

    @Test
    public void testQueryGetsCorrectUnroundedCoordinatesforTallinn() {
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
    public void testQueryGetsValidTemperaturesForTallinn() {
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
    public void testQueryGetsCorrectUnroundedCoordinatesForCapeTown() {
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
    public void testQueryGetsValidTemperaturesForCapeTown() {
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
