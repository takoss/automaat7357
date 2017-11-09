package main;

import javax.print.attribute.standard.Destination;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by admin on 29.09.2017.
 */
public class Controller {

    public static void weatherRequestLoop() {
        OpenWeatherRequest request = new OpenWeatherRequest();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter location:");
            String nextLine = scanner.nextLine();

            if ("quit".equals(nextLine) || "q".equals(nextLine)) return;

            WeatherReport report = request.getFullWeatherReport(nextLine);
            if (report == null) continue;

            System.out.println(report);
        }
    }

    public static Stream<String> readCitiesStreamFromFile(String url) throws IOException {
        return Files.lines(Paths.get(url));
    }

    public static void writeCityToOutputFile(Stream<String> cityList, String outputUrl) throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputUrl), "utf-8"))) {
            OpenWeatherRequest request = new OpenWeatherRequest();
            Stream<WeatherReport> reports = cityList.map(request::getFullWeatherReport);


            writer.append("something");


            writer.close();
        }

    }

    public static void printInputCitiesInfoToOutput() {
        Stream<String> cities = Stream.empty();
        try {
            cities =readCitiesStreamFromFile("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
            //return;
        }

        try {
            writeCityToOutputFile(cities, "output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //printInputCitiesInfoToOutput();
        weatherRequestLoop();
    }

}
