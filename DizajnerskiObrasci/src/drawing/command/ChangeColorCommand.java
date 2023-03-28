package drawing.command;

import drawing.model.DrawingModel;
import drawing.view.PnlDrawing;
import drawing.model.Shape;
import drawing.logging.Logger;

import java.awt.*;

public class ChangeColorCommand extends Command {
    private Color oldColor;
    private Color newColor;

    public ChangeColorCommand(Shape shape, PnlDrawing pnlDrawing, Color color, DrawingModel drawingModel) {
        super(shape, pnlDrawing, drawingModel);
        newColor = color;
    }

    @Override
    void doCommand() {
        oldColor = shape.getColor();
        shape.setColor(newColor);
        pnlDrawing.repaint();
        undo = false;
    }

    @Override
    void undoCommand() {
        shape.setColor(oldColor);
        pnlDrawing.repaint();
        undo = true;
    }

    @Override
    public String log() {
        if(undo) {
            return "Undo Change color " + shape;
        }
        return "Change color: " + shape;
    }
}