package weathergetter.controller;

import weathergetter.api.openweatherrequest.GetWeatherReport;
import weathergetter.api.openweatherrequest.ServerJSONRequest;
import weathergetter.weatherreport.WeatherReport;
import weathergetter.file.InputReader;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by admin on 29.09.2017.
 */
public class Controller {

    private final InputReader inputReader;
    private final CityOutputWriter cityOutputWriter;

    public Controller(InputReader inputReader,
                      CityOutputWriter cityOutputWriter) {
        this.inputReader = inputReader;
        this.cityOutputWriter = cityOutputWriter;
    }

    public static void weatherRequestLoop() {
        GetWeatherReport request = new GetWeatherReport(new ServerJSONRequest());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter location:");
            String nextLine = scanner.nextLine();

            if ("quit".equals(nextLine) || "q".equals(nextLine)) return;

            WeatherReport report = request.getFullWeatherReport(nextLine);
            if (report == null) continue;

            System.out.print(report);
        }
    }

    public void printInputCitiesInfoToOutput() {
        Stream<String> cities;
        try {
            cities = this.inputReader.readFromURL("input.txt");
        } catch (IOException e) {
            cities = Stream.empty();
            e.printStackTrace();
        }

        cities.forEach(cityOutputWriter::writeCityInfoAsFile);
    }
}
