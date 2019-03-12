package drawing_app.figures;

import java.awt.*;
import java.util.List;

public abstract class RegularFigure extends TwoPointsInitializable {

    protected Point center;
    protected double radius;

    public RegularFigure(List<Point> pointList) {
        super(pointList);
    }

    public RegularFigure(Point p1, Point p2) {
        super(p1, p2);
    }

    protected void initFromTwoPoints(Point center, Point borderPoint) {
        if (center == null || borderPoint == null) {
            throw new NullPointerException("FigureCircle: center of borderPoint is null");
        }
        this.center = center;
        radius = Point.distance(center.x, center.y, borderPoint.x, borderPoint.y);
    }
}
