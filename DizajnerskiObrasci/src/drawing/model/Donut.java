package drawing.model;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {

	private int innerRadius;

	private Area area;

	public Donut() {}

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius); // mora biti 1. linija koda
		this.innerRadius = innerRadius;
		this.area = createArea();
	}

	public Donut(Point center, int radius, int innerRadius, Color color, Color innerColor) {
		this(center, radius, innerRadius);
		this.setColor(color);
		this.setInnerColor(innerColor);

	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		this.setSelected(selected);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		this.setColor(color);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color);
		this.setInnerColor(innerColor);
	}

	private Area createArea() {
		Ellipse2D outer = new Ellipse2D.Double(center.getX() - radius,center.getY() - radius, 2 * radius, 2 * radius);
		Ellipse2D inner = new Ellipse2D.Double(center.getX() - innerRadius,center.getY() - innerRadius, 2 * innerRadius, 2 * innerRadius);

		Area area = new Area(outer);
		area.subtract(new Area(inner));
		return area;
	}

	public int compareTo(Object o) { // po povrsini poredimo
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(getColor());
		g2d.draw(area);

		g.setColor(getInnerColor());
		g2d.fill(area);

		if (isSelected()) {
			drawSelection(g);
			g.drawRect(center.getX() - innerRadius - 3, center.getY() - 3, 6, 6);
			g.drawRect(center.getX() + innerRadius - 3, center.getY() - 3, 6, 6);
			g.drawRect(center.getX() - 3, center.getY() - innerRadius - 3, 6, 6);
			g.drawRect(center.getX() - 3, center.getY() + innerRadius - 3, 6, 6);
		}
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public boolean contains(int x, int y) {
		return area.contains(x, y);
	}

	public boolean contains(Point p) {
		return area.contains(p.getX(), p.getY());
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut temp = (Donut) obj;
			if (this.getCenter().equals(temp.getCenter()) && this.getRadius() == temp.getRadius()
					&& this.innerRadius == temp.innerRadius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	@Override
	public String toString() {
		return "Donut center=" + center + ", radius=" + radius + ", innerRadius=" + innerRadius;
	}

}