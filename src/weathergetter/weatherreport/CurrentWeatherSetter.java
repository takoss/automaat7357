package weathergetter.weatherreport;

import org.json.JSONException;
import org.json.JSONObject;
import weathergetter.weatherreport.Coordinate;
import weathergetter.weatherreport.Temperature;
import weathergetter.weatherreport.WeatherReport;

import java.math.BigDecimal;

public class CurrentWeatherSetter {

    public static void setInformationForWeatherReport(JSONObject json,
                                                WeatherReport currentReport) throws JSONException {
        currentReport.setCityName(json.getString("name"));

        currentReport.setCurrTemperature(new Temperature(new BigDecimal(json.getJSONObject("main").get("temp").toString()),
                Temperature.KELVIN));

        JSONObject coords = json.getJSONObject("coord");
        BigDecimal latitude = new BigDecimal(coords.get("lat").toString());
        BigDecimal longitude = new BigDecimal(coords.get("lon").toString());
        currentReport.setCoordinate(new Coordinate(latitude, longitude));
    }
}
