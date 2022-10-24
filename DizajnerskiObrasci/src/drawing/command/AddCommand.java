package drawing.command;

import drawing.model.DrawingModel;
import drawing.view.PnlDrawing;
import drawing.model.Shape;
import drawing.logging.Logger;

public class AddCommand extends Command {


    public AddCommand(Shape shape, PnlDrawing pnlDrawing, DrawingModel drawingModel) {
        super(shape, pnlDrawing, drawingModel);
    }


    @Override
    public void doCommand() {
        drawingModel.getShapes().add(shape);
        pnlDrawing.repaint();
        Logger.addLog("Adding " + shape);
    }

    @Override
    public void undoCommand() {
        drawingModel.getShapes().remove(shape);
        pnlDrawing.repaint();
        Logger.addLog("Undo adding " + shape);
    }
}