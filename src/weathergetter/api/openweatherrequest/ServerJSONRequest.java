package weathergetter.api.openweatherrequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by admin on 21.12.2017.
 */
public class ServerJSONRequest {

    private static final int CURRENT_WEATHER = 0;

    private static final int FORECAST = 1;

    private String key = "2c73510650453bd0d063643d9c522cbb";

    public ServerJSONRequest() {}

    public ServerJSONRequest(String key) {
        this.key = key;
    }

    private JSONObject getJsonObject(String location, int type) throws IOException, JSONException {
        return new JSONObject(org.apache.commons.io.IOUtils.toString(
                new URL(getAPIRequestLink(location, type)), Charset.forName
                        ("UTF-8"))
        );
    }

    JSONObject getCurrentWeatherJSONObject(String location) throws
            IOException, JSONException {
        return getJsonObject(location, CURRENT_WEATHER);
    }

    JSONObject getForecastWeatherJSONObject(String location) throws
            IOException, JSONException {
        return getJsonObject(location, FORECAST);
    }

    private String getAPIRequestLink(String location, int type) {
        String baseURL = "http://api.openweathermap.org/data/2.5/";
        switch (type) {
            case CURRENT_WEATHER:
                return baseURL + "weather?q=" + location + "&APPID=" + getKey();
            case FORECAST:
                return baseURL + "forecast?q=" + location + "&APPID=" + getKey();
            default:
                throw new RuntimeException("Invalid weather type");
        }
    }

    private String getKey() {
        return this.key;
    }

}
