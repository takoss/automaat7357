package main;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class ForecastWeatherSetter {

    static void setInformationForWeatherReport(JSONObject json, WeatherReport forecastReport) {
        forecastReport.setCityName(json.getJSONObject("city").getString("name"));

        JSONObject coord = json.getJSONObject("city").getJSONObject("coord");
        BigDecimal latitude = new BigDecimal(coord.get("lat").toString());
        BigDecimal longitude = new BigDecimal(coord.get("lon").toString());
        forecastReport.setCoordinate(new Coordinate(latitude, longitude));

        setThreeDayHighsAndLows(json, forecastReport);
    }

    private static void setThreeDayHighsAndLows(JSONObject json, WeatherReport forecastReport) {
        JSONArray jsonArray = json.getJSONArray("list");

        List<Temperature> highTemps = new ArrayList<>();
        List<Temperature> lowTemps = new ArrayList<>();
        List<String> dateStrings = new ArrayList<>();

        // High and min temps to be found.
        Temperature dayLow = new Temperature(new BigDecimal(9000), Temperature.KELVIN);
        Temperature dayHigh = new Temperature(new BigDecimal(0), Temperature.KELVIN);

        // First date.
        dateStrings.add(jsonArray.getJSONObject(0).get("dt_txt").toString().substring(0, 10));

        String dateString;
        JSONObject mainObject;
        int entry = 0;
        while (true) {
            mainObject = jsonArray.getJSONObject(entry).getJSONObject("main");
            dateString = jsonArray.getJSONObject(entry).getString("dt_txt").substring(0, 10);

            if (dateStrings.get(dateStrings.size() - 1).equals(dateString)) { // still the same day

                if (dayLow.getKelvin().compareTo(new BigDecimal(mainObject.get("temp_min").toString())) > 0) {
                    dayLow = new Temperature(new BigDecimal(mainObject.get("temp_min").toString()), Temperature
                            .KELVIN);
                }

                if (dayHigh.getKelvin().compareTo(new BigDecimal(mainObject.get("temp_max").toString())) < 0) {
                    dayHigh = new Temperature(new BigDecimal(mainObject.get("temp_max").toString()),
                            Temperature.KELVIN);
                }
                ++entry;
            } else { // day has changed
                highTemps.add(dayHigh);
                lowTemps.add(dayLow);

                if (dateStrings.size() == 3) break;

                dateStrings.add(dateString);
                dayLow = new Temperature(new BigDecimal(9000), Temperature.KELVIN);
                dayHigh = new Temperature(new BigDecimal(0), Temperature.KELVIN);
            }
        }

        forecastReport.setForecastThreeDayHighTemps(highTemps);
        forecastReport.setForecastThreeDayLowTemps(lowTemps);
        forecastReport.setThreeDayDateStrings(dateStrings);
    }

}
