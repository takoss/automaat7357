package main;

import java.math.BigDecimal;

/**
 * Created by admin on 28.09.2017.
 */
public class Temperature {

    public static final int KELVIN = 0;

    public static final int CELSIUS = 1;

    private static final BigDecimal KELVIN_FOR_ZERO_CELSIUS = new BigDecimal("273.15");

    private BigDecimal temperature;

    private int unit;

    public Temperature(BigDecimal temperature, int unit) {
        this.temperature = temperature;
        this.unit = unit;
    }

    public BigDecimal getKelvin() {
        switch (unit) {
            case KELVIN:
                return temperature;
            case CELSIUS:
                return temperature.add(KELVIN_FOR_ZERO_CELSIUS);
            default:
                return null;
        }
    }

    public BigDecimal getCelsius() {
        switch (unit) {
            case KELVIN:
                return temperature.subtract(KELVIN_FOR_ZERO_CELSIUS);
            case CELSIUS:
                return temperature;
            default:
                return null;
        }
    }

}
