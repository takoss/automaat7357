package main;

import org.json.JSONObject;

import java.math.BigDecimal;

class CurrentWeatherSetter {
    static void setInformationForWeatherReport(JSONObject json, WeatherReport currentReport) {
        currentReport.setCityName(json.getString("name"));

        currentReport.setCurrTemperature(new Temperature(new BigDecimal(json.getJSONObject("main").get("temp").toString()),
                Temperature.KELVIN));

        JSONObject coords = json.getJSONObject("coord");
        BigDecimal latitude = new BigDecimal(coords.get("lat").toString());
        BigDecimal longitude = new BigDecimal(coords.get("lon").toString());
        currentReport.setCoordinate(new Coordinate(latitude, longitude));
    }
}
