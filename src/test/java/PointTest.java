import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void testValidConstruction() throws Exception {
        Point p1 = new Point(1,2);
        assertEquals(1, p1.getX(), 0);
        assertEquals(2, p1.getY(), 0);

        p1 = new Point(1.111,2.222);
        assertEquals(1.111, p1.getX(), 0);
        assertEquals(2.222, p1.getY(), 0);
    }

    @Test
    public void testInvalidConstruction() {

        try {
            new Point(1,Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            new Point(1,Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            new Point(1,Double.NaN);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            new Point(Double.POSITIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            new Point(Double.NEGATIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            new Point(Double.NaN, 1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

    }

    @Test
    public void testConstructionViaString() throws ShapeException {
        Point p1 = new Point(-97.25, 143.21);
        Point p2 = new Point(p1.toString());
        assertTrue(p2.toString().equals(p1.toString()));
    }

    @Test
    public void testScale() throws ShapeException {
        Point p1 = new Point(1,2);
        p1.scale(2.0);
        assertEquals(1, p1.getX(),0);
    }
    @Test
    public void testMoveX() throws Exception {
        Point p1 = new Point(1,2);

        p1.moveX(10);
        assertEquals(11, p1.getX(), 0);
        assertEquals(2, p1.getY(), 0);

        p1.moveX(0.1234);
        assertEquals(11.1234, p1.getX(), 0);
        assertEquals(2, p1.getY(), 0);

        p1.moveX(-20);
        assertEquals(-8.8766, p1.getX(), 0);
        assertEquals(2, p1.getY(), 0);

        p1.moveX(0);
        assertEquals(-8.8766, p1.getX(), 0);
        assertEquals(2, p1.getY(), 0);

        try {
            p1.moveX(Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            p1.moveX(Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            p1.moveX(Double.NaN);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

    }

    @Test
    public void testMoveY() throws Exception {
        Point p1 = new Point(1,2);

        p1.moveY(10);
        assertEquals(1, p1.getX(), 0);
        assertEquals(12, p1.getY(), 0);

        p1.moveY(0.1234);
        assertEquals(1, p1.getX(), 0);
        assertEquals(12.1234, p1.getY(), 0);

        p1.moveY(-20);
        assertEquals(1, p1.getX(), 0);
        assertEquals(-7.8766, p1.getY(), 0);

        p1.moveY(0);
        assertEquals(1, p1.getX(), 0);
        assertEquals(-7.8766, p1.getY(), 0);

        try {
            p1.moveY(Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            p1.moveY(Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            p1.moveY(Double.NaN);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

    }

    @Test
    public void testMove() throws Exception {
        Point p1 = new Point(1,2);

        p1.move(10, 20);
        assertEquals(11, p1.getX(), 0);
        assertEquals(22, p1.getY(), 0);

        p1.move(0.222, 0.333);
        assertEquals(11.222, p1.getX(), 0);
        assertEquals(22.333, p1.getY(), 0);

        p1.move(-0.222, -0.333);
        assertEquals(11, p1.getX(), 0);
        assertEquals(22, p1.getY(), 0);

        p1.move(-20, -30);
        assertEquals(-9, p1.getX(), 0);
        assertEquals(-8, p1.getY(), 0);

        try {
            p1.move(1, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            p1.move(1, Double.NEGATIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            p1.move(1, Double.NaN);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            p1.move(Double.POSITIVE_INFINITY, 1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            p1.move(Double.NEGATIVE_INFINITY,1 );
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            p1.move(Double.NaN,1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

    }

    @Test
    public void testClone() throws Exception {
        Point p1 = new Point(-123.45,-23.45);
        assertEquals(-123.45, p1.getX(), 0);
        assertEquals(-23.45, p1.getY(), 0);

        Point p2 = p1.copy();
        assertNotSame(p1, p2);
        assertEquals(p1.getX(), p2.getX(), 0);
        assertEquals(p1.getY(), p2.getY(), 0);
    }

    @Test
    public void testComputeArea() throws Exception {
        Point p1 = new Point(-123.45,-23.45);
        assertEquals(0, p1.computeArea(),0);
    }

    @Test
    public void testRender() throws Exception{
        BufferedImage bImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bImage.createGraphics();
        graphics.setColor(Color.white);
        Point p1 = new Point(40, 100);

        p1.render(graphics);

        assertTrue(ImageIO.write(bImage, "png", new File("output/point.png")));
    }
}