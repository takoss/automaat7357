package main;

import java.math.BigDecimal;

/**
 * Created by admin on 28.09.2017.
 */
public class Coordinate {

    private BigDecimal longitude;

    private BigDecimal latitude;

    public Coordinate(BigDecimal longitude, BigDecimal Latitude) {
        this.longitude = longitude;
        this.latitude = Latitude;
    }

    public String getFormattedCoordinates() {
        return "";
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }
}
