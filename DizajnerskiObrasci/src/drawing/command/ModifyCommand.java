package drawing.command;

import drawing.logging.Logger;
import drawing.model.*;
import drawing.view.PnlDrawing;

public class ModifyCommand extends Command {

    private Shape oldShape;
    private Shape oldShape2;

    public ModifyCommand(Shape shape, PnlDrawing pnlDrawing, DrawingModel drawingModel) {
        super(shape, pnlDrawing, drawingModel);
    }

    @Override
    void doCommand() {
        if (shape instanceof Point) {
            Point p;
            p = new Point();
            p.setX(((Point) shape).getX());
            p.setY(((Point) shape).getY());
            p.setColor(shape.getColor());
            oldShape = p;

            if(oldShape2 != null) {
                p = (Point) shape;
                p.setX(((Point) oldShape2).getX());
                p.setY(((Point) oldShape2).getY());
                p.setColor(oldShape2.getColor());
            }

        } else if (shape instanceof Line) {
            Line l = new Line();
            l.setStartPoint(new Point(((Line) shape).getStartPoint().getX(),
                    ((Line) shape).getStartPoint().getY()));
            l.setEndPoint(new Point(((Line) shape).getEndPoint().getX(),
                    ((Line) shape).getEndPoint().getY()));
            l.setColor(shape.getColor());
            oldShape = l;

            if(oldShape2 != null) {
                l = (Line) shape;
                l.setStartPoint(new Point(((Line) oldShape2).getStartPoint().getX(),
                        ((Line) oldShape2).getStartPoint().getY()));
                l.setEndPoint(new Point(((Line) oldShape2).getEndPoint().getX(),
                        ((Line) oldShape2).getEndPoint().getY()));
                l.setColor(oldShape2.getColor());
            }

        } else if (shape instanceof Rectangle) {
            Rectangle r = new Rectangle();

            r.setHeight(((Rectangle) shape).getHeight());
            r.setWidth(((Rectangle) shape).getWidth());
            r.setUpperLeftPoint(new Point(((Rectangle) shape).getUpperLeftPoint().getX(),
                    ((Rectangle) shape).getUpperLeftPoint().getY()));
            r.setColor(shape.getColor());
            r.setInnerColor(((Rectangle) shape).getInnerColor());

            oldShape = r;

            if(oldShape2 != null) {
                r = (Rectangle) shape;
                r.setHeight(((Rectangle) oldShape2).getHeight());
                r.setWidth(((Rectangle) oldShape2).getWidth());
                r.setUpperLeftPoint(new Point(((Rectangle) oldShape2).getUpperLeftPoint().getX(),
                        ((Rectangle) oldShape2).getUpperLeftPoint().getY()));
                r.setColor(oldShape2.getColor());
                r.setInnerColor(((Rectangle) oldShape2).getInnerColor());
            }
        } else if (shape instanceof Donut) {
            Donut d = new Donut();

            d.setCenter(new Point(((Donut) shape).getCenter().getX(),
                    ((Donut) shape).getCenter().getY()));
            d.setRadius(((Donut) shape).getRadius());
            d.setInnerRadius(((Donut) shape).getInnerRadius());
            d.setColor(shape.getColor());
            d.setInnerColor(((Donut) shape).getInnerColor());

            oldShape = d;

            if(oldShape2 != null) {
                d = (Donut) shape;

                d.setCenter(new Point(((Donut) oldShape2).getCenter().getX(),
                        ((Donut) oldShape2).getCenter().getY()));
                d.setRadius(((Donut) oldShape2).getRadius());
                d.setInnerRadius(((Donut) oldShape2).getInnerRadius());
                d.setColor(oldShape2.getColor());
                d.setInnerColor(((Donut) oldShape2).getInnerColor());
            }

        } else if (shape instanceof Circle) {
            Circle c = new Circle();
            c.setCenter(new Point(((Circle) shape).getCenter().getX(),
                    ((Circle) shape).getCenter().getY()));
            c.setRadius(((Circle) shape).getRadius());
            c.setColor(shape.getColor());
            c.setInnerColor(((Circle) shape).getInnerColor());

            oldShape = c;

            if(oldShape2 != null) {
                c = (Circle) shape;
                c.setCenter(new Point(((Circle) oldShape2).getCenter().getX(),
                        ((Circle) oldShape2).getCenter().getY()));
                c.setRadius(((Circle) oldShape2).getRadius());
                c.setColor(oldShape2.getColor());
                c.setInnerColor(((Circle) oldShape2).getInnerColor());
            }
        }
        pnlDrawing.repaint();
        Logger.addLog("Modify " + shape);
    }

