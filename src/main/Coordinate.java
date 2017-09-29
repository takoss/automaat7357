package main;

import java.math.BigDecimal;

/**
 * Created by admin on 28.09.2017.
 */
public class Coordinate {

    private BigDecimal longitude;

    private BigDecimal latitude;

    public Coordinate(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getFormattedCoordinates() {
        int latitude = bigDecimalRoundedToInt(this.latitude);
        int longitude = bigDecimalRoundedToInt(this.longitude);
        return String.format("%03d:%03d", latitude, longitude);
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    private static int bigDecimalRoundedToInt(BigDecimal bd) {
        if (bd.compareTo(new BigDecimal(0)) == -1) {
            return bd.subtract(new BigDecimal("0.5")).intValue();
        }

        return bd.add(new BigDecimal("0.5")).intValue();
    }

}
