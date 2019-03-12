package drawing_app.mouse_adapters;

import drawing_app.DrawControl;
import drawing_app.figures.FigureRay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.util.List;

public class RayMouseAdapter extends BaseMouseAdapter {

    protected Dimension drawAreaDimension;
    protected Class<? extends FigureRay> classValue;

    public RayMouseAdapter(
            Graphics2D graphics2D,
            DrawControl drawControl,
            Dimension drawAreaDimension,
            Class<? extends FigureRay> classValue) {

        super(graphics2D, drawControl);
        if (drawAreaDimension == null || classValue == null) {
            throw new NullPointerException("RayMouseAdapter: passed null to constructor");
        }
        this.drawAreaDimension = drawAreaDimension;
        this.classValue = classValue;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JComponent component = (JComponent) e.getSource();

        if (e.getButton() == MouseEvent.BUTTON1) {
            if (pointsAddedCount == 0) {
                addAndDrawNewPoint(e);
            } else {
                addAndDrawNewPoint(e);
                restoreImageFromTempFileAndUpdateGraphics();

                try {
                    Constructor<? extends FigureRay> ctor = classValue.getConstructor(List.class, Dimension.class);
                    FigureRay segment = ctor.newInstance(pointList, drawAreaDimension);
                    drawControl.onFigureConstructed(segment);
                } catch (Exception ex) {
                    String msg = "RayMouseAdapter.mouseClicked(): " +
                            "error occurred during class constructor invocation";
                    System.err.println(msg);
                    System.err.println(ex.getMessage());
                    System.exit(1);
                }

                pointsAddedCount = 0;
                pointList.clear();
                drawControl.saveImageToTempFile();
            }

            component.repaint();
        }
    }
}
