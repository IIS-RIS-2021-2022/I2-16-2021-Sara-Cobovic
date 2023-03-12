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
import drawing.controller.SaveLogsController;
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
			public void mouseClicked(MouseEvent arg0) {
				thisMouseClicked(arg0);
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
		frame.getLogger().setLogger(toFrontController);
		frame.getLogger().doLogging();
		repaint();
	}

	public void toBack() {
		ToBackController toBackController = new ToBackController(drawingModel, this);
		toBackController.toBack();
		frame.getLogger().setLogger(toBackController);
		frame.getLogger().doLogging();
		repaint();
	}

	public void bringToFront() {
		BringToFrontController bringToFrontController = new BringToFrontController(drawingModel, this);
		bringToFrontController.bringToFront();
		frame.getLogger().setLogger(bringToFrontController);
		frame.getLogger().doLogging();
		repaint();
	}

	public void bringToBack() {
		BringToBackController bringToBackController = new BringToBackController(drawingModel, this);
		bringToBackController.bringToBack();
		frame.getLogger().setLogger(bringToBackController);
		frame.getLogger().doLogging();
		repaint();
	}

	public void save() {
		new SaveController(drawingModel, this).save();
	}

	public void open() {
		new OpenController(drawingModel, this).open();
	}

	public void saveLogs() {
		new SaveLogsController(this).saveLogs();
	}
}