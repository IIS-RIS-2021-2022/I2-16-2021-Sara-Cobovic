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
        undo = false;
    }

    @Override
    void undoCommand() {
        drawingModel.getShapes().add(shape);
        pnlDrawing.repaint();
        undo = true;
    }

    @Override
    public String log() {
        if(undo) {
            return "Undo Delete " + shape;
        }
        return "Delete: " + shape;
    }
}