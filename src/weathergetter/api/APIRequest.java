package weathergetter.api;

import weathergetter.weatherreport.WeatherReport;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by admin on 28.09.2017.
 */
public interface APIRequest {

    WeatherReport getCurrentWeatherReport(String location) throws IOException, JSONException;

    WeatherReport getForecastWeatherReport(String location) throws IOException, JSONException;

    WeatherReport getFullWeatherReport(String location);

}
