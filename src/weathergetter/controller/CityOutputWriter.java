package weathergetter.controller;

import weathergetter.api.APIRequest;
import weathergetter.file.OutputWriter;
import weathergetter.weatherreport.WeatherReport;

/**
 * Created by admin on 20.12.2017.
 */
public class CityOutputWriter {
    private final APIRequest apiRequest;
    private final OutputWriter outputWriter;

    public CityOutputWriter(OutputWriter outputWriter, APIRequest apiRequest) {
        this.outputWriter = outputWriter;
        this.apiRequest = apiRequest;
    }

    public void writeCityInfoAsFile(String city) {
        WeatherReport report = apiRequest.getFullWeatherReport(city);
        if (report == null) System.out.println(city + " not found.\n");
        else outputWriter.write(
                report.toString(), "cities\\" + report.getCityName() + ""
                                   + ".txt");
    }
}
