package weathergetter.weatherreport;

import junit.framework.TestCase;
import weathergetter.api.APIRequest;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import weathergetter.api.openweatherrequest.GetWeatherReport;
import weathergetter.api.openweatherrequest.ServerJSONRequest;

import java.io.IOException;
import java.net.UnknownHostException;

import static org.junit.Assert.fail;

/**
 * Created by admin on 14.09.2017.
 */
public class GetWeatherReportTest {

    @Test
    public void testAPIIPAddressDetermined() {
        try {
            GetWeatherReport request = new GetWeatherReport(new ServerJSONRequest());
            request.getCurrentWeatherReport("Tallinn");
        } catch (IOException e) {
            if (e instanceof UnknownHostException) {
                fail();
            }
        } catch (JSONException e) {}
    }

    @Test
    public void testAPIJSONProcessingSuccessful() {
        try {
            new GetWeatherReport(new ServerJSONRequest()).getCurrentWeatherReport("Tallinn");
        } catch (JSONException e) {
            fail();
        } catch (IOException e) {}
    }

    @Test
    public void testAPIFalseKeyRejected() {
        try {
            new GetWeatherReport(new ServerJSONRequest("oooooooo"))
                    .getCurrentWeatherReport("Tallinn");
        } catch (IOException e) {
            return;
        } catch (JSONException e) {}

        fail();
    }

    @Test
    public void testAPIKeyAccepted() {
        try {
            new GetWeatherReport(new ServerJSONRequest()).getCurrentWeatherReport("Tallinn");
        } catch (IOException e) {
            fail();
        } catch (JSONException e) {}
    }

    @Test
    public void testGetWeatherNotExistingCityRequestReturnsNull() {
        Assert.assertTrue(new GetWeatherReport(new ServerJSONRequest()).getFullWeatherReport
                ("aaepfokaefaef") == null);
    }

    @Test
    public void testQueryGetsCorrectUnroundedCoordinatesforTallinn() {
        APIRequest request = new GetWeatherReport(new ServerJSONRequest());
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
        APIRequest request = new GetWeatherReport(new ServerJSONRequest());
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
        APIRequest request = new GetWeatherReport(new ServerJSONRequest());
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
        APIRequest request = new GetWeatherReport(new ServerJSONRequest());
        WeatherReport report = request.getFullWeatherReport("Cape Town");
        try {
            Validator.validateAllTemperatures(report);
        } catch (Exception e) {
            e.printStackTrace();
            TestCase.fail();
        }
    }



}
