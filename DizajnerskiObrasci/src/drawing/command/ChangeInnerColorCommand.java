package drawing.command;

import java.awt.*;

import drawing.model.Circle;
import drawing.model.DrawingModel;
import drawing.model.Rectangle;
import drawing.model.Shape;
import drawing.model.SurfaceShape;
import drawing.model.adapter.HexagonAdapter;
import drawing.view.PnlDrawing;

public class ChangeInnerColorCommand extends Command {
    private Color oldColor;
    private Color newColor;

    public ChangeInnerColorCommand(Shape shape, PnlDrawing pnlDrawing, Color color, DrawingModel drawingModel) {
        super(shape, pnlDrawing, drawingModel);
        newColor = color;
    }

    @Override
    void doCommand() {
        if (shape instanceof SurfaceShape) {
            SurfaceShape s = (SurfaceShape) shape;
            oldColor = s.getInnerColor();
            s.setInnerColor(newColor);
        }
        pnlDrawing.repaint();
        undo = false;
    }

    @Override
    void undoCommand() {
        if (shape instanceof SurfaceShape) {
            SurfaceShape s = (SurfaceShape) shape;
            s.setInnerColor(oldColor);
        }
        pnlDrawing.repaint();
        undo = true;
    }

    @Override
    public String log() {
        if(undo) {
            return "Undo Change inner color " + shape;
        }
        return "Change inner color: " + shape;
    }
}