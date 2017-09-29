package main;

import java.util.Scanner;

/**
 * Created by admin on 29.09.2017.
 */
public class Controller {

    public static void weatherRequestLoop() {
        OpenWeatherRequest request = new OpenWeatherRequest();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter location:");
            String nextLine = scanner.next();

            if ("quit".equals(nextLine) || "q".equals(nextLine)) return;

            WeatherReport report = request.getFullWeatherReport(nextLine);
            if (report == null) continue;

            System.out.println(nextLine);
            System.out.println("Coordinates (N:E): " + report.getCoordinate().getFormattedCoordinates());
            System.out.println("Current temperature: " + report.getCurrTemperature().getCelsius() + " Celsius\n");

            for (int i = 0; i < 3; ++i) {
                System.out.print(report.getThreeDayDateStrings().get(i) + "  ");
                System.out.print(report.getForecastThreeDayLowTemps().get(i).getCelsius() + "  ");
                System.out.println(report.getForecastThreeDayHighTemps().get(i).getCelsius());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        weatherRequestLoop();
    }

}
