package drawing_app.figures;

import java.awt.*;
import java.util.List;

public class FigureRay extends FigureSegment {

    protected Dimension drawAreaDimension;

    public FigureRay(List<Point> pointList, Dimension drawAreaDimension) {
        super(pointList);
        if(drawAreaDimension == null){
            throw new NullPointerException("FigureRay: drawAreaDimension is null");
        }
        this.drawAreaDimension = drawAreaDimension;
        assignBorderPointsIfNeeded();
    }

    public FigureRay(Point p1, Point p2, Dimension drawAreaDimension) {
        super(p1, p2);
        if(drawAreaDimension == null){
            throw new NullPointerException("FigureRay: drawAreaDimension is null");
        }
        this.drawAreaDimension = drawAreaDimension;
        assignBorderPointsIfNeeded();
    }

    protected void assignBorderPointsIfNeeded(){
        Point borderPoint = FigureRay.getRayIntersectionWithBorder(p1, p2, drawAreaDimension);
        p2 = borderPoint;
    }

    public static Point getRayIntersectionWithBorder(Point start, Point end, Dimension drawAreaDimension) {
        double angle = StrictMath.atan2(end.y - start.y, end.x - start.x);
        int maxDimension = Integer.max(drawAreaDimension.width, drawAreaDimension.height);
        double newX = start.x + maxDimension * StrictMath.cos(angle);
        double newY = start.y + maxDimension * StrictMath.sin(angle);
        return new Point((int)newX, (int) newY);
    }
}
