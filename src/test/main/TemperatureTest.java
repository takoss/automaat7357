package test.main;

import main.Coordinate;
import main.Temperature;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
    void testGetCelsiusCorrectWithTwoHundredKelvin() {
        Temperature temp = new Temperature(new BigDecimal(200),
                Temperature.KELVIN);
        Assert.assertTrue(new BigDecimal("-73.15")
                                  .compareTo(temp.getCelsius()) == 0);
    }

}
