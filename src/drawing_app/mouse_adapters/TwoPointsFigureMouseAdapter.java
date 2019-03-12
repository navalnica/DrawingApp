package drawing_app.mouse_adapters;

import drawing_app.DrawControl;
import drawing_app.figures.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.util.List;

public class TwoPointsFigureMouseAdapter extends BaseMouseAdapter {

    protected Class<? extends Figure> classValue;

    public TwoPointsFigureMouseAdapter(
            Graphics2D graphics2D,
            DrawControl drawControl,
            Class<? extends Figure> classValue) {

        super(graphics2D, drawControl);
        if(classValue == null){
            throw new NullPointerException("TwoPointsFigureMouseAdapter: passed null to constructor");
        }
        this.classValue = classValue;
        NEEDED_POINTS_CNT = 2;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            if (pointsAddedCount < NEEDED_POINTS_CNT) {
                addAndDrawNewPoint(e);
                if (pointsAddedCount == NEEDED_POINTS_CNT) {
                    try {
                        Constructor<? extends Figure> ctor = classValue.getConstructor(List.class);
                        Figure f = ctor.newInstance(pointList);
                        drawControl.onFigureConstructed(f);
                    } catch (Exception ex) {
                        String msg = "TwoPointsFigureMouseAdapter.mouseClicked(): " +
                                "error occurred during class constructor invocation";
                        System.err.println(msg);
                        System.err.println(ex.getMessage());
                        System.exit(1);
                    }
                    pointsAddedCount = 0;
                    pointList.clear();
                }
            }
            // inform parent component
            JComponent component = (JComponent) e.getSource();
            component.repaint();
        }
    }
}
