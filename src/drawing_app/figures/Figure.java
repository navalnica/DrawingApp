package drawing_app.figures;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.List;

public abstract class Figure {

    protected Color strokeColor = Color.BLACK;
    protected BasicStroke stroke = new BasicStroke(3);

    public abstract void draw(Graphics2D graphics2D);

    public float getStrokeSize() {
        // TODO: test if it returns correct value
        return stroke.getLineWidth();
    }

    public void setStrokeWidth(float width) {
        if (width <= 0) {
            throw new IllegalArgumentException("line width must be > 0");
        }
        stroke = new BasicStroke(width);
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public static GeneralPath getPathFromList(List<Point> pointList) {
        if (pointList.size() < 2) {
            throw new IllegalArgumentException("Figure.getPathFromList: pointList must contain at least 2 points");
        }
        GeneralPath path = new GeneralPath();
        path.moveTo(pointList.get(0).x, pointList.get(0).y);
        for (int i = 1; i < pointList.size(); ++i) {
            path.lineTo(pointList.get(i).x, pointList.get(i).y);
        }
        return path;
    }

}
