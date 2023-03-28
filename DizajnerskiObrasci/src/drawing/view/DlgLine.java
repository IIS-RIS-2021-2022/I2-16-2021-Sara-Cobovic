package drawing.view;

import drawing.model.Line;

import java.awt.BorderLayout;

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

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnOk;
	private JButton btnCancel;
	private JLabel lblStartpointXCoordinate;
	private JTextField txtStartX;
	private JTextField txtStartY;
	private JTextField txtEndX;
	private JTextField txtEndY;
	private boolean isOk;
	private Color color;
	private JButton btnOutline;
	private Line l;

	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setBounds(100, 100, 354, 301);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(220, 220, 220));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Draw/Modify line");
		setResizable(false);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblStartpointXCoordinate = new JLabel("Startpoint X coordinate:");
		}
		JLabel lblStartpointYCoordinate = new JLabel("Startpoint Y coordinate:");
		JLabel lblNewLabel = new JLabel("Endpoint X coordinate:");
		JLabel lblEndpointYCoordinate = new JLabel("Endpoint Y coordinate:");
		txtStartX = new JTextField();
		txtStartX.setColumns(10);
		txtStartY = new JTextField();
		txtStartY.setColumns(10);
		txtEndX = new JTextField();
		txtEndX.setColumns(10);
		txtEndY = new JTextField();
		txtEndY.setColumns(10);
		JButton btnOutline = new JButton("OUTLINE");
		btnOutline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Choose a color", null);
				btnOutline.setBackground(color);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(35)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel)
														.addGap(18).addComponent(txtEndX, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
																.addComponent(lblStartpointXCoordinate)
																.addComponent(lblStartpointYCoordinate))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(txtStartY, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(txtStartX, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblEndpointYCoordinate).addGap(18).addComponent(txtEndY,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(120).addComponent(btnOutline)))
						.addContainerGap(90, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addGap(25)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblStartpointXCoordinate)
						.addComponent(txtStartX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblStartpointYCoordinate)
						.addComponent(txtStartY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel
						.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(txtEndX,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblEndpointYCoordinate)
						.addComponent(txtEndY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(btnOutline).addContainerGap(31, Short.MAX_VALUE)));
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
							if (getTxtStartX().getText().trim().isEmpty() || getTxtEndX().getText().trim().isEmpty()
									|| getTxtStartY().getText().trim().isEmpty()
									|| getTxtEndY().getText().trim().isEmpty()) {
								isOk = false;
								getToolkit().beep();
								setVisible(true);
								JOptionPane.showMessageDialog(null, "You need to enter all values!");
							} else if (Integer.parseInt(getTxtStartX().getText().toString()) <= 0
									|| Integer.parseInt(getTxtStartY().getText().toString()) <= 0
									|| Integer.parseInt(getTxtEndX().getText().toString()) <= 0
									|| Integer.parseInt(getTxtEndY().getText().toString()) <= 0) {
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
							.addGap(48).addComponent(btnOk).addGap(143).addComponent(btnCancel).addGap(35)));
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

	public JTextField getTxtStartX() {
		return txtStartX;
	}

	public void setTxtStartX(JTextField txtStartX) {
		this.txtStartX = txtStartX;
	}

	public JTextField getTxtStartY() {
		return txtStartY;
	}

	public void setTxtStartY(JTextField txtStartY) {
		this.txtStartY = txtStartY;
	}

	public JTextField getTxtEndX() {
		return txtEndX;
	}

	public void setTxtEndX(JTextField txtEndX) {
		this.txtEndX = txtEndX;
	}

	public JTextField getTxtEndY() {
		return txtEndY;
	}

	public void setTxtEndY(JTextField txtEndY) {
		this.txtEndY = txtEndY;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public JButton getBtnOutline() {
		return btnOutline;
	}

	public void setBtnOutline(JButton btnColor) {
		this.btnOutline = btnColor;
	}

	public void setLine(Line l) {
		this.l = l;
		txtStartX.setText(String.valueOf(l.getStartPoint().getX()));
		txtStartY.setText(String.valueOf(l.getStartPoint().getY()));
		txtEndX.setText(String.valueOf(l.getEndPoint().getX()));
		txtEndY.setText(String.valueOf(l.getEndPoint().getY()));
		this.color = l.getColor();
	}

}