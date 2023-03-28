package drawing.model.adapter;

import java.awt.*;

import drawing.model.Circle;
import drawing.model.Point;
import drawing.model.Shape;
import drawing.model.SurfaceShape;
import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape {

    private Hexagon hexagon;

    public HexagonAdapter(int x, int y, int radius) {
        this.hexagon = new Hexagon(x, y, radius);
    }
    public HexagonAdapter(int x, int y, int radius, Color color, Color innerColor) {
        this(x, y, radius);
        this.hexagon.setBorderColor(color);
        this.hexagon.setAreaColor(innerColor);
    }

    @Override
    public void moveBy(final int x, final int y) {
        hexagon.setX(x);
        hexagon.setY(y);
    }

    @Override
    public boolean contains(int x, int y) {
        return hexagon.doesContain(x, y);
    }

    public boolean contains(Point p) {
        return hexagon.doesContain(p.getX(), p.getY());
    }

    @Override
    public void draw(Graphics g) {
        this.hexagon.paint(g);
    }

    public Point getCenter() {
        return new Point(hexagon.getX(), hexagon.getY());
    }

    public void setCenter(Point center) {
        hexagon.setX(center.getX());
        hexagon.setY(center.getY());
    }

    @Override
    public void fill(final Graphics g) {}

    public int getRadius() {
        return hexagon.getR();
    }
    
    public void setRadius(int radius) {
        hexagon.setR(radius);
    }

    @Override
    public Color getColor() {
        return hexagon.getBorderColor();
    }

    @Override
    public void setColor(Color color) {
        hexagon.setBorderColor(color);
    }

    @Override
    public Color getInnerColor() {
        return hexagon.getAreaColor();
    }

    @Override
    public void setInnerColor(Color color) {
        hexagon.setAreaColor(color);
    }

    @Override
    public boolean isSelected() {
        return hexagon.isSelected();
    }

    @Override
    public void setSelected(final boolean selected) {
        hexagon.setSelected(selected);
    }

    @Override
    public String toString() {
        return "Hexagon (" +
                "center=" + getCenter() +
                ", radius=" + getRadius() +
                ", color=" + getInnerColorCode() +
                ", outline=" + getColorCode() +
                ")";
    }

    @Override
    public Shape clone() {
        HexagonAdapter copy = new HexagonAdapter(getCenter().getX(), getCenter().getY(), getRadius());
        copy.setInnerColor(getInnerColor());
        copy.setColor(getColor());
        return copy;
    }

    @Override
    public void modify(final Shape shape) {
        HexagonAdapter source = (HexagonAdapter) shape;
        setRadius(source.getRadius());
        setCenter(source.getCenter());
        setInnerColor(source.getInnerColor());
        setColor(source.getColor());
    }

    public static HexagonAdapter fromLogs(final String line) {
        drawing.model.Point p = readPoint("center", line);
        int radius = readInt("radius", line);
        Color color = readColor("color", line);
        Color outLine = readColor("outline", line);
        return new HexagonAdapter(p.getX(), p.getY(), radius, outLine, color);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HexagonAdapter) {
            Hexagon other = ((HexagonAdapter) obj).hexagon;
            return hexagon.getX() == other.getX() &&
                    hexagon.getY() == other.getY() &&
                    hexagon.getR() == other.getR();
        }
        return false;
    }

    @Override
    public int compareTo(final Object o) {
        return 0;
    }
}
