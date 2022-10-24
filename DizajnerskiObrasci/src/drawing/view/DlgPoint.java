package drawing.view;

import drawing.model.Point;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnOk;
	private JButton btnCancel;
	private JLabel lblX;
	private JTextField txtX;
	private JTextField txtY;
	private boolean isOk;
	private Color color;
	private JButton btnOutline;
	private Point p;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPoint() {
		setBounds(100, 100, 337, 329);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(220, 220, 220));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Draw/Modify point");
		setResizable(false);
		setModal(true);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblX = new JLabel("X coordinate:");
		}

		JLabel lblY = new JLabel("Y coorditane:");

		txtX = new JTextField();
		txtX.setColumns(10);

		txtY = new JTextField();
		txtY.setColumns(10);
		JButton btnOutline = new JButton("OUTLINE");
		btnOutline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Choose a color", null);
				btnOutline.setBackground(color);
			}
		});

		JDesktopPane desktopPane = new JDesktopPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPanel.createSequentialGroup().addContainerGap(36, Short.MAX_VALUE).addGroup(gl_contentPanel
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblX)
												.addComponent(lblY))
										.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(
										gl_contentPanel.createSequentialGroup()
												.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1,
														GroupLayout.PREFERRED_SIZE)
												.addGap(112)))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(18).addComponent(txtY,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(23).addComponent(txtX,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnOutline)))
						.addGap(63)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(desktopPane,
										GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING,
										gl_contentPanel.createSequentialGroup().addGap(14)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblX).addComponent(txtX, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(44)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblY).addComponent(txtY, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
												.addComponent(btnOutline)))
						.addGap(41)));
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

							if (getTxtX().getText().trim().isEmpty() || getTxtY().getText().trim().isEmpty()) {
								isOk = false;
								getToolkit().beep();
								setVisible(true);
								JOptionPane.showMessageDialog(null, "You need to enter all values!");
							} else if (Integer.parseInt(getTxtX().getText().toString()) <= 0
									|| Integer.parseInt(getTxtY().getText().toString()) <= 0) {
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
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
					gl_buttonPane.createParallelGroup(Alignment.LEADING).addGroup(gl_buttonPane.createSequentialGroup()
							.addGap(23).addComponent(btnOk).addGap(151).addComponent(btnCancel).addGap(29)));
			gl_buttonPane
					.setVerticalGroup(
							gl_buttonPane.createParallelGroup(Alignment.LEADING)
									.addGroup(Alignment.TRAILING,
											gl_buttonPane.createSequentialGroup()
													.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
															.addComponent(btnOk).addComponent(btnCancel))
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
		this.btnOutline = btnOutline;
	}

	public void setPoint(Point p) {
		this.p = p;

		txtX.setText(String.valueOf(p.getX()));
		txtY.setText(String.valueOf(p.getY()));
		this.color = p.getColor();

	}

}