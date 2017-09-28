package main;

import java.math.BigDecimal;

/**
 * Created by admin on 28.09.2017.
 */
public class Temperature {
    public static final int KELVIN = 0;

    public static final int CELSIUS = 1;

    private BigDecimal temperature;

    private int unit;

    public Temperature(BigDecimal temperature, int unit) {
        this.temperature = temperature;
        this.unit = unit;
    }

    public BigDecimal getKelvin() {
        return null;
    }

    public BigDecimal getCelsius() {
        return null;
    }

}
