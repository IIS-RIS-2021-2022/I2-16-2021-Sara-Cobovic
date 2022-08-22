package drawing;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;

public class PnlDrawing extends JPanel {

	private FrmDrawing frame;
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	Point startPoint;
	Shape selected;

	public PnlDrawing() {
		setBackground(new Color(220, 220, 220));

	}

	public PnlDrawing(FrmDrawing frame) {
		this.frame = frame;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				thisMouseClicked(arg0);
			}
		});

	}

	protected void thisMouseClicked(MouseEvent e) {
		Shape newShape = null;
		if (frame.getTglbtnSelection().isSelected()) {
			selected = null;
			Iterator<Shape> it = shapes.iterator();
			while (it.hasNext()) {
				Shape shape = it.next();
				shape.setSelected(false);
				if (shape.contains(e.getX(), e.getY()))
					selected = shape;

			}
			if (selected != null)
				selected.setSelected(true);
		} else if (frame.getTglbtnPoint().isSelected()) {
				newShape = new Point(e.getX(), e.getY(), Color.BLACK);
		} else if (frame.getTglbtnLine().isSelected()) {
			if (startPoint == null)
				startPoint = new Point(e.getX(), e.getY());
			else {
				newShape = new Line(startPoint, new Point(e.getX(), e.getY()), Color.BLACK);
				startPoint = null;
			}

		} else if (frame.getTglbtnRectangle().isSelected()) {
			DlgRectangle dlg = new DlgRectangle();
			dlg.setModal(true);
			dlg.setRectangle(new Rectangle(new Point(e.getX(), e.getY()), -1, -1));
			dlg.setVisible(true);
			if (!dlg.isOk())
				return;
			try {
				newShape = dlg.getRectangle();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Wrong data type", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (frame.getTglbtnCircle().isSelected()) {
			DlgCircle dlg = new DlgCircle();
			dlg.setModal(true);
			dlg.setCircle(new Circle(new Point(e.getX(), e.getY()), -1));
			dlg.setVisible(true);
			if (!dlg.isOk())
				return;
			try {
				newShape = dlg.getCircle();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Wrong data type", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (frame.getTglbtnDonut().isSelected()) {
			DlgDonut dlg = new DlgDonut();
			dlg.setModal(true);
			dlg.setDonut(new Donut(new Point(e.getX(), e.getY()), -1, -1));
			dlg.setVisible(true);
			if (!dlg.isOk())
				return;
			try {
				newShape = dlg.getDonut();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Wrong data type", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (newShape != null)
			shapes.add(newShape);
		repaint();
	}

	// paint metoda
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}

	}

	// delete metoda
	public void delete() {
		if (shapes.isEmpty())
			JOptionPane.showMessageDialog(null, "You need to add shape first!");
		else if (selected == null)
			JOptionPane.showMessageDialog(null, "You need to select a shape!");
		else {
			int question = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete shape",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (question == 0) {
				shapes.remove(selected);
			}
			repaint();
		}
	}

	// modify metoda
	public void modify() {
		if (selected != null) {
			if (selected instanceof Point) {
				Point p = (Point) selected;
				DlgPoint dlgPoint = new DlgPoint();
				dlgPoint.setPoint(p);
				dlgPoint.setModal(true);
				dlgPoint.setVisible(true);
				if (dlgPoint.isOk()) {
					p.setX(Integer.parseInt(dlgPoint.getTxtX().getText()));
					p.setY(Integer.parseInt(dlgPoint.getTxtY().getText()));
					p.setColor(dlgPoint.getColor());

				}

			} else if (selected instanceof Line) {
				Line l = (Line) selected;
				DlgLine dlgLine = new DlgLine();
				dlgLine.setLine(l);
				dlgLine.setModal(true);
				dlgLine.setVisible(true);
				if (dlgLine.isOk()) {
					l.setStartPoint(new Point(Integer.parseInt(dlgLine.getTxtStartX().getText()),
							Integer.parseInt(dlgLine.getTxtStartY().getText())));
					l.setEndPoint(new Point(Integer.parseInt(dlgLine.getTxtEndX().getText()),
							Integer.parseInt(dlgLine.getTxtEndY().getText())));
					l.setColor(dlgLine.getColor());
				}

			} else if (selected instanceof Rectangle) {
				Rectangle r = (Rectangle) selected;
				DlgRectangle dlgRect = new DlgRectangle();
				dlgRect.setClicked(true);
				dlgRect.setRectangle(r);
				dlgRect.setModal(true);
				dlgRect.setVisible(true);

				if (dlgRect.isOk()) {
					r.setHeight(Integer.parseInt(dlgRect.getTxtHeight().getText()));
					r.setWidth(Integer.parseInt(dlgRect.getTxtWidth().getText()));
					r.setUpperLeftPoint(new Point(Integer.parseInt(dlgRect.getTxtX().getText()),
							Integer.parseInt(dlgRect.getTxtY().getText())));
					r.setColor(dlgRect.getColor());
					r.setInnerColor(dlgRect.getInnerColor());
				}
			} else if (selected instanceof Donut) {
				Donut d = (Donut) selected;
				DlgDonut dlgDonut = new DlgDonut();
				dlgDonut.setClicked(true);
				dlgDonut.setDonut(d);
				dlgDonut.setModal(true);
				dlgDonut.setVisible(true);
				if (dlgDonut.isOk()) {
					d.setCenter(new Point(Integer.parseInt(dlgDonut.getTxtX().getText()),
							Integer.parseInt(dlgDonut.getTxtY().getText())));
					d.setRadius(Integer.parseInt(dlgDonut.getTxtRadius().getText()));
					d.setInnerRadius(Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()));
					d.setColor(dlgDonut.getColor());
					d.setInnerColor(dlgDonut.getInnerColor());

				}

			} else if (selected instanceof Circle) {
				Circle c = (Circle) selected;
				DlgCircle dlgCircle = new DlgCircle();
				dlgCircle.setClicked(true);
				dlgCircle.setCircle(c);
				dlgCircle.setModal(true);
				dlgCircle.setVisible(true);
				if (dlgCircle.isOk()) {
					c.setCenter(new Point(Integer.parseInt(dlgCircle.getTxtX().getText()),
							Integer.parseInt(dlgCircle.getTxtY().getText())));
					c.setRadius(Integer.parseInt(dlgCircle.getTxtRadius().getText()));
					c.setColor(dlgCircle.getColor());
					c.setInnerColor(dlgCircle.getInnerColor());

				}

			}
			repaint();
		}
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public Shape getSelected() {
		return selected;
	}

	public void setSelected(Shape selected) {
		this.selected = selected;
	}

}
