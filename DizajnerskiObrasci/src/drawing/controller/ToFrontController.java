package drawing.controller;

import drawing.command.CommandManager;
import drawing.command.ToFrontCommand;
import drawing.logging.ILogger;
import drawing.model.DrawingModel;
import drawing.view.PnlDrawing;

public class ToFrontController implements ILogger {

    private DrawingModel drawingModel;
    private PnlDrawing pnlDrawing;

    public ToFrontController(DrawingModel drawingModel, PnlDrawing pnlDrawing) {
        this.drawingModel = drawingModel;
        this.pnlDrawing = pnlDrawing;
    }

    public void toFront() {
        if(drawingModel.getSelected() == null) {
            return;
        }
        int currentIndex = drawingModel.getShapes().indexOf(drawingModel.getSelected());
        if(currentIndex == drawingModel.getShapes().size() - 1) {
            return;
        }

        CommandManager.addCommand(new ToFrontCommand(drawingModel.getSelected(), pnlDrawing, drawingModel, currentIndex));
    }

    @Override
    public String log() {
        return "To front " + drawingModel.getSelected();
    }
}