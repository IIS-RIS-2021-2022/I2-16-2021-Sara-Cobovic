package drawing;

import java.awt.Color;
import java.awt.Graphics;

//abstraktna klasa za sve one oblike koji imaju povrsinu (krug,pravougaonik i donut)
public abstract class SurfaceShape extends Shape {

	private Color innerColor;

	public abstract void fill(Graphics g);// abstraktna metoda za popunjavanje

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

}