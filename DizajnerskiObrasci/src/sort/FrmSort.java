package sort;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class FrmSort extends JFrame {

	private JPanel contentPane;
	private final JButton btnSort = new JButton("SORT");
	JList listRectList = new JList();
	private DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
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
	public FrmSort() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 302);
		setTitle("Jukic Maja IT5-2019");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(119, 136, 153));
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 0));

		JScrollPane spScroll = new JScrollPane();
		pnlCenter.add(spScroll, BorderLayout.CENTER);

		listRectList.setBackground(new Color(192, 192, 192));
		spScroll.setViewportView(listRectList);

		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(205, 92, 92));
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// dugme Sort
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dlm.isEmpty()) {
					Rectangle[] rArray = new Rectangle[dlm.getSize()];
					for (int i = 0; i < rArray.length; i++)
						rArray[i] = dlm.getElementAt(i);
					
					Arrays.sort(rArray);
					dlm.clear(); 
					for (int i = 0; i < rArray.length; i++)
						dlm.addElement(rArray[i]);
				} else {
					JOptionPane.showMessageDialog(null, "The list is empty!");
				}
			}
		});

		pnlSouth.add(btnSort);

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(205, 92, 92));
		contentPane.add(pnlNorth, BorderLayout.NORTH);

		// dugme Add
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgAdd dlg = new DlgAdd();
				dlg.setVisible(true);
				if (dlg.getisOk()) {
					String x = dlg.getTxtX().getText().toString();
					int intX = Integer.parseInt(x);
					String y = dlg.getTxtY().getText().toString();
					int intY = Integer.parseInt(y);
					String height = dlg.getTxtHeight().getText().toString();
					int intHeight = Integer.parseInt(height);
					String width = dlg.getTxtWidth().getText().toString();
					int intWidth = Integer.parseInt(width);

					Rectangle r = new Rectangle(new Point(intX, intY), intWidth, intHeight);
					dlm.addElement(r);
				}
			}
		});
		pnlNorth.add(btnAdd);
		listRectList.setModel(dlm);// bindovanje modela dlm u JListu! Lista se vidi graficki, ali svaka izmena se
									// izvrsava nad modelom i zato moramo ovo spojiti
	}

}
