package drawing.controller;

import drawing.command.ChangeColorCommand;
import drawing.command.CommandManager;
import drawing.model.DrawingModel;
import drawing.view.PnlDrawing;

import javax.swing.*;

public class InnerColorChangeController {

    private DrawingModel drawingModel;
    private PnlDrawing pnlDrawing;

    public InnerColorChangeController(DrawingModel drawingModel, PnlDrawing pnlDrawing) {
        this.drawingModel = drawingModel;
        this.pnlDrawing = pnlDrawing;
    }

    public void changeInnerColor() {
        drawingModel.setColor(JColorChooser.showDialog(null, "Choose a color", null));
        pnlDrawing.getFrame().getColorChange().setBackground(drawingModel.getColor());
        if (drawingModel.getSelected() != null) {
            CommandManager.addCommand(new ChangeColorCommand(drawingModel.getSelected(), pnlDrawing, drawingModel.getColor(), drawingModel));
            drawingModel.getSelected().setColor(drawingModel.getColor());
        }
    }
}