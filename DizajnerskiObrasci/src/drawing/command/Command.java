package drawing.command;

import drawing.logging.ILogger;
import drawing.model.DrawingModel;
import drawing.view.PnlDrawing;
import drawing.model.Shape;

public abstract class Command implements ILogger {

    protected PnlDrawing pnlDrawing;
    protected Shape shape;
    protected DrawingModel drawingModel;
    protected boolean undo = false;

    public Command(Shape shape, PnlDrawing pnlDrawing, DrawingModel drawingModel) {
        this.shape = shape;
        this.pnlDrawing = pnlDrawing;
        this.drawingModel = drawingModel;
    }

    abstract void doCommand();

    abstract void undoCommand();
}