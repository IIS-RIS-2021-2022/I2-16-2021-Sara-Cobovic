package drawing.controller;

import drawing.command.BringToBackCommand;
import drawing.command.CommandManager;
import drawing.logging.ILogger;
import drawing.model.DrawingModel;
import drawing.view.PnlDrawing;

public class BringToBackController {

    private DrawingModel drawingModel;
    private PnlDrawing pnlDrawing;

    public BringToBackController(DrawingModel drawingModel, PnlDrawing pnlDrawing) {
        this.drawingModel = drawingModel;
        this.pnlDrawing = pnlDrawing;
    }

    public void bringToBack() {
        if (drawingModel.getSelected() == null) {
            return;
        }
        int currentIndex = drawingModel.getShapes().indexOf(drawingModel.getSelected());
        if (currentIndex == 0) {
            return;
        }

        CommandManager.addCommand(new BringToBackCommand(drawingModel.getSelected(), pnlDrawing, drawingModel, currentIndex));
    }
}