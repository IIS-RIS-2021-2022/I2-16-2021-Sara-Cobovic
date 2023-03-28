package drawing.controller;

import javax.swing.*;

import drawing.command.CommandManager;
import drawing.command.ModifyCommand;
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
import drawing.view.DlgLine;
import drawing.view.DlgPoint;
import drawing.view.DlgRectangle;
import drawing.view.PnlDrawing;

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

            Shape oldShape = drawingModel.getSelected();

            if (oldShape instanceof Point) {
                DlgPoint dlgPoint = new DlgPoint();
                Point newPoint = (Point) oldShape.clone();

                dlgPoint.setPoint(newPoint);
                dlgPoint.setModal(true);
                dlgPoint.setVisible(true);
                if (dlgPoint.isOk()) {
                    newPoint.setX(Integer.parseInt(dlgPoint.getTxtX().getText()));
                    newPoint.setY(Integer.parseInt(dlgPoint.getTxtY().getText()));
                    newPoint.setColor(dlgPoint.getColor());
                    CommandManager.addCommand(new ModifyCommand(oldShape, newPoint, pnlDrawing, drawingModel));
                }

            } else if (oldShape instanceof Line) {
                Line newLine = (Line) oldShape.clone();
                DlgLine dlgLine = new DlgLine();

                dlgLine.setLine(newLine);
                dlgLine.setModal(true);
                dlgLine.setVisible(true);
                if (dlgLine.isOk()) {
                    newLine.setStartPoint(new Point(Integer.parseInt(dlgLine.getTxtStartX().getText()), Integer.parseInt(dlgLine.getTxtStartY().getText())));
                    newLine.setEndPoint(new Point(Integer.parseInt(dlgLine.getTxtEndX().getText()), Integer.parseInt(dlgLine.getTxtEndY().getText())));
                    newLine.setColor(dlgLine.getColor());
                    CommandManager.addCommand(new ModifyCommand(oldShape, newLine, pnlDrawing, drawingModel));

                }

            } else if (oldShape instanceof Rectangle) {
                DlgRectangle dlgRect = new DlgRectangle();
                Rectangle newRectangle = (Rectangle)oldShape.clone();

                dlgRect.setClicked(true);
                dlgRect.setRectangle(newRectangle);
                dlgRect.setModal(true);
                dlgRect.setVisible(true);

                if (dlgRect.isOk()) {
                    newRectangle.setHeight(Integer.parseInt(dlgRect.getTxtHeight().getText()));
                    newRectangle.setWidth(Integer.parseInt(dlgRect.getTxtWidth().getText()));
                    newRectangle.setUpperLeftPoint(new Point(Integer.parseInt(dlgRect.getTxtX().getText()), Integer.parseInt(dlgRect.getTxtY().getText())));
                    newRectangle.setColor(dlgRect.getColor());
                    newRectangle.setInnerColor(dlgRect.getInnerColor());
                    CommandManager.addCommand(new ModifyCommand(oldShape, newRectangle, pnlDrawing, drawingModel));
                }
            } else if (oldShape instanceof Donut) {
                Donut newDonut = (Donut) oldShape.clone();

                DlgDonut dlgDonut = new DlgDonut();
                dlgDonut.setClicked(true);
                dlgDonut.setDonut(newDonut);
                dlgDonut.setModal(true);
                dlgDonut.setVisible(true);

                if (dlgDonut.isOk()) {
                    newDonut.setCenter(new Point(Integer.parseInt(dlgDonut.getTxtX().getText()), Integer.parseInt(dlgDonut.getTxtY().getText())));
                    newDonut.setRadius(Integer.parseInt(dlgDonut.getTxtRadius().getText()));
                    newDonut.setInnerRadius(Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()));
                    newDonut.setColor(dlgDonut.getColor());
                    newDonut.setInnerColor(dlgDonut.getInnerColor());
                    CommandManager.addCommand(new ModifyCommand(oldShape, newDonut, pnlDrawing, drawingModel));
                }

            }  else if (oldShape instanceof Circle) {
                Circle newCircle = (Circle) oldShape.clone();

                DlgCircle dlgCircle = new DlgCircle();
                dlgCircle.setClicked(true);
                dlgCircle.setCircle(newCircle);
                dlgCircle.setModal(true);
                dlgCircle.setVisible(true);
                if (dlgCircle.isOk()) {
                    newCircle.setCenter(new Point(Integer.parseInt(dlgCircle.getTxtX().getText()), Integer.parseInt(dlgCircle.getTxtY().getText())));
                    newCircle.setRadius(Integer.parseInt(dlgCircle.getTxtRadius().getText()));
                    newCircle.setColor(dlgCircle.getColor());
                    newCircle.setInnerColor(dlgCircle.getInnerColor());
                    CommandManager.addCommand(new ModifyCommand(oldShape, newCircle, pnlDrawing, drawingModel));
                }
            }  else if (oldShape instanceof HexagonAdapter) {
                // TODO Check
                HexagonAdapter newHexagon = (HexagonAdapter) oldShape.clone();

                DlgHexagon dlgHexagon = new DlgHexagon();
                dlgHexagon.setClicked(true);
                dlgHexagon.setHexagon(newHexagon);
                dlgHexagon.setModal(true);
                dlgHexagon.setVisible(true);
                if (dlgHexagon.isOk()) {
                    newHexagon.setCenter(new Point(Integer.parseInt(dlgHexagon.getTxtX().getText()), Integer.parseInt(dlgHexagon.getTxtY().getText())));
                    newHexagon.setRadius(Integer.parseInt(dlgHexagon.getTxtRadius().getText()));
                    newHexagon.setColor(dlgHexagon.getColor());
                    newHexagon.setInnerColor(dlgHexagon.getInnerColor());
                    CommandManager.addCommand(new ModifyCommand(oldShape, newHexagon, pnlDrawing, drawingModel));
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "You need to select a shape!");
        }
    }
}