package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	private int x;
	private int y;

	public Point() {

	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point(int x, int y, Color color) {
		this(x,y);
		this.setColor(color);
	}

	public Point(int x, int y, boolean selected) {
		this(x, y);// mora biti prva linija!
		this.setSelected(selected);// selected je private pa joj moramo pristupiti!

	}

	public Point(int x, int y, boolean selected, Color color) {
		this(x, y, selected);
		this.setColor(color);

	}

	// distance metoda
	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}

	// compareTo metoda - po udajenosti od koordinatnog pocetka
	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point start = new Point(0, 0);
			return (int) (this.distance(start.getX(), start.getY()) - ((Point) o).distance(start.getX(), start.getY()));
		}
		return 0;
	}

	// moveBy metoda koju je obavezno implementirati
	public void moveBy(int byX, int byY) {
		this.x = this.x + byX;
		this.y = this.y + byY;

	}

	// metoda contains
	@Override
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3;
	}
    
	//metoda draw
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.x - 2, this.y, this.x + 2, this.y);
		g.drawLine(this.x, this.y - 2, this.x, this.y + 2);
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.x - 3, this.y - 3, 6, 6);//za iscrtavanje malog kvadrata kada je selektovan point, ovo prvo je tacka gore levo!
		}

	}

	// metoda equals
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point temp = (Point) obj;
			if (this.x == temp.x && this.y == temp.y) {// posto smo u toj klasi ne moramo preko get da pristupamo x i y
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		// (x,y)
		return "(" + x + "," + y + ")";
	}

}
