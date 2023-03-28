package drawing.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import drawing.controller.BringToBackController;
import drawing.controller.BringToFrontController;
import drawing.controller.ColorChangeController;
import drawing.controller.DeleteShapeController;
import drawing.controller.InnerColorChangeController;
import drawing.controller.ModifyShapeController;
import drawing.controller.OnClickController;
import drawing.controller.OpenController;
import drawing.controller.SaveController;
import drawing.controller.ToBackController;
import drawing.controller.ToFrontController;
import drawing.model.DrawingModel;
import drawing.model.Shape;

public class PnlDrawing extends JPanel {

	private FrmDrawing frame;
	private DrawingModel drawingModel;

	public PnlDrawing(FrmDrawing frame) {
		this.frame = frame;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				thisMouseClicked(e);
			}
		});

		this.drawingModel = new DrawingModel();
		drawingModel.addObserver(frame);
	}

	protected void thisMouseClicked(MouseEvent e) {
		OnClickController onClickController = new OnClickController(drawingModel, this);
		onClickController.onMouseClicked(e);
		repaint();
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
		new ColorChangeController(drawingModel, this).changeColor();
		repaint();
	}

	public void innerColorChange() {
		new InnerColorChangeController(drawingModel, this).changeInnerColor();
		repaint();
	}

	public DrawingModel getDrawingModel() {
		return drawingModel;
	}

	public void toFront() {
		ToFrontController toFrontController = new ToFrontController(drawingModel, this);
		toFrontController.toFront();
		repaint();
	}

	public void toBack() {
		ToBackController toBackController = new ToBackController(drawingModel, this);
		toBackController.toBack();
		repaint();
	}

	public void bringToFront() {
		BringToFrontController bringToFrontController = new BringToFrontController(drawingModel, this);
		bringToFrontController.bringToFront();
		repaint();
	}

	public void bringToBack() {
		BringToBackController bringToBackController = new BringToBackController(drawingModel, this);
		bringToBackController.bringToBack();
		repaint();
	}

	public void save() {
		new SaveController(this).save();
	}

	public void open() {
		new OpenController(drawingModel, this).open();
	}
}