package drawing.model;

import drawing.observer.IListener;
import drawing.observer.IObserver;
import drawing.observer.ObserverEvents;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawingModel implements IObserver {

    private List<IListener> listeners = new ArrayList<>();

    private List<Shape> shapes = new ArrayList<>();
    private Shape selected;
    private Point startPoint;
    private Color color;
    private Color innerColor;

    public DrawingModel() {
        this.color = Color.BLACK;
        this.innerColor = Color.WHITE;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public Shape getSelected() {
        return selected;
    }

    public void setSelected(Shape selected) {
        if(selected != null) {
            notifyObservers(ObserverEvents.SELECTED);
        } else {
            notifyObservers(ObserverEvents.DESELECTED);
        }
        this.selected = selected;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getInnerColor() {
        return innerColor;
    }

    public void setInnerColor(Color innerColor) {
        this.innerColor = innerColor;
    }

    @Override
    public void addObserver(IListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeObserver(IListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyObservers(Object event) {
        for (IListener listener : listeners) {
            listener.update(event);
        }
    }
}