package drawing.view;

import drawing.command.*;
import drawing.controller.*;
import drawing.model.*;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	}

	protected void thisMouseClicked(MouseEvent e) {
		OnClickController onClickController = new OnClickController(drawingModel, this);
		onClickController.onMouseClicked(e);
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
		DeleteShapeController deleteShapeController = new DeleteShapeController(drawingModel, this);
		deleteShapeController.deleteShape();
	}

	// modify metoda
	public void modify() {
		ModifyShapeController modifyShapeController = new ModifyShapeController(drawingModel, this);
		modifyShapeController.modifyShape();
		repaint();
	}

	public FrmDrawing getFrame() {
		return frame;
	}

	public void colorChange() {
		InnerColorChangeController innerColorChangeController = new InnerColorChangeController(drawingModel, this);
		innerColorChangeController.changeInnerColor();
		repaint();
	}

	public void innerColorChange() {
		ColorChangeController colorChangeController = new ColorChangeController(drawingModel, this);
		colorChangeController.changeColor();
		repaint();
	}

	public DrawingModel getDrawingModel() {
		return drawingModel;
	}
}