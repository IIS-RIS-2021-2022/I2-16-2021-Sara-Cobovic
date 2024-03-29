package drawing.view;

import drawing.command.CommandManager;
import drawing.logging.Logger;
import drawing.observer.IListener;
import drawing.observer.ObserverEvents;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmDrawing extends JFrame implements IListener {

	private PnlDrawing drawing = new PnlDrawing(this);
	private JPanel contentPane;
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnHexagon;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnDonut;
	private JToggleButton tglbtnSelection;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnDo;
	private JButton btnUndo;
	private JButton colorChange;
	private JButton innerColorChange;
	private JButton btnSeeLog;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JButton btnSave;
	private JButton btnOpen;
	private ButtonGroup btnGroup = new ButtonGroup();

	private Logger logger = new Logger();


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
		setBounds(100, 100, 1100, 700);
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

		tglbtnHexagon = new JToggleButton("Hexagon");
		toolBar.add(tglbtnHexagon);
		btnGroup.add(tglbtnHexagon);

		tglbtnCircle = new JToggleButton("Circle");
		toolBar.add(tglbtnCircle);
		btnGroup.add(tglbtnCircle);

		tglbtnDonut = new JToggleButton("Donut");
		toolBar.add(tglbtnDonut);
		btnGroup.add(tglbtnDonut);

		toolBar.add(new JSeparator());

		btnUndo = new JButton("Undo");
		toolBar.add(btnUndo);
		btnUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CommandManager.undoCommand();
			}
		});
		btnUndo.setEnabled(false);



		btnDo = new JButton("Redo");
		toolBar.add(btnDo);
		btnDo.addActionListener(e -> CommandManager.doCommand());
		btnDo.setEnabled(false);

		toolBar.add(new JSeparator());

		toolBar.add(new JLabel("Fill:"));

		innerColorChange = new JButton();
		innerColorChange.setBackground(drawing.getDrawingModel().getInnerColor());
		toolBar.add(innerColorChange);

		innerColorChange.addActionListener(arg0 -> drawing.innerColorChange());
		toolBar.add(new JSeparator());

		toolBar.add(new JLabel("Outline:"));

		colorChange = new JButton();
		colorChange.setBackground(drawing.getDrawingModel().getColor());
		toolBar.add(colorChange);

		colorChange.addActionListener(arg0 -> drawing.colorChange());
		toolBar.add(new JSeparator());

		btnToFront = new JButton("To Front");
		toolBar.add(btnToFront);
		btnToFront.setEnabled(false);
		btnToFront.addActionListener(arg0 -> drawing.toFront());

		btnToBack = new JButton("To Back");
		toolBar.add(btnToBack);
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(arg0 -> drawing.toBack());

		btnBringToFront = new JButton("Bring to Front");
		toolBar.add(btnBringToFront);
		btnBringToFront.setEnabled(false);
		btnBringToFront.addActionListener(arg0 -> drawing.bringToFront());

		btnBringToBack = new JButton("Bring to Back");
		toolBar.add(btnBringToBack);
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(arg0 -> drawing.bringToBack());


		toolBar.add(new JSeparator());

		btnOpen = new JButton("Open");
		toolBar.add(btnOpen);
		btnOpen.addActionListener(e -> {
			drawing.open();
		});

		btnSave = new JButton("Save");
		toolBar.add(btnSave);
		btnSave.addActionListener(e -> {
			drawing.save();
		});

		toolBar.add(new JSeparator());

		btnSeeLog = new JButton("Logs");
		toolBar.add(btnSeeLog);
		btnSeeLog.addActionListener(e -> {
			StringBuilder sb = new StringBuilder();
			for(String log : logger.getLogs()) {
				sb.append(log).append("\n");
			}
			JOptionPane.showMessageDialog(drawing, sb.toString(), "Logs", JOptionPane.INFORMATION_MESSAGE);
		});

		JPanel panel = new JPanel();
		panel.setBackground(new Color(205, 92, 92));
		contentPane.add(panel, BorderLayout.SOUTH);

		btnModify = new JButton("Modify");
		btnModify.addActionListener(arg0 -> drawing.modify());
		panel.add(btnModify);
		btnModify.setEnabled(false);

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
		btnDelete.setEnabled(false);

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

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(final JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
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

	public JButton getBtnDo() {
		return btnDo;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public Logger getLogger() {
		return logger;
	}

	@Override
	public void update(Object event) {
		if(event.equals(ObserverEvents.SELECTED)) {
			btnDelete.setEnabled(true);
			btnModify.setEnabled(true);
			btnToFront.setEnabled(true);
			btnToBack.setEnabled(true);
			btnBringToFront.setEnabled(true);
			btnBringToBack.setEnabled(true);
		} else if (event.equals(ObserverEvents.DESELECTED)) {
			btnDelete.setEnabled(false);
			btnModify.setEnabled(false);
			btnToFront.setEnabled(false);
			btnToBack.setEnabled(false);
			btnBringToFront.setEnabled(false);
			btnBringToBack.setEnabled(false);
		}
	}
}