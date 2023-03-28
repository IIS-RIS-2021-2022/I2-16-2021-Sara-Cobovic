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
        undo = false;
    }

    @Override
    public void undoCommand() {
        drawingModel.getShapes().remove(shape);
        pnlDrawing.repaint();
        undo = true;
    }

    @Override
    public String log() {
        if(undo) {
            return "Undo Add " + shape;
        }
        return "Add: " + shape;
    }
}