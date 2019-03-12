package drawing_app.figures;

import java.awt.*;
import java.util.List;

public class FigureSegment extends Figure1D {

    protected Point p1;
    protected Point p2;

    public FigureSegment(List<Point> pointList) {
        if (pointList.size() != 2) {
            throw new IllegalArgumentException(
                    "FigureSegment: pointList must contain exactly 2 points. its size: " + pointList.size());
        }
        this.p1 = pointList.get(0);
        this.p2 = pointList.get(1);
        if (p1 == null || p2 == null) {
            throw new NullPointerException("FigureSegment: pointList contains null values");
        }
    }

    public FigureSegment(Point p1, Point p2) {
        if (p1 == null || p2 == null) {
            throw new NullPointerException("FigureSegment: p1 or p2 is null");
        }
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(strokeColor);
        graphics2D.setStroke(stroke);
        graphics2D.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
}
