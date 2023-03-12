package drawing.controller;

import drawing.command.CommandManager;
import drawing.command.ToBackCommand;
import drawing.logging.ILogger;
import drawing.model.DrawingModel;
import drawing.view.PnlDrawing;

public class ToBackController implements ILogger {

    private DrawingModel drawingModel;
    private PnlDrawing pnlDrawing;

    public ToBackController(DrawingModel drawingModel, PnlDrawing pnlDrawing) {
        this.drawingModel = drawingModel;
        this.pnlDrawing = pnlDrawing;
    }

    public void toBack() {
        if(drawingModel.getSelected() == null) {
            return;
        }
        int currentIndex = drawingModel.getShapes().indexOf(drawingModel.getSelected());
        if(currentIndex == 0) {
            return;
        }

        CommandManager.addCommand(new ToBackCommand(drawingModel.getSelected(), pnlDrawing, drawingModel, currentIndex));
    }

    @Override
    public String log() {
        return "To back " + drawingModel.getSelected();
    }
}