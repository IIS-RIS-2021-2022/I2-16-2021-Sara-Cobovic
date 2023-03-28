package drawing.controller;

import drawing.command.BringToFrontCommand;
import drawing.command.CommandManager;
import drawing.model.DrawingModel;
import drawing.view.PnlDrawing;

public class BringToFrontController {

    private DrawingModel drawingModel;

    private PnlDrawing pnlDrawing;

    public BringToFrontController(DrawingModel drawingModel, PnlDrawing pnlDrawing) {
        this.drawingModel = drawingModel;
        this.pnlDrawing = pnlDrawing;
    }

    public void bringToFront() {
        // If not selected
        if (drawingModel.getSelected() == null) {
            return;
        }

        int currentIndex = drawingModel.getShapes().indexOf(drawingModel.getSelected());

        // If already on top
        if (currentIndex == drawingModel.getShapes().size() - 1) {
            return;
        }

        CommandManager.addCommand(new BringToFrontCommand(drawingModel.getSelected(), pnlDrawing, drawingModel, currentIndex));
    }
}