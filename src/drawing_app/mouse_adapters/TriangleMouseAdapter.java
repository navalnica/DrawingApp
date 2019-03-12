package drawing_app.mouse_adapters;

import drawing_app.DrawControl;
import drawing_app.figures.FigureTriangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TriangleMouseAdapter extends BaseMouseAdapter {

    public TriangleMouseAdapter(Graphics2D graphics2D, DrawControl drawControl) {
        super(graphics2D, drawControl);
        NEEDED_POINTS_CNT = 3;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (pointsAddedCount < NEEDED_POINTS_CNT) {
                addAndDrawNewPoint(e);
                if (pointsAddedCount == NEEDED_POINTS_CNT) {
                    FigureTriangle triangle = new FigureTriangle(pointList);
                    drawControl.onFigureConstructed(triangle);
                    pointsAddedCount = 0;
                    pointList.clear();
                }
                JComponent component = (JComponent) e.getSource();
                component.repaint();
            }
        }
    }
}
