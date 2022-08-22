package drawing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnOk;
	private JButton btnCancel;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private JButton btnOutline;
	private JButton btnFill;
	private Color color;
	private Color innerColor;
	private boolean isOk;
	private Circle c;
	protected boolean isClicked;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		setBounds(100, 100, 401, 304);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(220, 220, 220));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setTitle("Draw/Modify circle");
		setResizable(false);
		JLabel lblCenterXCoordinate = new JLabel("Center X coordinate:");
		JLabel lblCenterYCoordinate = new JLabel("Center Y coordinate:");
		JLabel lblRadius = new JLabel("Radius:");
		txtX = new JTextField();
		txtX.setColumns(10);
		txtY = new JTextField();
		txtY.setColumns(10);
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		JButton btnOutline = new JButton("OUTLINE");
		btnOutline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Choose a color", null);
				btnOutline.setBackground(color);
			}
		});
		JButton btnFill = new JButton("FILL");
		btnFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Choose a color", null);
				btnFill.setBackground(innerColor);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING,
						gl_contentPanel.createSequentialGroup().addContainerGap(290, Short.MAX_VALUE)
								.addComponent(btnFill).addGap(32))
				.addGroup(gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(32).addGroup(gl_contentPanel
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(13)
										.addComponent(lblCenterXCoordinate).addGap(18).addComponent(txtX,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnOutline)
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addComponent(lblCenterYCoordinate).addGap(18).addComponent(txtY,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)))))
						.addGroup(Alignment.TRAILING,
								gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(lblRadius)
										.addGap(18).addComponent(txtRadius, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(126, Short.MAX_VALUE)));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(40)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCenterXCoordinate))
								.addGap(30)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCenterYCoordinate))
								.addGap(24)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRadius))
								.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(btnFill)
										.addComponent(btnOutline))
								.addContainerGap()));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(205, 92, 92));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (getTxtX().getText().trim().isEmpty() || getTxtY().getText().trim().isEmpty()
									|| getTxtRadius().getText().trim().isEmpty()) {
								isOk = false;
								getToolkit().beep();
								setVisible(true);
								JOptionPane.showMessageDialog(null, "You need to enter all values!");
							} else if (Integer.parseInt(getTxtX().getText().toString()) <= 0
									|| Integer.parseInt(getTxtY().getText().toString()) <= 0
									|| Integer.parseInt(getTxtRadius().getText().toString()) <= 0) {
								JOptionPane.showMessageDialog(null, "You need to enter values greater than 0!");
							} else {
								isOk = true;
								dispose();
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "You need to enter numbers!");
						}
					}
				});
				btnOk.setActionCommand("OK");
				getRootPane().setDefaultButton(btnOk);
			}
			{
				btnCancel = new JButton("CANCEL");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
					gl_buttonPane.createParallelGroup(Alignment.LEADING).addGroup(gl_buttonPane.createSequentialGroup()
							.addGap(61).addComponent(btnOk).addGap(164).addComponent(btnCancel).addGap(48)));
			gl_buttonPane
					.setVerticalGroup(
							gl_buttonPane.createParallelGroup(Alignment.LEADING)
									.addGroup(Alignment.TRAILING,
											gl_buttonPane.createSequentialGroup()
													.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
															.addComponent(btnCancel).addComponent(btnOk))
													.addContainerGap()));
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public JButton getBtnOutline() {
		return btnOutline;
	}

	public void setBtnOutline(JButton btnOutline) {
		this.btnOutline = btnOutline;
	}

	public JButton getBtnFill() {
		return btnFill;
	}

	public void setBtnFill(JButton btnFill) {
		this.btnFill = btnFill;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public void setCircle(Circle c) {
		this.c = c;
		if (isClicked) {// za modify
			txtX.setText(String.valueOf(c.getCenter().getX()));
			txtY.setText(String.valueOf(c.getCenter().getY()));
			txtRadius.setText(String.valueOf(c.getRadius()));
			this.color = c.getColor();
			this.innerColor = c.getInnerColor();

		} else {
			txtX.setText(String.valueOf(c.getCenter().getX()));
			txtY.setText(String.valueOf(c.getCenter().getY()));
			txtRadius.setText("");

		}

	}

	public Circle getCircle() {
		return new Circle(new Point(Integer.parseInt(txtX.getText()), Integer.parseInt(txtY.getText())),
				Integer.parseInt(txtRadius.getText()), color, innerColor);
	}

}
