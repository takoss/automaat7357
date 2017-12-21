package weathergetter.weatherreport;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by admin on 28.09.2017.
 */
public class Validator {

    static void validateCoordinates(Coordinate coord) throws Exception {
        if (coord.getLongitude().compareTo(new BigDecimal("-180")) == -1
                || coord.getLongitude().compareTo(new BigDecimal("180")) ==
                   1) {
            throw new Exception("Invalid longitude: " + coord.getLongitude());
        }

        if (coord.getLatitude().compareTo(new BigDecimal("-90")) == -1
            || coord.getLatitude().compareTo(new BigDecimal("90")) ==
               1) {
            throw new Exception("Invalid latitude: " + coord.getLongitude());
        }
    }

    static void validateAllTemperatures(WeatherReport report) throws Exception {
        Validator.validateTemperature(report.getCurrTemperature());

        Validator.validateListOfTemperatures(report.getForecastThreeDayHighTemps());
        Validator.validateListOfTemperatures(report.getForecastThreeDayLowTemps());

        Validator.validateHighAndLowTempLists(report.getForecastThreeDayHighTemps(),
                report.getForecastThreeDayLowTemps());
    }

    private static void validateTemperature(Temperature temp) throws Exception {
        if (temp.getCelsius().compareTo(new BigDecimal("-95")) == -1
            || temp.getCelsius().compareTo(new BigDecimal("65")) == 1) {
            throw new Exception("Invalid temperature: " + temp.getCelsius());
        }
    }

    private static void validateHighAndLowTempLists(List<Temperature> highs, List<Temperature> lows) throws Exception {
        if (highs.size() != lows.size()) throw new Exception("High and low lists not the same length!");

        for (int i = 0; i < highs.size(); ++i) {
            validateHighAndLowTempPair(highs.get(i), lows.get(i));
        }
    }

    private static void validateHighAndLowTempPair(Temperature high, Temperature low) throws Exception {
        System.out.println("high" + high.getCelsius() + ", low: " + low.getCelsius());
        if (high.getCelsius().compareTo(low.getCelsius()) == -1) throw new Exception("Low temp higher than high temp");

        validateTemperature(high);
        validateTemperature(low);
    }

    private static void validateListOfTemperatures(List<Temperature> temps)
            throws Exception {
        for (Temperature temp : temps) {
            validateTemperature(temp);
        }
    }
}
