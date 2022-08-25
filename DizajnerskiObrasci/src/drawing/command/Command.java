package drawing.command;

import drawing.PnlDrawing;
import drawing.Shape;

public abstract class Command {

    protected PnlDrawing pnlDrawing;
    protected Shape shape;

    public Command(Shape shape, PnlDrawing pnlDrawing) {
        this.shape = shape;
        this.pnlDrawing = pnlDrawing;
    }

    abstract void doCommand();

    abstract void undoCommand();
}
