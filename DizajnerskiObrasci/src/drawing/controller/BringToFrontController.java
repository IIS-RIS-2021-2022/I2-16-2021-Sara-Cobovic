package drawing.controller;

import drawing.logging.ILogger;
import drawing.logging.Logger;
import drawing.model.DrawingModel;
import drawing.model.Shape;

import java.util.ArrayList;
import java.util.List;

public class BringToFrontController implements ILogger {

    private DrawingModel drawingModel;

    public BringToFrontController(DrawingModel drawingModel) {
        this.drawingModel = drawingModel;
    }

    public void bringToFront() {
        if (drawingModel.getSelected() == null) {
            return;
        }
        int index = drawingModel.getShapes().indexOf(drawingModel.getSelected());
        if (index == drawingModel.getShapes().size() - 1) {
            return;
        }
        List<Shape> oldShapes = new ArrayList<>();
        for (int i = index + 1; i < drawingModel.getShapes().size(); i++) {
            oldShapes.add(drawingModel.getShapes().get(i));
        }

        for (Shape shape : oldShapes) {
            drawingModel.getShapes().set(index++, shape);
        }
        drawingModel.getShapes().set(drawingModel.getShapes().size() - 1, drawingModel.getSelected());
    }

    @Override
    public String log() {
        return "Bring to front " + drawingModel.getSelected();
    }
}