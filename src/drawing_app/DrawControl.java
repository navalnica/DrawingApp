package drawing_app;

import drawing_app.figures.*;
import drawing_app.mouse_adapters.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.RenderedImage;
import java.io.File;

public class DrawControl extends JComponent {

    private final File tmpFile;
    private final String tmpFilePath = "tmp/tmp_draw_control_image";

    private int width;
    private int height;
    private Dimension drawAreaDimension;

    private Image offscreenImage;
    private Graphics2D offscreenGraphics2D;

    private Color backgroundColor = Color.WHITE;
    private Color strokeColor = Color.BLACK;
    private Color fillColor = Color.WHITE;

    private MouseAdapter currentMouseListener;

    public enum FigureMode {
        CIRCLE, ELLIPSE, POLYGON, RECTANGLE, RHOMBUS, SQUARE, TRIANGLE,
        SEGMENT, RAY, LINE, POLYGONAL_CHAIN
    }

    DrawControl() {
        // we provide custom double-buffering with the use of offscreenGraphics
        this.setDoubleBuffered(false);

        tmpFile = new File(tmpFilePath);
        File parent = tmpFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("BaseMouseAdapter: couldn't create dir: " + parent);
        }
    }

    public void setFigureMode(FigureMode mode) {
        if (currentMouseListener != null) {
            removeMouseListener(currentMouseListener);
        }

        switch (mode) {
            case RECTANGLE:
                currentMouseListener = new TwoPointsFigureMouseAdapter(
                        offscreenGraphics2D, this, FigureRectangle.class);
                break;
            case RHOMBUS:
                currentMouseListener = new TwoPointsFigureMouseAdapter(
                        offscreenGraphics2D, this, FigureRhombus.class);
                break;
            case ELLIPSE:
                currentMouseListener = new TwoPointsFigureMouseAdapter(
                        offscreenGraphics2D, this, FigureEllipse.class);
                break;
            case CIRCLE:
                currentMouseListener = new TwoPointsFigureMouseAdapter(
                        offscreenGraphics2D, this, FigureCircle.class);
                break;
            case SQUARE:
                currentMouseListener = new TwoPointsFigureMouseAdapter(
                        offscreenGraphics2D, this, FigureSquare.class);
                break;
            case POLYGON:
                currentMouseListener = new PolygonMouseAdapter(offscreenGraphics2D, this);
                break;
            case TRIANGLE:
                currentMouseListener = new TriangleMouseAdapter(offscreenGraphics2D, this);
                break;

            case SEGMENT:
                currentMouseListener = new TwoPointsFigureMouseAdapter(
                        offscreenGraphics2D, this, FigureSegment.class);
                break;

            case RAY:
                currentMouseListener = new RayMouseAdapter(offscreenGraphics2D, this,
                        drawAreaDimension, FigureRay.class);
                break;
            case LINE:
                currentMouseListener = new RayMouseAdapter(offscreenGraphics2D, this,
                        drawAreaDimension, FigureLine.class);
                break;
            case POLYGONAL_CHAIN:
                currentMouseListener = new PolygonalChainMouseAdapter(offscreenGraphics2D, this);
                break;

            default:
                System.out.println("DrawControl.setFigureMode(): Trying to set not implemented mode");
                return;
        }
        addMouseListener(currentMouseListener);
    }

    @Override
    public Dimension getPreferredSize() {
        width = this.getParent().getWidth();
        height = this.getParent().getHeight();
        drawAreaDimension = new Dimension(width, height);
        return new Dimension(width, height);
    }

    protected void paintComponent(Graphics g) {
        if (offscreenImage == null) {
            System.out.println("DrawControl.paintComponent()");
            offscreenImage = createImage(width, height);
            offscreenGraphics2D = (Graphics2D) offscreenImage.getGraphics();
            offscreenGraphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(offscreenImage, 0, 0, null); // ?
    }

    public void clear() {
        System.out.println("DrawControl.clear()");
        offscreenGraphics2D.setColor(backgroundColor);
        offscreenGraphics2D.fillRect(0, 0, width, height);
        repaint();
    }

    public void onFigureConstructed(Figure figure) {
        if (strokeColor != null) {
            figure.setStrokeColor(strokeColor);
        }
        if (figure instanceof Figure2D && fillColor != null) {
            ((Figure2D) figure).setFillColor(fillColor);
        }
        figure.draw(offscreenGraphics2D);
    }

    public Image getImage() {
        return offscreenImage;
    }

    public void setImage(Image image) {
        offscreenImage = image;
        offscreenGraphics2D = (Graphics2D) offscreenImage.getGraphics();
        offscreenGraphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        this.repaint();
    }

    public void saveImageToTempFile() {
        try {
            ImageIO.write((RenderedImage) offscreenImage, "png", tmpFile);
        } catch (Exception ex) {
            System.err.println("BaseMouseAdapter.addAndDrawNewPoint(): error occurred");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public Graphics2D restoreImageFromTempFile() {
        // restore image from temp file that does not have
        // added helper points
        try {
            Image image = ImageIO.read(tmpFile);
            setImage(image);
        } catch (Exception ex) {
            System.err.println("BaseMouseAdapter.addAndDrawNewPoint(): error occurred");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        return offscreenGraphics2D;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}
