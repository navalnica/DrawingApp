package drawing_app.figures;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.List;

public abstract class Figure2D extends Figure {

    protected Color fillColor = Color.WHITE;

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    protected void drawShapeWithBorder(Graphics2D graphics2D, Shape shape) {
        graphics2D.setStroke(stroke);
        graphics2D.setColor(strokeColor);
        graphics2D.draw(shape);
        graphics2D.setColor(fillColor);
        graphics2D.fill(shape);
    }

    public static GeneralPath getClosedPath(List<Point> pointList) {
        if (pointList.size() < 3) {
            throw new IllegalArgumentException("Figure2D.getClosedPath: pointList must contain at least 3 points");
        }
        GeneralPath path = getPathFromList(pointList);
        path.closePath();
        return path;
    }
}
