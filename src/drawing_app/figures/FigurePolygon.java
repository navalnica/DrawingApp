package drawing_app.figures;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.List;

public class FigurePolygon extends Figure2D {
    protected java.util.List<Point> pointList;

    public FigurePolygon(List<Point> pointList) {
        this.pointList = pointList;
    }

    public void draw(Graphics2D graphics2D) {
        GeneralPath path = getClosedPath(pointList);
        drawShapeWithBorder(graphics2D, path);
    }
}
