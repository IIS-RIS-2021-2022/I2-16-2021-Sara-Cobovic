package drawing.controller;

import drawing.command.ChangeColorCommand;
import drawing.command.ChangeInnerColorCommand;
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
        drawingModel.setInnerColor(JColorChooser.showDialog(null, "Choose a color", drawingModel.getInnerColor()));
        pnlDrawing.getFrame().getInnerColorChange().setBackground(drawingModel.getInnerColor());
        if (drawingModel.getSelected() != null) {
            CommandManager.addCommand(new ChangeInnerColorCommand(drawingModel.getSelected(), pnlDrawing, drawingModel.getInnerColor(), drawingModel));
        }
    }
}