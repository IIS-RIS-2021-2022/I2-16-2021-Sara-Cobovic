package drawing.model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

//nadklasa za sve klase
public abstract class Shape implements Movable, Comparable, Serializable {

	// svaki oblik mora da moze da se selektuje i da ima neku boju zato ide u ovu
	// nadklasu
	private boolean selected;
	private Color color;

	public Shape() {

	}

	public Shape(Color color) {
		this.color = color;
	}

	public Shape(Color color, boolean selected) {
		this.color = color;
		this.selected = selected;
	}

	// apstraktne metode koje nam trebaju za svaki oblik
	public abstract boolean contains(int x, int y);
	public abstract void draw(Graphics g);
	public abstract Shape clone();
	public abstract void modify(Shape shape);

	// getteri i setteri
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	protected String getColorCode() {
		if(getColor() == null) {
			return null;
		}

		return String.format("#%02x%02x%02x", getColor().getRed(), getColor().getGreen(), getColor().getBlue());
	}

	protected static Color readColor(String key, String line) {
		String colorCode = readData(key, line);
		if(colorCode.equals("null")) {
			return null;
		}
		return Color.decode(readData(key, line));
	}

	protected static Point readPoint(String key, String line) {
		String pointLine = readPointData(key, line);
		return readPoint(pointLine);
	}
	protected static Point readPoint(String line) {
		int x = readInt("x", line);
		int y = readInt("y", line);
		return new Point(x, y);
	}

	protected static int readInt(String key, String line) {
		return Integer.parseInt(readData(key, line));
	}

	protected static String readPointData(String key, String line) {
		int start = line.indexOf(key + "=");
		line = line.substring(start);
		int end = line.indexOf(")");
		return line.substring(key.length() + 1, end + 1);
	}

	protected static String readData(String key, String line) {
		int start = line.indexOf(key + "=");
		line = line.substring(start);
		int end = line.length()-1;
		int separator1 = line.indexOf(",");
		int separator2 = line.indexOf(")");
		if(separator1 > separator2) {
			end = separator2;
		} else if(separator2 > separator1) {
			end = separator1;
		}

		if (end == -1) {
			end = line.indexOf(")");
		}
		return line.substring(key.length()+1, end);
	}
}