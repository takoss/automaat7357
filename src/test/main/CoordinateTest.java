package test.main;

import main.Coordinate;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * Created by admin on 28.09.2017.
 */
public class CoordinateTest {

    @Test
    void testGetCoordinateAddsZerosToTheFrontIfNotThreeChars() {
        Coordinate coord = new Coordinate(new BigDecimal("4"), new
                BigDecimal(0));
        Assert.assertTrue("004:000".equals(coord.getFormattedCoordinates()));
    }

    @Test
    void testGetCoordinateRoundsToNearestIntegers() {
        Coordinate coord = new Coordinate(new BigDecimal("365.49"), new BigDecimal("365.50"));
        Assert.assertTrue("365:366".equals(coord.getFormattedCoordinates()));
    }

}
