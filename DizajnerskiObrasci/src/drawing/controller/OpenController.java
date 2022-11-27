package drawing.controller;

import drawing.model.DrawingModel;
import drawing.view.PnlDrawing;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class OpenController {

    private DrawingModel drawingModel;
    private PnlDrawing pnlDrawing;

    public OpenController(DrawingModel drawingModel, PnlDrawing pnlDrawing) {
        this.drawingModel = drawingModel;
        this.pnlDrawing = pnlDrawing;
    }

    public void open() {
        JFileChooser jfc = new JFileChooser(System.getProperty("user.home") + File.separator + "Desktop");
        File file;
        if(jfc.showDialog(pnlDrawing, "Open") == JFileChooser.APPROVE_OPTION) {
            if(!jfc.getSelectedFile().getName().endsWith(".drw")) {
                JOptionPane.showMessageDialog(pnlDrawing, "Only .drw files can be opened!");
                return;
            }
            file = jfc.getSelectedFile();
            ObjectInputStream os;
            try {
                os = new ObjectInputStream(new FileInputStream(file));
                DrawingModel readFile = (DrawingModel) os.readObject();
                if(readFile != null) {
                    drawingModel.setShapes(readFile.getShapes());
                    drawingModel.setColor(readFile.getColor());
                    drawingModel.setInnerColor(readFile.getInnerColor());
                    drawingModel.setStartPoint(readFile.getStartPoint());
                    drawingModel.setListeners(new ArrayList<>());
                    pnlDrawing.getFrame().getColorChange().setBackground(drawingModel.getColor());
                    pnlDrawing.getFrame().getInnerColorChange().setBackground(drawingModel.getInnerColor());
                    pnlDrawing.repaint();
                    JOptionPane.showMessageDialog(pnlDrawing, "Successfully loaded");
                } else {
                    JOptionPane.showMessageDialog(pnlDrawing, "Something went wrong!");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}