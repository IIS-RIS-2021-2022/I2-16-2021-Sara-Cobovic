package drawing.model;

import java.awt.Color;
import java.awt.Graphics;

//abstraktna klasa za sve one oblike koji imaju povrsinu (krug,pravougaonik i donut)
public abstract class SurfaceShape extends Shape {

	private Color innerColor;

	public abstract void fill(Graphics g);// abstraktna metoda za popunjavanje

	public Color getInnerColor() {
		return innerColor;
	}

	protected String getInnerColorCode() {
		if(getInnerColor() == null) {
			return null;
		}
		return String.format("#%02x%02x%02x", getInnerColor().getRed(), getInnerColor().getGreen(), getInnerColor().getBlue());
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

}