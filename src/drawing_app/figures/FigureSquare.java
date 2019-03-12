package drawing_app.figures;

import java.awt.*;
import java.util.List;

import static java.lang.StrictMath.sqrt;

public class FigureSquare extends RegularFigure {

    public FigureSquare(List<Point> pointList) {
        super(pointList);

    }

    public FigureSquare(Point p1, Point p2) {
        super(p1, p2);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        double halfSide = radius / sqrt(2);
        double side = halfSide + halfSide;
        Rectangle.Double rect = new Rectangle.Double(center.x - halfSide, center.y - halfSide, side, side);
        drawShapeWithBorder(graphics2D, rect);
    }
}
