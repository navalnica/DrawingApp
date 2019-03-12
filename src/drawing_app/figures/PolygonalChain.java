package drawing_app.figures;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.List;

public class PolygonalChain extends Figure1D {

    protected List<Point> pointList;

    public PolygonalChain(List<Point> pointList) {
        if(pointList == null){
            throw new NullPointerException("PolygonalChain: pointList is null");
        }
        this.pointList = pointList;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(strokeColor);
        graphics2D.setStroke(stroke);
        GeneralPath path = getPathFromList(pointList);
        graphics2D.draw(path);
    }
}