    @Override
    void undoCommand() {
        if (shape instanceof Point) {
            Point p2 = new Point();
            p2.setX(((Point) shape).getX());
            p2.setY(((Point) shape).getY());
            p2.setColor(shape.getColor());

            Point p = (Point) shape;
            p.setX(((Point) oldShape).getX());
            p.setY(((Point) oldShape).getY());
            p.setColor(oldShape.getColor());

            oldShape2 = p2;



        } else if (shape instanceof Line) {
            Line l2 = new Line();
            l2.setStartPoint(new Point(((Line) shape).getStartPoint().getX(),
                    ((Line) shape).getStartPoint().getY()));
            l2.setEndPoint(new Point(((Line) shape).getEndPoint().getX(),
                    ((Line) shape).getEndPoint().getY()));
            l2.setColor(shape.getColor());

            Line l = (Line) shape;
            l.setStartPoint(new Point(((Line) oldShape).getStartPoint().getX(),
                    ((Line) oldShape).getStartPoint().getY()));
            l.setEndPoint(new Point(((Line) oldShape).getEndPoint().getX(),
                    ((Line) oldShape).getEndPoint().getY()));
            l.setColor(oldShape.getColor());

            oldShape2 = l2;

        } else if (shape instanceof Rectangle) {
            Rectangle r2 = new Rectangle();

            r2.setHeight(((Rectangle) shape).getHeight());
            r2.setWidth(((Rectangle) shape).getWidth());
            r2.setUpperLeftPoint(new Point(((Rectangle) shape).getUpperLeftPoint().getX(),
                    ((Rectangle) shape).getUpperLeftPoint().getY()));
            r2.setColor(shape.getColor());
            r2.setInnerColor(((Rectangle) shape).getInnerColor());

            Rectangle r = (Rectangle) shape;

            r.setHeight(((Rectangle) oldShape).getHeight());
            r.setWidth(((Rectangle) oldShape).getWidth());
            r.setUpperLeftPoint(new Point(((Rectangle) oldShape).getUpperLeftPoint().getX(),
                    ((Rectangle) oldShape).getUpperLeftPoint().getY()));
            r.setColor(oldShape.getColor());
            r.setInnerColor(((Rectangle) oldShape).getInnerColor());

            oldShape2 = r2;

        } else if (shape instanceof Donut) {
            Donut d2 = new Donut();
            d2.setCenter(new Point(((Donut) shape).getCenter().getX(),
                    ((Donut) shape).getCenter().getY()));
            d2.setRadius(((Donut) shape).getRadius());
            d2.setInnerRadius(((Donut) shape).getInnerRadius());
            d2.setColor(shape.getColor());
            d2.setInnerColor(((Donut) shape).getInnerColor());

            Donut d = (Donut) shape;
            d.setCenter(new Point(((Donut) oldShape).getCenter().getX(),
                    ((Donut) oldShape).getCenter().getY()));
            d.setRadius(((Donut) oldShape).getRadius());
            d.setInnerRadius(((Donut) oldShape).getInnerRadius());
            d.setColor(oldShape.getColor());
            d.setInnerColor(((Donut) oldShape).getInnerColor());

            oldShape2 = d2;

        } else if (shape instanceof Circle) {
            Circle c2 = new Circle();
            c2.setCenter(new Point(((Circle) shape).getCenter().getX(),
                    ((Circle) shape).getCenter().getY()));
            c2.setRadius(((Circle) shape).getRadius());
            c2.setColor(shape.getColor());
            c2.setInnerColor(((Circle) shape).getInnerColor());

            Circle c = (Circle) shape;
            c.setCenter(new Point(((Circle) oldShape).getCenter().getX(),
                    ((Circle) oldShape).getCenter().getY()));
            c.setRadius(((Circle) oldShape).getRadius());
            c.setColor(oldShape.getColor());
            c.setInnerColor(((Circle) oldShape).getInnerColor());

            oldShape2 = c2;
        }
        pnlDrawing.repaint();
        Logger.addLog("Undo modify " + shape);
    }
}