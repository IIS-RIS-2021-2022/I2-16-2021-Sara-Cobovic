package drawing.command;

import drawing.PnlDrawing;
import drawing.Shape;
import drawing.logging.Logger;

public class AddCommand extends Command {


    public AddCommand(Shape shape, PnlDrawing pnlDrawing) {
        super(shape, pnlDrawing);
    }


    @Override
    public void doCommand() {
        pnlDrawing.getShapes().add(shape);
        pnlDrawing.repaint();
        Logger.addLog("Adding " + shape);
    }

    @Override
    public void undoCommand() {
        pnlDrawing.getShapes().remove(shape);
        pnlDrawing.repaint();
        Logger.addLog("Undo adding " + shape);
    }
}