import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class LineTest {

    @Test
    public void testValidConstruction() throws Exception {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4, 10);

        Line myLine = new Line(p1, p2);
        assertEquals(1, myLine.getPoint1().getX(), 0);
        assertEquals(2, myLine.getPoint1().getY(), 0);
        assertEquals(4, myLine.getPoint2().getX(), 0);
        assertEquals(10, myLine.getPoint2().getY(), 0);

        myLine = new Line(p1, p1);
        assertEquals(1, myLine.getPoint1().getX(), 0);
        assertEquals(2, myLine.getPoint1().getY(), 0);
        assertEquals(1, myLine.getPoint2().getX(), 0);
        assertEquals(2, myLine.getPoint2().getY(), 0);

        p1 = new Point(1.4,2.5);
        p2 = new Point(4.6, 10.7);
        myLine = new Line(p1, p2);
        assertEquals(1.4, myLine.getPoint1().getX(), 0);
        assertEquals(2.5, myLine.getPoint1().getY(), 0);
        assertEquals(4.6, myLine.getPoint2().getX(), 0);
        assertEquals(10.7, myLine.getPoint2().getY(), 0);

        myLine = new Line(1, 3.33, 4.444, 5.5555);
        assertEquals(1, myLine.getPoint1().getX(), 0);
        assertEquals(3.33, myLine.getPoint1().getY(), 0);
        assertEquals(4.444, myLine.getPoint2().getX(), 0);
        assertEquals(5.5555, myLine.getPoint2().getY(), 0);
    }

    @Test
    public void testInvalidConstruction() throws ShapeException {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4, 10);

        try {
            new Line(p1, null);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }

        try {
            new Line(null, p2);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            // ignore
        }
    }

    @Test
    public void testConstructionViaString() throws ShapeException {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4, 10);

        Line myLine1 = new Line(p1, p2);
        Line myLine2 = new Line(myLine1.toString());

        assertEquals(1,myLine2.getPoint1().getX(),0);
        assertEquals(2,myLine2.getPoint1().getY(),0);
        assertEquals(4,myLine2.getPoint2().getX(),0);
        assertEquals(10,myLine2.getPoint2().getY(),0);
        assertTrue(myLine2.toString().equals(myLine1.toString()));
    }

    @Test
    public void testMove() throws ShapeException {
        Line myLine = new Line(1, 2, 4, 10);

        myLine.move(3, 4);
        assertEquals(4, myLine.getPoint1().getX(), 0);
        assertEquals(6, myLine.getPoint1().getY(), 0);
        assertEquals(7, myLine.getPoint2().getX(), 0);
        assertEquals(14, myLine.getPoint2().getY(), 0);

        myLine.move(.4321, .7654);
        assertEquals(4.4321, myLine.getPoint1().getX(), 0);
        assertEquals(6.7654, myLine.getPoint1().getY(), 0);
        assertEquals(7.4321, myLine.getPoint2().getX(), 0);
        assertEquals(14.7654, myLine.getPoint2().getY(), 0);

        myLine.move(-0.4321, -0.7654);
        assertEquals(4, myLine.getPoint1().getX(), 0);
        assertEquals(6, myLine.getPoint1().getY(), 0);
        assertEquals(7, myLine.getPoint2().getX(), 0);
        assertEquals(14, myLine.getPoint2().getY(), 0);
    }

    @Test
    public void testComputeLength() throws ShapeException {

        Line myLine = new Line(1, 2, 4, 10);
        assertEquals(8.544, myLine.computeLength(), 0.001);

        myLine = new Line(1, 2, 1, 2);
        assertEquals(Math.sqrt(0), myLine.computeLength(), 0);

        myLine = new Line(3, -2, -4, 10);
        assertEquals(13.892, myLine.computeLength(), 0.001);
    }

    @Test
    public void testComputeSlope() throws ShapeException {
        Line myLine = new Line(2, 2, 4, 10);
        assertEquals(0.25, myLine.computeSlope(), 0.1);

        myLine = new Line(2, 2, 4, 10);
        assertEquals(0.25, myLine.computeSlope(), 0.1);

        myLine = new Line(2, 2, 2, 4);
        assertEquals(0, myLine.computeSlope(), 0.1);

        myLine = new Line(2, 2, 4, 2);
        assertEquals(Double.POSITIVE_INFINITY, myLine.computeSlope(), 0.1);

        myLine = new Line(4, 2, 2, 2);
        assertEquals(Double.NEGATIVE_INFINITY, myLine.computeSlope(), 0.1);

        myLine = new Line(2, 2, 2, 2);
        assertEquals(Double.NaN, myLine.computeSlope(), 0.1);
    }

    @Test
    public void testStrongEncapsulation() throws ShapeException {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4, 10);

        Line myLine = new Line(p1, p2);
        assertNotSame(p1, myLine.getPoint1());
        assertNotSame(p2, myLine.getPoint2());

        p1.move(10, 20);
        assertEquals(1, myLine.getPoint1().getX(), 0);
        assertEquals(2, myLine.getPoint1().getY(), 0);
        assertEquals(4, myLine.getPoint2().getX(), 0);
        assertEquals(10, myLine.getPoint2().getY(), 0);

        p1.move(20, 30);
        assertEquals(1, myLine.getPoint1().getX(), 0);
        assertEquals(2, myLine.getPoint1().getY(), 0);
        assertEquals(4, myLine.getPoint2().getX(), 0);
        assertEquals(10, myLine.getPoint2().getY(), 0);

    }

    @Test
    public void testRender() throws Exception {
        BufferedImage bImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bImage.createGraphics();
        graphics.setColor(Color.white);
        Point p1 = new Point(1,2);
        Point p2 = new Point(40, 100);

        Line myLine = new Line(p1, p2);
        myLine.render(graphics);

        assertTrue(ImageIO.write(bImage, "png", new File("output/line.png")));
    }

    @Test
    public void testScale() throws ShapeException {
        Point point1 = new Point(8, 20);
        Point point2 = new Point(39, 2);
        Line myLine = new Line(point1, point2);

        myLine.scale(2.0);

        assertEquals(16, myLine.getPoint1().getX(),0.1);
        assertEquals(40, myLine.getPoint1().getY(),0.1);
        assertEquals(78, myLine.getPoint2().getX(),0.1);
        assertEquals(4, myLine.getPoint2().getY(),0.1);

    }

    @Test
    public void testComputeArea() throws ShapeException {
        Point point1 = new Point(8, 20);
        Point point2 = new Point(39, 2);
        Line myLine = new Line(point1, point2);

        assertEquals(0,myLine.computeArea(),0);
    }
}