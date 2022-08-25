package drawing.command;

import drawing.Circle;
import drawing.PnlDrawing;
import drawing.Rectangle;
import drawing.Shape;

import java.awt.*;

public class ChangeInnerColorCommand extends Command{
    private Color oldColor;
    private Color newColor;

    public ChangeInnerColorCommand(Shape shape, PnlDrawing pnlDrawing, Color color) {
        super(shape, pnlDrawing);
        newColor = color;
    }

    @Override
    void doCommand() {
        if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            oldColor = r.getInnerColor();
            r.setInnerColor(newColor);
        } else if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            oldColor = c.getInnerColor();
            c.setInnerColor(newColor);
        }
        pnlDrawing.repaint();
    }

    @Override
    void undoCommand() {
        if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            r.setInnerColor(oldColor);
        } else if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            c.setInnerColor(oldColor);
        }
        pnlDrawing.repaint();
    }
}
