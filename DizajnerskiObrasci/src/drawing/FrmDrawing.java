package drawing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FrmDrawing extends JFrame {

	private PnlDrawing drawing = new PnlDrawing(this);
	private JPanel contentPane;
	JToggleButton tglbtnPoint;
	JToggleButton tglbtnLine;
	JToggleButton tglbtnRectangle;
	JToggleButton tglbtnCircle;
	JToggleButton tglbtnDonut;
	JToggleButton tglbtnSelection;
	JButton btnModify;
	JButton btnDelete;
	JButton colorChange;
	JButton innerColorChange;
	private ButtonGroup btnGroup = new ButtonGroup();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Sara Cobovic I2 16/2021");

		contentPane.add(drawing, BorderLayout.CENTER);//dodavanje panela na contentPane

		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(new Color(205, 92, 92));
		contentPane.add(toolBar, BorderLayout.NORTH);

		tglbtnPoint = new JToggleButton("Point");
		toolBar.add(tglbtnPoint);
		btnGroup.add(tglbtnPoint);

		tglbtnLine = new JToggleButton("Line");
		toolBar.add(tglbtnLine);
		btnGroup.add(tglbtnLine);


		tglbtnRectangle = new JToggleButton("Rectangle");
		toolBar.add(tglbtnRectangle);
		btnGroup.add(tglbtnRectangle);


		tglbtnCircle = new JToggleButton("Circle");
		toolBar.add(tglbtnCircle);
		btnGroup.add(tglbtnCircle);


		tglbtnDonut = new JToggleButton("Donut");
		toolBar.add(tglbtnDonut);
		btnGroup.add(tglbtnDonut);

		toolBar.add(new JLabel("Fill:"));

		innerColorChange = new JButton();
		innerColorChange.setBackground(drawing.getInnerColor());
		toolBar.add(innerColorChange);

		innerColorChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawing.innerColorChange();
			}
		});

		toolBar.add(new JLabel("Outline:"));

		colorChange = new JButton();
		colorChange.setBackground(drawing.getColor());
		toolBar.add(colorChange);

		colorChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawing.colorChange();
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(new Color(205, 92, 92));
		contentPane.add(panel, BorderLayout.SOUTH);

		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawing.modify();
			}
		});
		panel.add(btnModify);

		tglbtnSelection = new JToggleButton("Selection");
		panel.add(tglbtnSelection);
		btnGroup.add(tglbtnSelection);


		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawing.delete();
			}
		});
		panel.add(btnDelete);
	}


	// getteri i setteri za sve dugmice
	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public JToggleButton getTglbtnSelection() {
		return tglbtnSelection;
	}

	public void setTglbtnSelection(JToggleButton tglbtnSelection) {
		this.tglbtnSelection = tglbtnSelection;
	}

	public JButton getTglbtnModify() {
		return btnModify;
	}

	public void setTglbtnModify(JButton tglbtnModify) {
		this.btnModify = tglbtnModify;
	}

	public JButton getTglbtnDelete() {
		return btnDelete;
	}

	public void setTglbtnDelete(JButton tglbtnDelete) {
		this.btnDelete = tglbtnDelete;
	}

	public JButton getColorChange() {
		return colorChange;
	}

	public void setColorChange(JButton colorChange) {
		this.colorChange = colorChange;
	}

	public JButton getInnerColorChange() {
		return innerColorChange;
	}

	public void setInnerColorChange(JButton innerColorChange) {
		this.innerColorChange = innerColorChange;
	}
}