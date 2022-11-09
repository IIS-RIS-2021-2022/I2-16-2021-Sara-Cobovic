package drawing.view;

import drawing.command.*;
import drawing.controller.*;
import drawing.model.*;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

public class PnlDrawing extends JPanel {

	private FrmDrawing frame;
	private DrawingModel drawingModel;

	public PnlDrawing(FrmDrawing frame) {
		this.frame = frame;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				thisMouseClicked(arg0);
			}
		});
		this.drawingModel = new DrawingModel();
		drawingModel.addObserver(frame);
	}

	protected void thisMouseClicked(MouseEvent e) {
		new OnClickController(drawingModel, this).onMouseClicked(e);
	}

	// paint metoda
	public void paint(Graphics g) {
		super.paint(g);
		for (Shape shape : drawingModel.getShapes()) {
			shape.draw(g);
		}

	}

	// delete metoda
	public void delete() {
		new DeleteShapeController(drawingModel, this).deleteShape();

	}

	// modify metoda
	public void modify() {
		new ModifyShapeController(drawingModel, this).modifyShape();
		repaint();
	}

	public FrmDrawing getFrame() {
		return frame;
	}

	public void colorChange() {
		new InnerColorChangeController(drawingModel, this).changeInnerColor();
		repaint();
	}

	public void innerColorChange() {
		new ColorChangeController(drawingModel, this).changeColor();
		repaint();
	}

	public DrawingModel getDrawingModel() {
		return drawingModel;
	}

	public void toFront() {
		new ToFrontController(drawingModel).toFront();
		repaint();
	}

	public void toBack() {
		new ToBackController(drawingModel).toBack();
		repaint();
	}

	public void bringToFront() {
		new BringToFrontController(drawingModel).bringToFront();
		repaint();
	}

	public void bringToBack() {
		new BringToBackController(drawingModel).bringToBack();
		repaint();
	}
}