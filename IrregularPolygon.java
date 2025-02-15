import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList

import gpdraw.*; // for DrawingTool

public class IrregularPolygon {
    private ArrayList<Point2D.Double> pointsList = new ArrayList<Point2D.Double>();

    // constructor
    public IrregularPolygon() {}

    // public methods
    public void add(Point2D.Double newVertex) {
        // TODO: Add a point to the IrregularPolygon.
        pointsList.add(newVertex);
    }

    public double perimeter() {
        // TODO: Calculate the perimeter.
        if (pointsList.size() < 2) {
            return 0.0;
        }

        double totalPerimeter = 0.0;
        int numPoints = pointsList.size();

        for (int i = 0; i < numPoints; i++) {
            Point2D.Double current = pointsList.get(i);
            Point2D.Double next = pointsList.get((i + 1) % numPoints); // Wrap around
            totalPerimeter += current.distance(next);
        }

        return totalPerimeter;
    }

    public double area() {
        // TODO: Calculate the area.
        if (pointsList.size() < 3) {
            return 0.0;
        }

        double areaSum = 0.0;
        int totalVertices = pointsList.size();

        for (int i = 0; i < totalVertices; i++) {
            Point2D.Double first = pointsList.get(i);
            Point2D.Double second = pointsList.get((i + 1) % totalVertices); // Wrap around
            areaSum += (first.getX() * second.getY()) - (second.getX() * first.getY());
        }

        return Math.abs(areaSum) / 2.0;
    }

    public void draw() {
        // Wrap the DrawingTool in a try/catch to allow development without need for graphics.
        try {
            if (pointsList.size() < 2) {
                return;
            }

            // TODO: Draw the polygon.
            DrawingTool penTool = new DrawingTool(new SketchPad(500, 500));
            penTool.up();
            Point2D.Double startPoint = pointsList.get(0);
            penTool.move(startPoint.getX(), startPoint.getY());
            penTool.down();

            for (Point2D.Double vertex : pointsList) {
                penTool.move(vertex.getX(), vertex.getY());
            }

            // Close the polygon
            penTool.move(startPoint.getX(), startPoint.getY());

        } catch (java.awt.HeadlessException ex) {
            System.out.println("Exception: No graphics support available.");
        }
    }
}
