package drawing_app.mouse_adapters;

import drawing_app.DrawControl;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseMouseAdapter extends MouseAdapter {

    protected DrawControl drawControl;
    protected Graphics2D graphics2D;
    protected Color fillColor;

    protected Color dotColor = Color.RED;
    protected final int DOT_SIZE = 14;

    protected int pointsAddedCount = 0;
    protected int NEEDED_POINTS_CNT = Integer.MAX_VALUE;
    protected List<Point> pointList = new ArrayList<>();

    @Override
    public abstract void mouseClicked(MouseEvent e);

    public BaseMouseAdapter(Graphics2D graphics2D, DrawControl drawControl) {
        if(graphics2D == null || drawControl == null){
            throw new NullPointerException("BaseMouseAdapter: passed null to constructor");
        }
        this.graphics2D = graphics2D;
        this.drawControl = drawControl;
    }

    protected void addAndDrawNewPoint(MouseEvent e) {
        if (pointsAddedCount == 0) {
            drawControl.saveImageToTempFile();
        }

        Point p = new Point(e.getX(), e.getY());
        pointsAddedCount++;
        pointList.add(p);
        System.out.println("added new point: " + p);

        if (pointsAddedCount == NEEDED_POINTS_CNT) {
            restoreImageFromTempFileAndUpdateGraphics();
        }
        else{
            // draw helper points to show click positions
            graphics2D.setColor(dotColor);
            graphics2D.fillOval(p.x - DOT_SIZE / 2, p.y - DOT_SIZE / 2, DOT_SIZE, DOT_SIZE);
        }
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    protected void restoreImageFromTempFileAndUpdateGraphics(){
        graphics2D = drawControl.restoreImageFromTempFile();
    }

}