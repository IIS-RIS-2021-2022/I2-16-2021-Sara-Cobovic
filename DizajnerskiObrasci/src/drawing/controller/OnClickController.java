package drawing.controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.*;

import drawing.command.AddCommand;
import drawing.command.CommandManager;
import drawing.model.Circle;
import drawing.model.Donut;
import drawing.model.DrawingModel;
import drawing.model.Line;
import drawing.model.Point;
import drawing.model.Rectangle;
import drawing.model.Shape;
import drawing.model.adapter.HexagonAdapter;
import drawing.view.DlgCircle;
import drawing.view.DlgDonut;
import drawing.view.DlgHexagon;
import drawing.view.DlgRectangle;
import drawing.view.FrmDrawing;
import drawing.view.PnlDrawing;

public class OnClickController {

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
                if (shape.contains(e.getX(), e.getY()))
                    selected = shape;
            }

            if (selected != null) {
                selected.setSelected(true);
            }

            pnlDrawing.repaint();
        } else if (frame.getTglbtnPoint().isSelected()) {
            newShape = new Point(e.getX(), e.getY(), color);
        } else if (frame.getTglbtnLine().isSelected()) {
            if (startPoint == null)
                startPoint = new Point(e.getX(), e.getY());
            else {
                newShape = new Line(startPoint, new Point(e.getX(), e.getY()), color);
                startPoint = null;
            }

        } else if (frame.getTglbtnRectangle().isSelected()) {
            DlgRectangle dlg = new DlgRectangle();
            dlg.setModal(true);
            dlg.setRectangle(new Rectangle(new Point(e.getX(), e.getY()), -1, -1, color, innerColor));
            dlg.setVisible(true);
            if (!dlg.isOk())
                return;
            try {
                newShape = dlg.getRectangle();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Wrong data type", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (frame.getTglbtnHexagon().isSelected()) {
            DlgHexagon dlg = new DlgHexagon();
            dlg.setModal(true);
            dlg.setHexagon(new HexagonAdapter(e.getX(), e.getY(), -1, color, innerColor));
            dlg.setVisible(true);
            if (!dlg.isOk())
                return;
            try {
                newShape = dlg.getHexagon();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Wrong data type", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (frame.getTglbtnCircle().isSelected()) {
            DlgCircle dlg = new DlgCircle();
            dlg.setModal(true);
            dlg.setCircle(new Circle(new Point(e.getX(), e.getY()), -1, color, innerColor));
            dlg.setVisible(true);
            if (!dlg.isOk())
                return;
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
            if (!dlg.isOk())
                return;
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
        if (newShape != null) {
            CommandManager.addCommand(new AddCommand(newShape, pnlDrawing, drawingModel));
        }
    }
}