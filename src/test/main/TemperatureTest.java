package test.main;

import main.Coordinate;
import main.Temperature;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.Temperature.CELSIUS;
import static main.Temperature.KELVIN;

/**
 * Created by admin on 28.09.2017.
 */
public class TemperatureTest {

    @Test
    void testGetKelvinCorrectWithZeroCelsius() {
        Temperature temp = new Temperature(new BigDecimal(0),
                Temperature.CELSIUS);
        Assert.assertTrue(new BigDecimal("273.15")
                                  .compareTo(temp.getKelvin()) == 0);
    }

    @Test
    void testGetKelvinCorrectWithPositiveCelsius() {
        Temperature temp = new Temperature(new BigDecimal(200),
                Temperature.CELSIUS);
        Assert.assertTrue(new BigDecimal("473.15")
                                  .compareTo(temp.getKelvin()) == 0);
    }

    @Test
    void testGetKelvinCorrectWithNegativeCelsius() {
        Temperature temp = new Temperature(new BigDecimal(-250),
                Temperature.CELSIUS);
        Assert.assertTrue(new BigDecimal("23.15")
                                  .compareTo(temp.getKelvin()) == 0);
    }

    @Test
    void testGetCelsiusCorrectWithTwoHundredKelvin() {
        Temperature temp = new Temperature(new BigDecimal(200),
                Temperature.KELVIN);
        System.out.println(temp.getCelsius());
        Assert.assertTrue(new BigDecimal("-73.15")
                                  .compareTo(temp.getCelsius()) == 0);
    }

}
