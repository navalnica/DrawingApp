package drawing_app.figures;

import java.awt.*;
import java.util.List;

public class FigureTriangle extends FigurePolygon {

    public FigureTriangle(List<Point> pointList) {
        super(pointList);

        if (pointList.size() != 3) {
            throw new IllegalArgumentException("pointList size: " + pointList.size() + ". needed: 3");
        }
    }
}
