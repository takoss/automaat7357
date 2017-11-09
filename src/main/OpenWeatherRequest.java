package main;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

        currentReport.setCityName(json.getString("name"));

        currentReport.setCurrTemperature(new Temperature(new BigDecimal(json.getJSONObject("main").get("temp").toString()),
                Temperature.KELVIN));


        JSONObject coords = json.getJSONObject("coord");
        BigDecimal latitude = new BigDecimal(coords.get("lat").toString());
        BigDecimal longitude = new BigDecimal(coords.get("lon").toString());
        currentReport.setCoordinate(new Coordinate(latitude, longitude));

        return currentReport;
    }

    @Override
    public WeatherReport getForecastWeatherReport(String location, String key) throws IOException, JSONException {
        JSONObject json = new JSONObject(org.apache.commons.io.IOUtils.toString(
                new URL(getAPIRequestLink(FORECAST, location, key)), Charset.forName("UTF-8"))
        );

        WeatherReport forecastReport = new WeatherReport();

        forecastReport.setCityName(json.getJSONObject("city").getString("name"));

        JSONObject coord = json.getJSONObject("city").getJSONObject("coord");
        BigDecimal latitude = new BigDecimal(coord.get("lat").toString());
        BigDecimal longitude = new BigDecimal(coord.get("lon").toString());
        forecastReport.setCoordinate(new Coordinate(latitude, longitude));

        JSONArray jsonArray = json.getJSONArray("list");

        forecastReport.setForecastThreeDayHighTemps(new ArrayList<>());
        forecastReport.setForecastThreeDayLowTemps(new ArrayList<>());
        forecastReport.setThreeDayDateStrings(new ArrayList<>());

        Temperature dayLow = new Temperature(new BigDecimal(9000), Temperature.KELVIN);
        Temperature dayHigh = new Temperature(new BigDecimal(0), Temperature.KELVIN);
        forecastReport.getThreeDayDateStrings().add(jsonArray.getJSONObject(0)
                .get("dt_txt").toString().substring(0, 10));

        String dateString;
        JSONObject mainObject;
        int entry = 0;
        while (true) {
            mainObject = jsonArray.getJSONObject(entry).getJSONObject("main");
            dateString = jsonArray.getJSONObject(entry).get("dt_txt").toString().substring(0, 10);

            if (forecastReport.getThreeDayDateStrings().get(forecastReport.getThreeDayDateStrings().size() - 1)
                    .equals(dateString)) {

                if (dayLow.getKelvin().compareTo(new BigDecimal(mainObject.get("temp_min").toString())) == 1) {
                    dayLow = new Temperature(new BigDecimal(mainObject.get("temp_min").toString()), Temperature.KELVIN);
                }

                if (dayHigh.getKelvin().compareTo(new BigDecimal(mainObject.get("temp_max").toString())) == -1) {
                    dayHigh = new Temperature(new BigDecimal(mainObject.get("temp_max").toString()),
                            Temperature.KELVIN);
                }
                ++entry;
            } else {
                forecastReport.getForecastThreeDayLowTemps().add(dayLow);
                forecastReport.getForecastThreeDayHighTemps().add(dayHigh);

                if (forecastReport.getThreeDayDateStrings().size() == 3) break;
                forecastReport.getThreeDayDateStrings().add(dateString);

                dayLow = new Temperature(new BigDecimal(9000), Temperature.KELVIN);
                dayHigh = new Temperature(new BigDecimal(0), Temperature.KELVIN);
            }
        }

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
                throw new RuntimeException("Invalid type");
        }
    }

    public static String getKey() {
        return KEY;
    }

}
