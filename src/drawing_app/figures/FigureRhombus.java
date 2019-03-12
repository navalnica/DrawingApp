package drawing_app.figures;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

public class FigureRhombus extends FigureRectangle {
    public FigureRhombus(Point p1, Point p2) {
        super(p1, p2);

    }

    public FigureRhombus(List<Point> pointList) {
        super(pointList);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(topLeftPoint.x, topLeftPoint.y + height / 2));
        pointList.add(new Point(topLeftPoint.x + width / 2, topLeftPoint.y));
        pointList.add(new Point(topLeftPoint.x + width, topLeftPoint.y + height / 2));
        pointList.add(new Point(topLeftPoint.x + width / 2, topLeftPoint.y + height));
        GeneralPath path = getClosedPath(pointList);
        drawShapeWithBorder(graphics2D, path);
    }
}
