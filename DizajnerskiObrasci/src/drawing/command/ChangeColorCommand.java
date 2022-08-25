package drawing.command;

import drawing.PnlDrawing;
import drawing.Shape;

import java.awt.*;

public class ChangeColorCommand extends Command{
    private Color oldColor;
    private Color newColor;

    public ChangeColorCommand(Shape shape, PnlDrawing pnlDrawing, Color color) {
        super(shape, pnlDrawing);
        newColor = color;
    }

    @Override
    void doCommand() {
        oldColor = shape.getColor();
        shape.setColor(newColor);
        pnlDrawing.repaint();
    }

    @Override
    void undoCommand() {
        shape.setColor(oldColor);
        pnlDrawing.repaint();
    }
}
