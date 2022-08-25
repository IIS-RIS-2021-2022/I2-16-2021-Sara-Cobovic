package drawing.command;

import drawing.PnlDrawing;
import drawing.Shape;

public class DeleteCommand extends Command {

    public DeleteCommand(Shape shape, PnlDrawing pnlDrawing) {
        super(shape, pnlDrawing);
    }

    @Override
    void doCommand() {
        pnlDrawing.getShapes().remove(shape);
        pnlDrawing.repaint();
    }

    @Override
    void undoCommand() {
        pnlDrawing.getShapes().add(shape);
        pnlDrawing.repaint();
    }
}
