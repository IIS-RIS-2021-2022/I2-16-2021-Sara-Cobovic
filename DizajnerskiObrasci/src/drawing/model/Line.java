package drawing.model;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {
	private Point startPoint;
	private Point endPoint;

	public Line() {

	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	public Line(Point startPoint, Point endPoint, Color color) {
		this(startPoint, endPoint);
		this.setColor(color);
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		this.setSelected(selected);
	}

	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		this.setColor(color);
	}

	@Override
	public void moveBy(int byX, int byY) {// pozivamo je iz point!
		this.startPoint.moveBy(byX, byY);
		this.endPoint.moveBy(byX, byY);
	}

	// length metoda
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Line) {
			return (int) (this.length() - ((Line) o).length());
		}
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		if ((startPoint.distance(x, y) + endPoint.distance(x, y)) - length() <= 0.05) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.startPoint.getX() - 3, this.startPoint.getY() - 3, 6, 6);
			g.drawRect(this.endPoint.getX() - 3, this.endPoint.getY() - 3, 6, 6);
			g.drawRect(this.middleOfLine().getX() - 3, this.middleOfLine().getY() - 3, 6, 6);// middle of line je metoda
																								// koja vraca sredinu
																								// linije!
		}

	}

	// equals metoda
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line temp = (Line) obj;// down cast
			if (this.startPoint.equals(temp.startPoint) && this.endPoint.equals(temp.endPoint)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// midleOfLine metoda
	public Point middleOfLine() {
		int middleByX = (this.startPoint.getX() + this.endPoint.getX()) / 2;
		int middleByY = (this.startPoint.getY() + this.endPoint.getY()) / 2;
		Point middlePoint = new Point(middleByX, middleByY);
		return middlePoint;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public String toString() {
		return "Line startPoint=" + startPoint + ", endPoint=" + endPoint;
	}

}