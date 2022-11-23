package drawing.controller;

import drawing.logging.ILogger;
import drawing.logging.Logger;
import drawing.model.DrawingModel;
import drawing.model.Shape;

public class ToFrontController implements ILogger {

    private DrawingModel drawingModel;

    public ToFrontController(DrawingModel drawingModel) {
        this.drawingModel = drawingModel;
    }

    public void toFront() {
        if(drawingModel.getSelected() == null) {
            return;
        }
        int index = drawingModel.getShapes().indexOf(drawingModel.getSelected());
        if(index == drawingModel.getShapes().size() - 1) {
            return;
        }
        Shape oldShape = drawingModel.getShapes().get(index + 1);
        drawingModel.getShapes().set(index + 1, drawingModel.getSelected());
        drawingModel.getShapes().set(index, oldShape);
    }

    @Override
    public String log() {
        return "To front " + drawingModel.getSelected();
    }
}