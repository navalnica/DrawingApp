package drawing_app.figures;

import java.awt.*;
import java.util.List;

public abstract class TwoPointsInitializable extends Figure2D {

    protected abstract void initFromTwoPoints(Point p1, Point p2);

    public TwoPointsInitializable(List<Point> pointList) {
        if (pointList.size() != 2) {
            throw new IllegalArgumentException(
                    "TwoPointsInitializable: pointList must contain exactly 2 points. its size: " + pointList.size());
        }
        initFromTwoPoints(pointList.get(0), pointList.get(1));
    }

    public TwoPointsInitializable(Point p1, Point p2) {
        initFromTwoPoints(p1, p2);
    }
}
