package main;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by admin on 28.09.2017.
 */
public class CoordinateTest {

    @Test
    public void testGetCoordinateAddsZerosToTheFrontIfNotThreeChars() {
        Coordinate coord = new Coordinate(new BigDecimal("4"), new
                BigDecimal(0));
        System.out.println(coord.getFormattedCoordinates());
        Assert.assertTrue("004:000".equals(coord.getFormattedCoordinates()));
    }

    @Test
    public void testGetCoordinateRoundsUpFromPositivePointFifty() {
        Coordinate coord = new Coordinate(new BigDecimal("160.50"), new
                BigDecimal("160.50"));
        Assert.assertTrue("161:161".equals(coord.getFormattedCoordinates()));
    }

    @Test
    public void testGetCoordinateRoundsDownFromPositivePointFortyNine() {
        Coordinate coord = new Coordinate(
                new BigDecimal("160.49"), new BigDecimal("160.49"));
        Assert.assertTrue("160:160".equals(coord.getFormattedCoordinates()));
    }

    @Test
    public void testGetCoordinateRoundsDownFromNegativePointFifty() {
        Coordinate coord = new Coordinate(new BigDecimal("-160.50"), new
                BigDecimal("-160.50"));
        Assert.assertTrue("-161:-161".equals(coord.getFormattedCoordinates()));
    }

    @Test
    public void testGetCoordinateRoundsUpFromNegativePointFortyNine() {
        Coordinate coord = new Coordinate(new BigDecimal("-160.49"), new
                BigDecimal("-160.49"));
        Assert.assertTrue("-160:-160".equals(coord.getFormattedCoordinates()));
    }

}
