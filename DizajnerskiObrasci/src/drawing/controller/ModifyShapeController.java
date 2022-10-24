package drawing.controller;

import drawing.command.CommandManager;
import drawing.command.ModifyCommand;
import drawing.model.*;
import drawing.view.*;

import javax.swing.*;

public class ModifyShapeController {

    private DrawingModel drawingModel;
    private PnlDrawing pnlDrawing;

    public ModifyShapeController(DrawingModel drawingModel, PnlDrawing pnlDrawing) {
        this.drawingModel = drawingModel;
        this.pnlDrawing = pnlDrawing;
    }

    public void modifyShape() {
        if (drawingModel.getShapes().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You need to add shape first!");
            return;
        }
        if (drawingModel.getSelected() != null) {
            CommandManager.addCommand(new ModifyCommand(drawingModel.getSelected(), pnlDrawing, drawingModel));
            if (drawingModel.getSelected() instanceof Point) {
                Point p = (Point) drawingModel.getSelected();
                DlgPoint dlgPoint = new DlgPoint();
                dlgPoint.setPoint(p);
                dlgPoint.setModal(true);
                dlgPoint.setVisible(true);
                if (dlgPoint.isOk()) {
                    p.setX(Integer.parseInt(dlgPoint.getTxtX().getText()));
                    p.setY(Integer.parseInt(dlgPoint.getTxtY().getText()));
                    p.setColor(dlgPoint.getColor());

                }

            } else if (drawingModel.getSelected() instanceof Line) {
                Line l = (Line) drawingModel.getSelected();
                DlgLine dlgLine = new DlgLine();
                dlgLine.setLine(l);
                dlgLine.setModal(true);
                dlgLine.setVisible(true);
                if (dlgLine.isOk()) {
                    l.setStartPoint(new Point(Integer.parseInt(dlgLine.getTxtStartX().getText()), Integer.parseInt(dlgLine.getTxtStartY().getText())));
                    l.setEndPoint(new Point(Integer.parseInt(dlgLine.getTxtEndX().getText()), Integer.parseInt(dlgLine.getTxtEndY().getText())));
                    l.setColor(dlgLine.getColor());
                }

            } else if (drawingModel.getSelected() instanceof Rectangle) {
                Rectangle r = (Rectangle) drawingModel.getSelected();
                DlgRectangle dlgRect = new DlgRectangle();
                dlgRect.setClicked(true);
                dlgRect.setRectangle(r);
                dlgRect.setModal(true);
                dlgRect.setVisible(true);

                if (dlgRect.isOk()) {
                    r.setHeight(Integer.parseInt(dlgRect.getTxtHeight().getText()));
                    r.setWidth(Integer.parseInt(dlgRect.getTxtWidth().getText()));
                    r.setUpperLeftPoint(new Point(Integer.parseInt(dlgRect.getTxtX().getText()), Integer.parseInt(dlgRect.getTxtY().getText())));
                    r.setColor(dlgRect.getColor());
                    r.setInnerColor(dlgRect.getInnerColor());
                }
            } else if (drawingModel.getSelected() instanceof Donut) {
                Donut d = (Donut) drawingModel.getSelected();
                DlgDonut dlgDonut = new DlgDonut();
                dlgDonut.setClicked(true);
                dlgDonut.setDonut(d);
                dlgDonut.setModal(true);
                dlgDonut.setVisible(true);
                if (dlgDonut.isOk()) {
                    d.setCenter(new Point(Integer.parseInt(dlgDonut.getTxtX().getText()), Integer.parseInt(dlgDonut.getTxtY().getText())));
                    d.setRadius(Integer.parseInt(dlgDonut.getTxtRadius().getText()));
                    d.setInnerRadius(Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()));
                    d.setColor(dlgDonut.getColor());
                    d.setInnerColor(dlgDonut.getInnerColor());

                }

            } else if (drawingModel.getSelected() instanceof Circle) {
                Circle c = (Circle) drawingModel.getSelected();
                DlgCircle dlgCircle = new DlgCircle();
                dlgCircle.setClicked(true);
                dlgCircle.setCircle(c);
                dlgCircle.setModal(true);
                dlgCircle.setVisible(true);
                if (dlgCircle.isOk()) {
                    c.setCenter(new Point(Integer.parseInt(dlgCircle.getTxtX().getText()), Integer.parseInt(dlgCircle.getTxtY().getText())));
                    c.setRadius(Integer.parseInt(dlgCircle.getTxtRadius().getText()));
                    c.setColor(dlgCircle.getColor());
                    c.setInnerColor(dlgCircle.getInnerColor());

                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "You need to select a shape!");
        }
    }
}