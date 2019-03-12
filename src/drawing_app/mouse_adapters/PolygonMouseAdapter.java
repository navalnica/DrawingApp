package drawing_app.mouse_adapters;

import drawing_app.DrawControl;
import drawing_app.figures.FigurePolygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PolygonMouseAdapter extends BaseMouseAdapter {


    public PolygonMouseAdapter(Graphics2D graphics2D, DrawControl drawControl) {
        super(graphics2D, drawControl);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JComponent component = (JComponent) e.getSource();

        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                addAndDrawNewPoint(e);
                component.repaint();
                break;

            // right button
            case MouseEvent.BUTTON3:
                // close polygon
                if (pointsAddedCount > 2) {
                    // component.repaint() is redundant
                    // because we update DrawComponent's image
                    // TODO: why the polygon is shown? graphics is updated before drawing
                    restoreImageFromTempFileAndUpdateGraphics();

                    FigurePolygon polygon = new FigurePolygon(pointList);
                    drawControl.onFigureConstructed(polygon);

                    pointList.clear();
                    drawControl.saveImageToTempFile();
                } else {
                    System.err.println("To close polygon you need at least 3 selected points");
                }
                break;
        }
    }
}
