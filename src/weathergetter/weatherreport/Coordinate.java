package weathergetter.weatherreport;

import java.math.BigDecimal;

/**
 * Created by admin on 28.09.2017.
 */
public class Coordinate {

    private BigDecimal longitude, latitude;

    Coordinate(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    String getFormattedCoordinates() {
        int latitude = roundBigDecimaltoInt(this.latitude);
        int longitude = roundBigDecimaltoInt(this.longitude);
        return String.format("%03d:%03d", latitude, longitude);
    }

    BigDecimal getLongitude() {
        return longitude;
    }

    BigDecimal getLatitude() {
        return latitude;
    }

    private static int roundBigDecimaltoInt(BigDecimal bd) {
        if (bd.compareTo(new BigDecimal(0)) < 0) {
            return bd.subtract(new BigDecimal("0.5")).intValue();
        }

        return bd.add(new BigDecimal("0.5")).intValue();
    }

}
