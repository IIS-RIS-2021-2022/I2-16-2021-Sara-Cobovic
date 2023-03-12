package drawing.command;

import drawing.model.DrawingModel;
import drawing.model.Shape;
import drawing.view.PnlDrawing;

public class ToBackCommand extends Command {

    private int currentIndex;
    private Shape oldShape;
    private int oldIndex;

    public ToBackCommand(Shape shape, PnlDrawing pnlDrawing, DrawingModel drawingModel, int currentIndex) {
        super(shape, pnlDrawing, drawingModel);
        this.currentIndex = currentIndex;
    }

    @Override
    public void doCommand() {
        oldIndex = currentIndex - 1;
        oldShape = drawingModel.getShapes().get(oldIndex);

        drawingModel.getShapes().set(oldIndex, shape);
        drawingModel.getShapes().set(currentIndex, oldShape);

        pnlDrawing.repaint();

        undo = false;
    }

    @Override
    public void undoCommand() {
        drawingModel.getShapes().set(currentIndex, shape);
        drawingModel.getShapes().set(oldIndex, oldShape);

        pnlDrawing.repaint();
        undo = true;
    }

    @Override
    public String log() {
        if(undo) {
            return "Undo bring to front " + shape;
        }
        return "Bring to front " + shape;
    }
}