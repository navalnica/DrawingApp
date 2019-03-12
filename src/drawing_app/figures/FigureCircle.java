package drawing_app.figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;

public class FigureCircle extends RegularFigure {

    public FigureCircle(List<Point> pointList) {
        super(pointList);
    }

    public FigureCircle(Point p1, Point p2) {
        super(p1, p2);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        double diameter = radius + radius;
        Ellipse2D ellipse2D = new Ellipse2D.Double(
                center.x - radius, center.y - radius, diameter, diameter);
        drawShapeWithBorder(graphics2D, ellipse2D);
    }
}
