package drawing_app.figures;

import java.awt.*;
import java.util.List;

public class FigureRectangle extends TwoPointsInitializable {
    protected Point topLeftPoint;
    protected int width;
    protected int height;

    public FigureRectangle(List<Point> pointList) {
        super(pointList);
    }

    public FigureRectangle(Point p1, Point p2) {
        super(p1, p2);
    }

    protected void initFromTwoPoints(Point p1, Point p2) {
        if(p1 == null || p2 == null){
            throw new NullPointerException("FigureRectangle.initFromTwoPoints: p1 or p2 is null");
        }
        if(p2.x < p1.x){
            Point tmpPoint = p2;
            p2 = p1;
            p1 = tmpPoint;
        }
        if(p1.y > p2.y){
            int tmpY = p2.y;
            p2.y = p1.y;
            p1.y = tmpY;
        }
        topLeftPoint = p1;
        width = p2.x - p1.x;
        height = p2.y - p1.y;
    }

    @Override
    public String toString() {
        return "topLeft: " + topLeftPoint + "; width: " + width + "; height: " + height;
    }

    public void draw(Graphics2D graphics2D) {
        Rectangle rect = new Rectangle(topLeftPoint.x, topLeftPoint.y, width, height);
        drawShapeWithBorder(graphics2D, rect);
    }
}
