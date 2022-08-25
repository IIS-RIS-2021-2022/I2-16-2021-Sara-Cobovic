package drawing.command;

import drawing.PnlDrawing;
import drawing.Shape;

public class AddCommand extends Command {


    public AddCommand(Shape shape, PnlDrawing pnlDrawing) {
        super(shape, pnlDrawing);
    }


    @Override
    public void doCommand() {
        pnlDrawing.getShapes().add(shape);
        pnlDrawing.repaint();
    }

    @Override
    public void undoCommand() {
        pnlDrawing.getShapes().remove(shape);
        pnlDrawing.repaint();
    }
}
