package drawing_app.mouse_adapters;

import drawing_app.DrawControl;
import drawing_app.figures.PolygonalChain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PolygonalChainMouseAdapter extends PolygonMouseAdapter {

    public PolygonalChainMouseAdapter(Graphics2D graphics2D, DrawControl drawControl) {
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
                if (pointsAddedCount > 1) {
                    // TODO:
                    restoreImageFromTempFileAndUpdateGraphics();
                    PolygonalChain chain = new PolygonalChain(pointList);
                    drawControl.onFigureConstructed(chain);
                    pointList.clear();
                    drawControl.saveImageToTempFile();
                } else {
                    System.err.println("To close polygonal chain you need at least 2 selected points");
                }
                break;
        }
    }
}
