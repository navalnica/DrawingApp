package drawing_app.figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;

public class FigureEllipse extends FigureRectangle {

    public FigureEllipse(List<Point> pointList) {
        super(pointList);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Ellipse2D ellipse2D = new Ellipse2D.Double(topLeftPoint.x, topLeftPoint.y, width, height);
        drawShapeWithBorder(graphics2D, ellipse2D);
    }
}
