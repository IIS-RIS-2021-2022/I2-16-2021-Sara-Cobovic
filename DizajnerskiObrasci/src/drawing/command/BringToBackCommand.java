package drawing.command;

import java.util.ArrayList;
import java.util.List;

import drawing.model.DrawingModel;
import drawing.model.Shape;
import drawing.view.PnlDrawing;

public class BringToBackCommand extends Command {

    private int currentIndex;
    private List<Shape> oldShapes;
    private List<Integer> oldIndexes;

    public BringToBackCommand(Shape shape, PnlDrawing pnlDrawing, DrawingModel drawingModel, int currentIndex) {
        super(shape, pnlDrawing, drawingModel);
        this.currentIndex = currentIndex;
    }

    @Override
    public void doCommand() {
        oldShapes = new ArrayList<>();
        oldIndexes = new ArrayList<>();
        int index = currentIndex;
        for (int i = index - 1; i >= 0; i--) {
            oldShapes.add(drawingModel.getShapes().get(i));
            oldIndexes.add(i);
        }

        for (Shape shape : oldShapes) {
            drawingModel.getShapes().set(index, shape);
            index--;
        }

        drawingModel.getShapes().set(0, shape);
        pnlDrawing.repaint();
        undo = false;
    }

    @Override
    public void undoCommand() {
        for(int i = 0; i < oldIndexes.size(); i++) {
            drawingModel.getShapes().set(oldIndexes.get(i), oldShapes.get(i));
        }

        drawingModel.getShapes().set(currentIndex, shape);
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