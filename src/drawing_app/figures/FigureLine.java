package drawing_app.figures;

import java.awt.*;
import java.util.List;

public class FigureLine extends FigureRay {

    public FigureLine(List<Point> pointList, Dimension drawAreaDimension) {
        super(pointList, drawAreaDimension);
    }

    public FigureLine(Point p1, Point p2, Dimension drawAreaDimension) {
        super(p1, p2, drawAreaDimension);
    }

    @Override
    protected void assignBorderPointsIfNeeded() {
        Point borderPoint1 = getRayIntersectionWithBorder(p1, p2, drawAreaDimension);
        Point borderPoint2 = getRayIntersectionWithBorder(p2, p1, drawAreaDimension);
        p1 = borderPoint1;
        p2 = borderPoint2;
    }
}
