import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CompositeShapeTest {

    @Test
    public void testAddRemoveShape() throws Exception {
        Point point1 = new Point(-1.5, 0);
        Point point2 = new Point(1.5, 0);
        Point point3 = new Point(1.5, -2);
        Point point4 = new Point(-1.5, -2);
        Point point5 = new Point(40, 60);

        Rectangle rectangle= new Rectangle(point1, point2, point3, point4);
        Triangle triangle = new Triangle(point1, point2, point3);
        Line line = new Line(point1, point5);
        Circle circle = new Circle(100, 100, 50);

        CompositeShape compositeShape = new CompositeShape();
        assertEquals(compositeShape.getItemCount(), 0);

        compositeShape.addShape(rectangle);
        assertEquals(compositeShape.getItemCount(), 1);

        compositeShape.addShape(triangle);
        assertEquals(compositeShape.getItemCount(), 2);

        compositeShape.addShape(circle);
        assertEquals(compositeShape.getItemCount(), 3);

        compositeShape.addShape(line);
        assertEquals(compositeShape.getItemCount(), 4);

        compositeShape.addShape(point1);
        assertEquals(compositeShape.getItemCount(), 5);

        compositeShape.removeShape(point1);
        assertEquals(compositeShape.getItemCount(), 4);

        compositeShape.removeShape(circle);
        assertEquals(compositeShape.getItemCount(), 3);

        compositeShape.removeShape(line);
        assertEquals(compositeShape.getItemCount(), 2);

        compositeShape.removeShape(triangle);
        assertEquals(compositeShape.getItemCount(), 1);

        compositeShape.removeShape(rectangle);
        assertEquals(compositeShape.getItemCount(), 0);

    }

    @Test
    public void testCompositeInsideComposite() throws Exception{
        Point point1 = new Point(-1.5, 0);
        Point point2 = new Point(1.5, 0);
        Point point3 = new Point(1.5, -2);
        Point point4 = new Point(-1.5, -2);
        Point point5 = new Point(40, 60);

        Rectangle rectangle= new Rectangle(point1, point2, point3, point4);
        Triangle triangle = new Triangle(point1, point2, point3);
        Line line = new Line(point1, point5);
        Circle circle = new Circle(100, 100, 50);
        CompositeShape compositeShape = new CompositeShape();
        CompositeShape compositeShape2 = new CompositeShape();

        compositeShape.addShape(circle);
        compositeShape.addShape(rectangle);
        compositeShape.addShape(point1);

        compositeShape2.addShape(triangle);
        compositeShape2.addShape(line);

        compositeShape.addShape(compositeShape2);
        assertEquals(compositeShape.getItemCount(),4,0);
    }

    @Test
    public void testMove() throws Exception {
        Point point1 = new Point(-1.5, 0);
        Point point2 = new Point(1.5, 0);
        Point point3 = new Point(1.5, -2);
        Point point4 = new Point(-1.5, -2);
        Point point5 = new Point(40, 60);

        Rectangle rectangle= new Rectangle(point1, point2, point3, point4);
        Triangle triangle = new Triangle(point1, point2, point3);
        Line line = new Line(point1, point5);
        Circle circle = new Circle(100, 100, 50);

        CompositeShape compositeShape = new CompositeShape();
        compositeShape.addShape(triangle);
        compositeShape.addShape(rectangle);
        compositeShape.addShape(line);
        compositeShape.addShape(circle);

        int deltaX = 10;
        int deltaY = 10;
        compositeShape.move(deltaX, deltaY);

        rectangle.move(deltaX, deltaY);
        triangle.move(deltaX, deltaY);
        line.move(deltaX, deltaY);
        circle.move(deltaX, deltaY);

        CompositeShape deltaCompositeShape = new CompositeShape();
        deltaCompositeShape.addShape(triangle);
        deltaCompositeShape.addShape(rectangle);
        deltaCompositeShape.addShape(line);
        deltaCompositeShape.addShape(circle);

        assertEquals(deltaCompositeShape.getItemCount(), compositeShape.getItemCount());
        assertEquals(deltaCompositeShape.computeArea(), compositeShape.computeArea(),0.1);
        assertTrue(deltaCompositeShape.toString().equals(compositeShape.toString()));
    }

    @Test
    public void testScale() throws Exception {
        Point point1 = new Point(-1.5, 0);
        Point point2 = new Point(1.5, 0);
        Point point3 = new Point(1.5, -2);
        Point point4 = new Point(-1.5, -2);
        Point point5 = new Point(40, 60);

        Rectangle rectangle= new Rectangle(point1, point2, point3, point4);
        Triangle triangle = new Triangle(point1, point2, point3);
        Line line = new Line(point1, point5);
        Circle circle = new Circle(100, 100, 50);

        CompositeShape compositeShape = new CompositeShape();
        compositeShape.addShape(triangle);
        compositeShape.addShape(rectangle);
        compositeShape.addShape(line);
        compositeShape.addShape(circle);
        compositeShape.scale(2.0);

        assertEquals( 80,line.getPoint2().getX(),0.1);
    }

    @Test
    public void testComputeArea() throws Exception {
        Point point1 = new Point(-1.5, 0);
        Point point2 = new Point(1.5, 0);
        Point point3 = new Point(1.5, -2);
        Point point4 = new Point(-1.5, -2);
        Point point5 = new Point(40, 60);

        Rectangle rectangle= new Rectangle(point1, point2, point3, point4);
        Triangle triangle = new Triangle(point1, point2, point3);
        Line line = new Line(point1, point5);
        Circle circle = new Circle(100, 100, 50);

        CompositeShape compositeShape = new CompositeShape();
        compositeShape.addShape(triangle);
        compositeShape.addShape(rectangle);
        compositeShape.addShape(line);
        compositeShape.addShape(circle);

        double compositeArea = compositeShape.computeArea();
        double totalShapeArea = rectangle.computeArea() + triangle.computeArea() + line.computeArea() + circle.computeArea();
        assertEquals(compositeArea, totalShapeArea, 0.1);
    }

    @Test
    public void testRender() throws Exception {
        Point point1 = new Point(-1.5, 0);
        Point point2 = new Point(1.5, 0);
        Point point3 = new Point(1.5, -2);
        Point point4 = new Point(-1.5, -2);
        Point point5 = new Point(40, 60);

        Rectangle rectangle= new Rectangle(point1, point2, point3, point4);
        Triangle triangle = new Triangle(point1, point2, point3);
        Line line = new Line(point1, point5);
        Circle circle = new Circle(100, 100, 50);

        String filename = "phoenix.png";
        EmbeddedImage myImage = new EmbeddedImage(filename, 100, 100, 300, 150);

        CompositeShape compositeShape = new CompositeShape();
        compositeShape.addShape(myImage);
        compositeShape.addShape(triangle);
        compositeShape.addShape(rectangle);
        compositeShape.addShape(line);
        compositeShape.addShape(circle);

        BufferedImage bImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = bImage.createGraphics();
        graphics.setColor(Color.white);

        compositeShape.render(graphics);

        assertTrue(ImageIO.write(bImage, "png", new File("output/compositeImage.png")));
    }

    @Test
    public void toStringTest() throws Exception{
        Point point1 = new Point(-1.5, 0);
        Point point2 = new Point(1.5, 0);
        Point point3 = new Point(1.5, -2);
        Point point4 = new Point(-1.5, -2);
        Point point5 = new Point(40, 60);

        Rectangle rectangle= new Rectangle(point1, point2, point3, point4);
        Triangle triangle = new Triangle(point1, point2, point3);

        CompositeShape compositeShape1 = new CompositeShape();
        CompositeShape compositeShape2 = new CompositeShape();

        compositeShape1.addShape(triangle);
        compositeShape1.addShape(rectangle);
        compositeShape2.addShape(triangle);
        compositeShape2.addShape(rectangle);

        assertTrue(compositeShape1.toString().equals(compositeShape2.toString()));
    }
}