package drawing.command;

import drawing.PnlDrawing;
import drawing.Shape;
import drawing.logging.Logger;

public class DeleteCommand extends Command {

    public DeleteCommand(Shape shape, PnlDrawing pnlDrawing) {
        super(shape, pnlDrawing);
    }

    @Override
    void doCommand() {
        pnlDrawing.getShapes().remove(shape);
        pnlDrawing.repaint();
        Logger.addLog("Deleting " + shape);
    }

    @Override
    void undoCommand() {
        pnlDrawing.getShapes().add(shape);
        pnlDrawing.repaint();
        Logger.addLog("Undo deleting " + shape);
    }
}