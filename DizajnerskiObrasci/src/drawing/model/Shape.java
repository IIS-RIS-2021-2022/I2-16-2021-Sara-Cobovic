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

}