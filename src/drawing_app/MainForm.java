package drawing_app;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.File;

public class MainForm extends JFrame {

    private JButton rectangleButton;
    private JButton squareButton;
    private JButton triangleButton;
    private JButton ellipseButton;
    private JPanel drawPanel;
    private JPanel controlsPanel;
    private JPanel topPanel;
    private JPanel figure2DPanel;
    private JPanel figure1DPanel;
    private JButton clearButton;
    private JButton polygonButton;
    private JButton circleButton;
    private JButton rhombusButton;
    private JPanel generalControlsPanel;
    private JButton lineButton;
    private JButton rayButton;
    private JButton segmentButton;
    private JButton polygonalChainButton;
    private JButton fillColorButton;
    private JButton strokeColorButton;
    private JButton saveButton;
    private JButton loadButton;

    private final DrawControl drawControl;
    private JFileChooser fileChooser;

    public MainForm() {
        super("Drawing App. Trafimau");
        Container container = this.getContentPane();
        container.add(topPanel);

        drawControl = new DrawControl();
        drawPanel.add(drawControl, BorderLayout.CENTER);

        this.setBounds(200, 100, 1600, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        initListeners();
        initFileChooser();
    }

    private void initListeners() {
        ButtonsListener bl = new ButtonsListener();

        rectangleButton.addActionListener(bl);
        rhombusButton.addActionListener(bl);
        ellipseButton.addActionListener(bl);

        polygonButton.addActionListener(bl);
        triangleButton.addActionListener(bl);

        circleButton.addActionListener(bl);
        squareButton.addActionListener(bl);

        segmentButton.addActionListener(bl);
        rayButton.addActionListener(bl);
        lineButton.addActionListener(bl);
        polygonalChainButton.addActionListener(bl);

        fillColorButton.addActionListener(bl);
        strokeColorButton.addActionListener(bl);
        saveButton.addActionListener(bl);
        loadButton.addActionListener(bl);
        clearButton.addActionListener(bl);
    }

    private final void initFileChooser() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        // disable default filter that allows all file extensions
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".png");
            }

            @Override
            public String getDescription() {
                return "Png files (*.png)";
            }
        });
    }

    private void loadPaintingFromFile(ActionEvent e) {
        try {
            int fileChooseResult = fileChooser.showOpenDialog(this);
            switch (fileChooseResult) {
                case (JFileChooser.APPROVE_OPTION):
                    File toOpen = fileChooser.getSelectedFile();
                    Image image = ImageIO.read(toOpen);
                    drawControl.setImage(image);
                    break;
            }
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }

    private void savePaintingToFile(ActionEvent e) {
        try {
            int fileChooseResult = fileChooser.showSaveDialog(this);
            switch (fileChooseResult) {
                case (JFileChooser.APPROVE_OPTION):
                    File toSave = fileChooser.getSelectedFile();
                    if (!toSave.getPath().endsWith(".png")) {
                        toSave = new File(toSave.getCanonicalFile() + ".png");
                    }
                    ImageIO.write((RenderedImage) drawControl.getImage(),
                            "png", toSave);
                    break;
            }
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }

    private class ButtonsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object sender = e.getSource();

            if (sender == rectangleButton) {
                System.out.println("rectangle button clicked");
                drawControl.setFigureMode(DrawControl.FigureMode.RECTANGLE);
            } else if (sender == ellipseButton) {
                System.out.println("ellipse button clicked");
                drawControl.setFigureMode(DrawControl.FigureMode.ELLIPSE);
            } else if (sender == rhombusButton) {
                System.out.println("rhombus button clicked");
                drawControl.setFigureMode(DrawControl.FigureMode.RHOMBUS);
            } else if (sender == circleButton) {
                System.out.println("circle button clicked");
                drawControl.setFigureMode(DrawControl.FigureMode.CIRCLE);
            } else if (sender == squareButton) {
                System.out.println("square button clicked");
                drawControl.setFigureMode(DrawControl.FigureMode.SQUARE);
            } else if (sender == polygonButton) {
                System.out.println("polygon button clicked");
                drawControl.setFigureMode(DrawControl.FigureMode.POLYGON);
            } else if (sender == triangleButton) {
                System.out.println("triangle button clicked");
                drawControl.setFigureMode(DrawControl.FigureMode.TRIANGLE);
            } else if (sender == segmentButton) {
                System.out.println("segment button clicked");
                drawControl.setFigureMode(DrawControl.FigureMode.SEGMENT);
            } else if (sender == rayButton) {
                System.out.println("ray button clicked");
                drawControl.setFigureMode(DrawControl.FigureMode.RAY);
            } else if (sender == lineButton) {
                System.out.println("line button clicked");
                drawControl.setFigureMode(DrawControl.FigureMode.LINE);
            } else if (sender == polygonalChainButton) {
                System.out.println("polygonal chain button clicked");
                drawControl.setFigureMode(DrawControl.FigureMode.POLYGONAL_CHAIN);
            }

            else if (sender == fillColorButton) {
                System.out.println("fill color button clicked");
                Color pickedColor = JColorChooser.showDialog(
                        MainForm.this,"Set Fill Color", drawControl.getFillColor());
                drawControl.setFillColor(pickedColor);
            } else if (sender == strokeColorButton) {
                System.out.println("stroke color button clicked");
                Color pickedColor = JColorChooser.showDialog(
                        MainForm.this,"Set Stroke Color", drawControl.getStrokeColor());
                drawControl.setStrokeColor(pickedColor);
            } else if (sender == saveButton) {
                System.out.println("save button clicked");
                savePaintingToFile(e);
            }
            else if (sender == loadButton) {
                System.out.println("load button clicked");
                loadPaintingFromFile(e);
            }
            else if (sender == clearButton) {
                System.out.println("clear button clicked");
                drawControl.clear();
            }

            else {
                System.out.println("unknown button clicked");
            }
        }
    }
}
