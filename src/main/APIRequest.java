package main;

/**
 * Created by admin on 28.09.2017.
 */
public interface APIRequest {

    WeatherReport getCurrentWeather(String location);

    WeatherReport getForecastWeather(String location);

    WeatherReport getFullWeatherReport(String location);
}
