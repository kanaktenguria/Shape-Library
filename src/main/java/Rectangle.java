/**
 *
 *  Rectangle
 *
 *  This class represents rectangle objects that can be moved.  Users of a rectangle can also get its area.
 *
 */
@SuppressWarnings("WeakerAccess")
public class Rectangle {
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;

    /**
     * Constructor based on x-y Locations
     * @param x1                The x-location of first point -- must be a valid double.
     * @param y1                The y-location of first point -- must be a valid double.
     * @param x2                The x-location of second point -- must be a valid double.
     * @param y2                The y-location of second point -- must be a valid double.
     * @param x3                The x-location of third point -- must be a valid double.
     * @param y3                The y-location of third point -- must be a valid double.
     * @param x4                The x-location of fourth point -- must be a valid double.
     * @param y4                The y-location of fourth point -- must be a valid double.
     * @throws ShapeException   Exception throw if any parameter is invalid
     */
    public Rectangle(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) throws ShapeException {
        point1 = new Point(x1, y1);
        point2 = new Point(x2, y2);
        point3 = new Point(x3, y3);
        point4 = new Point(x4, y4);
    }

    /**
     *
     * @param point1            The first point -- must not be null
     * @param point2            The second point -- must not be null
     * @param point3            The third point -- must not be null
     * @param point4            The fourth point -- must not be null
     * @throws ShapeException   Exception throw if any parameter is invalid
     */
    public Rectangle(Point point1, Point point2, Point point3, Point point4) throws ShapeException {
        if (point1==null || point2==null || point3==null || point4==null)
            throw new ShapeException("Invalid Point");

        this.point1 = point1.copy();
        this.point2 = point2.copy();
        this.point3 = point3.copy();
        this.point4 = point4.copy();
    }

    /**
     * @return  The first point
     */
    public Point getPoint1() throws ShapeException { return point1.copy(); }

    /**
     * @return  The second point
     */
    public Point getPoint2() throws ShapeException { return point2.copy(); }

    /**
     * @return  The third point
     */
    public Point getPoint3() throws ShapeException { return point3.copy(); }

    /**
     * @return  The fourth point
     */
    public Point getPoint4() throws ShapeException { return point4.copy(); }

    /**
     * Move a rectangle
     *
     * @param deltaX            The delta x-location by which the rectangle should be moved -- must be a valid double
     * @param deltaY            The delta y-location by which the rectangle should be moved -- must be a valid double
     * @throws ShapeException   Exception throw if any parameter is invalid
     */
    public void move(double deltaX, double deltaY) throws ShapeException {
        point1.move(deltaX, deltaY);
        point2.move(deltaX, deltaY);
        point3.move(deltaX, deltaY);
        point4.move(deltaX, deltaY);
    }

    /**
     * Scale the rectangle
     *
     * @param scaleFactor       a non-negative double that represents the percentage to scale the rectangle.
     *                          0>= and <1 to shrink.
     *                          >1 to grow.
     * @throws ShapeException   Exception thrown if the scale factor is not valid
     */
    public void scale(double scaleFactor) throws ShapeException {
        Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");
        radius *= scaleFactor;
    }

    /**
     * @return  The area of the circle.
     */
    public double computeArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}