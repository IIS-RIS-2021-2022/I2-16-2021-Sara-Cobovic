package drawing.controller;

import drawing.command.CommandManager;
import drawing.command.DeleteCommand;
import drawing.model.DrawingModel;
import drawing.view.PnlDrawing;

import javax.swing.*;

public class DeleteShapeController {

    private DrawingModel drawingModel;
    private PnlDrawing pnlDrawing;

    public DeleteShapeController(DrawingModel drawingModel, PnlDrawing pnlDrawing) {
        this.drawingModel = drawingModel;
        this.pnlDrawing = pnlDrawing;
    }

    public void deleteShape() {
        if (drawingModel.getShapes().isEmpty()) JOptionPane.showMessageDialog(null, "You need to add shape first!");
        else if (drawingModel.getSelected() == null) JOptionPane.showMessageDialog(null, "You need to select a shape!");
        else {
            int question = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete shape", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (question == 0) {
                CommandManager.addCommand(new DeleteCommand(drawingModel.getSelected(), pnlDrawing, drawingModel));
            }
        }
    }
}