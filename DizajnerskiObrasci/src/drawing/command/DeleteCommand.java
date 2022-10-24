package drawing.command;

import drawing.model.DrawingModel;
import drawing.view.PnlDrawing;
import drawing.model.Shape;
import drawing.logging.Logger;

public class DeleteCommand extends Command {

    public DeleteCommand(Shape shape, PnlDrawing pnlDrawing, DrawingModel drawingModel) {
        super(shape, pnlDrawing, drawingModel);
    }

    @Override
    void doCommand() {
        drawingModel.getShapes().remove(shape);
        pnlDrawing.repaint();
        Logger.addLog("Deleting " + shape);
    }

    @Override
    void undoCommand() {
        drawingModel.getShapes().add(shape);
        pnlDrawing.repaint();
        Logger.addLog("Undo deleting " + shape);
    }
}