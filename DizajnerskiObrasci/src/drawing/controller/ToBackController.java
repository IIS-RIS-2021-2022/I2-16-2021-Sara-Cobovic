package drawing.controller;

import drawing.logging.Logger;
import drawing.model.DrawingModel;
import drawing.model.Shape;

public class ToBackController {

    private DrawingModel drawingModel;

    public ToBackController(DrawingModel drawingModel) {
        this.drawingModel = drawingModel;
    }

    public void toBack() {
        if(drawingModel.getSelected() == null) {
            return;
        }
        int index = drawingModel.getShapes().indexOf(drawingModel.getSelected());
        if(index == 0) {
            return;
        }
        Shape oldShape = drawingModel.getShapes().get(index - 1);
        drawingModel.getShapes().set(index - 1, drawingModel.getSelected());
        drawingModel.getShapes().set(index, oldShape);
        Logger.addLog("To back " + drawingModel.getSelected());
    }
}