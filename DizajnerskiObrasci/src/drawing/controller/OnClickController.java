package drawing.controller;

import drawing.command.AddCommand;
import drawing.command.CommandManager;
import drawing.logging.ILogger;
import drawing.logging.Logger;
import drawing.model.*;
import drawing.model.Point;
import drawing.model.Rectangle;
import drawing.model.Shape;
import drawing.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class OnClickController implements ILogger {

    private DrawingModel drawingModel;
    private PnlDrawing pnlDrawing;

    public OnClickController(DrawingModel drawingModel, PnlDrawing pnlDrawing) {
        this.drawingModel = drawingModel;
        this.pnlDrawing = pnlDrawing;
    }

    public void onMouseClicked(MouseEvent e) {
        Shape newShape = null;
        Shape selected = drawingModel.getSelected();
        FrmDrawing frame = pnlDrawing.getFrame();
        Point startPoint = drawingModel.getStartPoint();
        Color innerColor = drawingModel.getInnerColor();
        Color color = drawingModel.getColor();
        if (frame.getTglbtnSelection().isSelected()) {
            selected = null;
            Iterator<Shape> it = drawingModel.getShapes().iterator();
            while (it.hasNext()) {
                Shape shape = it.next();
                shape.setSelected(false);
                if (shape.contains(e.getX(), e.getY())) selected = shape;

            }
            if (selected != null) selected.setSelected(true);
            pnlDrawing.repaint();
        } else if (frame.getTglbtnPoint().isSelected()) {
            newShape = new Point(e.getX(), e.getY(), color);
        } else if (frame.getTglbtnLine().isSelected()) {
            if (startPoint == null) startPoint = new Point(e.getX(), e.getY());
            else {
                newShape = new Line(startPoint, new Point(e.getX(), e.getY()), color);
                startPoint = null;
            }

        } else if (frame.getTglbtnRectangle().isSelected()) {
            DlgRectangle dlg = new DlgRectangle();
            dlg.setModal(true);
            dlg.setRectangle(new Rectangle(new Point(e.getX(), e.getY()), -1, -1, color, innerColor));
            dlg.setVisible(true);
            if (!dlg.isOk()) return;
            try {
                newShape = dlg.getRectangle();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Wrong data type", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (frame.getTglbtnCircle().isSelected()) {
            DlgCircle dlg = new DlgCircle();
            dlg.setModal(true);
            dlg.setCircle(new Circle(new Point(e.getX(), e.getY()), -1, color, innerColor));
            dlg.setVisible(true);
            if (!dlg.isOk()) return;
            try {
                newShape = dlg.getCircle();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Wrong data type", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (frame.getTglbtnDonut().isSelected()) {
            DlgDonut dlg = new DlgDonut();
            dlg.setModal(true);
            dlg.setDonut(new Donut(new Point(e.getX(), e.getY()), -1, -1, color, innerColor));
            dlg.setVisible(true);
            if (!dlg.isOk()) return;
            try {
                newShape = dlg.getDonut();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Wrong data type", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        drawingModel.setColor(color);
        drawingModel.setInnerColor(innerColor);
        drawingModel.setSelected(selected);
        drawingModel.setStartPoint(startPoint);
        if (newShape != null){
            CommandManager.addCommand(new AddCommand(newShape, pnlDrawing, drawingModel));

        } else {
            pnlDrawing.getFrame().getLogger().setLogger(this);
            pnlDrawing.getFrame().getLogger().doLogging();
        }

    }

    @Override
    public String log() {
        if(drawingModel.getSelected() != null) {
            return "Selected " + drawingModel.getSelected();
        }
        return "Deselected";
    }
}