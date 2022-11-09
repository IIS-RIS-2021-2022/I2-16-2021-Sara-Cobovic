package drawing.controller;

import drawing.logging.Logger;
import drawing.model.DrawingModel;
import drawing.model.Shape;

import java.util.ArrayList;
import java.util.List;

public class BringToBackController {

    private DrawingModel drawingModel;

    public BringToBackController(DrawingModel drawingModel) {
        this.drawingModel = drawingModel;
    }

    public void bringToBack() {
        if (drawingModel.getSelected() == null) {
            return;
        }
        int index = drawingModel.getShapes().indexOf(drawingModel.getSelected());
        if (index == 0) {
            return;
        }
        List<Shape> oldShapes = new ArrayList<>();
        for (int i = index - 1; i >= 0; i--) {
            oldShapes.add(drawingModel.getShapes().get(i));
        }

        for (Shape shape : oldShapes) {
            drawingModel.getShapes().set(index--, shape);
        }
        drawingModel.getShapes().set(0, drawingModel.getSelected());
        Logger.addLog("Bring to back " + drawingModel.getSelected());
    }
}