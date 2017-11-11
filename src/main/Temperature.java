package main;

import java.math.BigDecimal;

/**
 * Created by admin on 28.09.2017.
 */
class Temperature {

    static final int KELVIN = 0;

    static final int CELSIUS = 1;

    private static final BigDecimal KELVIN_FOR_ZERO_CELSIUS = new BigDecimal("273.15");

    private BigDecimal temperature;

    private int unit;

    Temperature(BigDecimal temperature, int unit) {
        this.temperature = temperature;
        this.unit = unit;
    }

    BigDecimal getKelvin() {
        switch (unit) {
            case KELVIN:
                return temperature;
            case CELSIUS:
                return temperature.add(KELVIN_FOR_ZERO_CELSIUS);
            default:
                throw new RuntimeException("Unit does not exist!");
        }
    }

    BigDecimal getCelsius() {
        switch (unit) {
            case KELVIN:
                return temperature.subtract(KELVIN_FOR_ZERO_CELSIUS);
            case CELSIUS:
                return temperature;
            default:
                throw new RuntimeException("Unit does not exist!");
        }
    }

}
