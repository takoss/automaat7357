package weathergetter.api.openweatherrequest;

import weathergetter.api.APIRequest;
import weathergetter.weatherreport.CurrentWeatherSetter;
import weathergetter.weatherreport.ForecastWeatherSetter;
import weathergetter.weatherreport.WeatherReport;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by admin on 14.09.2017.
 */
public class GetWeatherReport implements APIRequest {

    private ServerJSONRequest request;

    public GetWeatherReport(ServerJSONRequest request) {
        this.request = request;
    }

    @Override
    public WeatherReport getCurrentWeatherReport(String location) throws IOException, JSONException {
        JSONObject json = request.getCurrentWeatherJSONObject
                (location);

        WeatherReport currentReport = new WeatherReport();
        CurrentWeatherSetter.setInformationForWeatherReport(json, currentReport);
        return currentReport;
    }

    @Override
    public WeatherReport getForecastWeatherReport(String location) throws IOException, JSONException {
        JSONObject json = request.getForecastWeatherJSONObject
                (location);

        WeatherReport forecastReport = new WeatherReport();
        ForecastWeatherSetter.setInformationForWeatherReport(json, forecastReport);
        return forecastReport;
    }

    @Override
    public WeatherReport getFullWeatherReport(String location) {
        try {
            WeatherReport currentWeatherReport = this.getCurrentWeatherReport(location);
            WeatherReport forecastWeatherReport = this.getForecastWeatherReport(location);

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


}
