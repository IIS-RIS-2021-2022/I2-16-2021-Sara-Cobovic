package drawing.model;

import java.awt.*;

public class Circle extends SurfaceShape {

	protected Point center;
	protected int radius;

	public Circle() {

	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	public Circle(Point center, int radius, Color color, Color innerColor) {
		this(center, radius);
		this.setColor(color);
		this.setInnerColor(innerColor);
	}

	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		this.setSelected(selected);
	}

	public Circle(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		this.setColor(color);
	}

	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		this.setInnerColor(innerColor);
	}
	//area
	public double area() {
		return radius * radius * Math.PI;
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.center.moveBy(byX, byY);

	}

	@Override // po duzini radiusa
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return this.radius - ((Circle) o).radius;
		}
		return 0;
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - this.radius + 1, this.center.getY() - this.radius + 1, this.radius * 2 - 2,
				this.radius * 2 - 2);

	}

	@Override
	public boolean contains(int x, int y) {
		return center.distance(x, y) <= radius;
	}

	public boolean contains(Point p) {
		return center.distance(p.getX(), p.getY()) <= radius;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(this.center.getX() - this.radius, this.center.getY() - this.radius, this.radius * 2,
				this.radius * 2);
		this.fill(g);

		if (isSelected()) {
			drawSelection(g);
		}
	}

	protected void drawSelection(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(center.getX() - 3, center.getY() - 3, 6, 6);
		g.drawRect(center.getX() - radius - 3, center.getY() - 3, 6, 6);
		g.drawRect(center.getX() + radius - 3, center.getY() - 3, 6, 6);
		g.drawRect(center.getX() - 3, center.getY() - radius - 3, 6, 6);
		g.drawRect(center.getX() - 3, center.getY() + radius - 3, 6, 6);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle temp = (Circle) obj;
			if (this.center.equals(temp.center) &&
					this.radius == temp.radius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Circle (" +
				"center=" + center +
				", radius=" + radius +
				", color=" + getInnerColorCode() +
				", outline=" + getColorCode() +
				")";
	}
	public static Circle fromLogs(final String line) {
		Point p = readPoint("center", line);
		int radius = readInt("radius", line);
		Color color = readColor("color", line);
		Color outLine = readColor("outline", line);
		return new Circle(p, radius, outLine, color);
	}

	@Override
	public Shape clone() {
		Circle copy = new Circle();
		copy.setCenter(getCenter().clone());
		copy.setRadius(getRadius());
		copy.setInnerColor(getInnerColor());
		copy.setColor(getColor());
		return copy;
	}

	@Override
	public void modify(final Shape shape) {
		Circle source = (Circle) shape;
		setRadius(source.getRadius());
		setCenter(source.getCenter());
		setInnerColor(source.getInnerColor());
		setColor(source.getColor());
	}

}