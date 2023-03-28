package drawing.command;

import drawing.model.*;
import drawing.view.PnlDrawing;

public class ModifyCommand extends Command {

    private Shape oldShape;
    private Shape newShape;

    public ModifyCommand(Shape shape, Shape newShape, PnlDrawing pnlDrawing, DrawingModel drawingModel) {
        super(shape, pnlDrawing, drawingModel);
        this.newShape = newShape;
        this.oldShape = shape.clone();
    }

    @Override
    void doCommand() {
        shape.modify(newShape);
        pnlDrawing.repaint();
        undo = false;
    }

    @Override
    void undoCommand() {
        shape.modify(oldShape);
        pnlDrawing.repaint();
        undo = true;
    }

    @Override
    public String log() {
        if (undo) {
            return "Undo Modify " + newShape;
        }
        return "Modify: " + oldShape + " : " + newShape;
    }
}