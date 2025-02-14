import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList

import gpdraw.*; // for DrawingTool


public class IrregularPolygon {
    private ArrayList<Point2D.Double> myPolygon = new ArrayList<Point2D.Double>();

    // constructor
    public IrregularPolygon() {}

    // public methods
    public void add(Point2D.Double aPoint)
    {
        // TODO: Add a point to the IrregularPolygon.
        myPolygon.add(aPoint);
    }

    public double perimeter() {
        // TODO: Calculate the perimeter.
        if (myPolygon.size() < 2) {
            return 0.0;
        }

        double perimeter = 0.0;
        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double current = myPolygon.get(i);
            Point2D.Double next = myPolygon.get((i + 1) % myPolygon.size()); // Wrap around
            perimeter += current.distance(next);
        }
        
        return perimeter;
    }

    public double area() {
        // TODO: Calculate the area.
        if (myPolygon.size() < 3) {
            return 0.0;
        }

        double sum = 0.0;
        int n = myPolygon.size();

        for (int i = 0; i < n; i++) {
            Point2D.Double current = myPolygon.get(i);
            Point2D.Double next = myPolygon.get((i + 1) % n); // Wrap around
            sum += (current.getX() * next.getY()) - (next.getX() * current.getY());
        }

        return Math.abs(sum) / 2.0;

    }

    public void draw()
    {
        // Wrap the DrawingTool in a try/catch to allow development without need for graphics.
        try {
            if (myPolygon.size() < 2) {
                return;
            }

            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));
            pen.up();
            Point2D.Double first = myPolygon.get(0);
            pen.move(first.getX(), first.getY());
            pen.down();

            for (Point2D.Double point : myPolygon) {
                pen.move(point.getX(), point.getY());
            }

            // Close the polygon
            pen.move(first.getX(), first.getY());

        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }

    
}
