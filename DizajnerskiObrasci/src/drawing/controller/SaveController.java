package drawing.controller;

import drawing.model.DrawingModel;
import drawing.view.PnlDrawing;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveController {

    private DrawingModel drawingModel;
    private PnlDrawing pnlDrawing;

    public SaveController(DrawingModel drawingModel, PnlDrawing pnlDrawing) {
        this.drawingModel = drawingModel;
        this.pnlDrawing = pnlDrawing;
    }
    public void save() {
        JFileChooser jfc = new JFileChooser(System.getProperty("user.home") + File.separator + "Desktop");
        String filename;
        if(jfc.showDialog(pnlDrawing, "Save") == JFileChooser.APPROVE_OPTION) {
            if(!jfc.getSelectedFile().getAbsolutePath().endsWith(".drw")) {
                filename = jfc.getSelectedFile().getAbsolutePath() + ".drw";
            } else {
                filename = jfc.getSelectedFile().getAbsolutePath();
            }
            ObjectOutputStream os;
            try {
                os = new ObjectOutputStream(new FileOutputStream(filename));
                os.writeObject(drawingModel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}