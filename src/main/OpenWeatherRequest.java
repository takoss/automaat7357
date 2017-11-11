package main;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by admin on 14.09.2017.
 */
public class OpenWeatherRequest implements APIRequest {

    private static final int CURRENT_WEATHER = 0;

    private static final int FORECAST = 1;

    private static final String KEY = "2c73510650453bd0d063643d9c522cbb";

    @Override
    public WeatherReport getCurrentWeatherReport(String location, String key) throws IOException, JSONException {
        JSONObject json = new JSONObject(org.apache.commons.io.IOUtils.toString(
                new URL(getAPIRequestLink(CURRENT_WEATHER, location, key)), Charset.forName("UTF-8"))
        );

        WeatherReport currentReport = new WeatherReport();
        CurrentWeatherSetter.setInformationForWeatherReport(json, currentReport);
        return currentReport;
    }

    @Override
    public WeatherReport getForecastWeatherReport(String location, String key) throws IOException, JSONException {
        JSONObject json = new JSONObject(org.apache.commons.io.IOUtils.toString(
                new URL(OpenWeatherRequest.getAPIRequestLink
                        (OpenWeatherRequest.FORECAST, location, key)), Charset.forName("UTF-8"))
        );

        WeatherReport forecastReport = new WeatherReport();
        ForecastWeatherSetter.setInformationForWeatherReport(json, forecastReport);
        return forecastReport;
    }

    @Override
    public WeatherReport getFullWeatherReport(String location) {
        try {
            WeatherReport currentWeatherReport = this.getCurrentWeatherReport(location, KEY);
            WeatherReport forecastWeatherReport = this.getForecastWeatherReport(location, KEY);

            return WeatherReport.generateMergedCurrAndForecastWeatherReports(
                    currentWeatherReport, forecastWeatherReport);
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Location not found.");
            } else {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getAPIRequestLink(int type, String location, String key) {
        String baseURL = "http://api.openweathermap.org/data/2.5/";
        switch (type) {
            case CURRENT_WEATHER:
                return baseURL + "weather?q=" + location + "&APPID=" + key;
            case FORECAST:
                return baseURL + "forecast?q=" + location + "&APPID=" + key;
            default:
                throw new RuntimeException("Invalid weather type");
        }
    }

    static String getKey() {
        return KEY;
    }

}
