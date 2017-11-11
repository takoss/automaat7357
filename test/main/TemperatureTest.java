package main;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by admin on 28.09.2017.
 */
public class TemperatureTest {

    @Test
    public void testGetKelvinCorrectWithZeroCelsius() {
        Temperature temp = new Temperature(new BigDecimal(0),
                Temperature.CELSIUS);
        Assert.assertTrue(new BigDecimal("273.15")
                                  .compareTo(temp.getKelvin()) == 0);
    }

    @Test
    public void testGetKelvinCorrectWithPositiveCelsius() {
        Temperature temp = new Temperature(new BigDecimal(200),
                Temperature.CELSIUS);
        Assert.assertTrue(new BigDecimal("473.15")
                                  .compareTo(temp.getKelvin()) == 0);
    }

    @Test
    public void testGetKelvinCorrectWithNegativeCelsius() {
        Temperature temp = new Temperature(new BigDecimal(-250),
                Temperature.CELSIUS);
        Assert.assertTrue(new BigDecimal("23.15")
                                  .compareTo(temp.getKelvin()) == 0);
    }

    @Test
    public void testGetCelsiusCorrectWithTwoHundredKelvin() {
        Temperature temp = new Temperature(new BigDecimal(200),
                Temperature.KELVIN);
        System.out.println(temp.getCelsius());
        Assert.assertTrue(new BigDecimal("-73.15")
                                  .compareTo(temp.getCelsius()) == 0);
    }

}
