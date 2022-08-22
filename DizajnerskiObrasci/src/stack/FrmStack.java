package stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmStack extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();
	JList listRectList = new JList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
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
	public FrmStack() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 304);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Jukic Maja IT5-2019");
		setResizable(false);
		setContentPane(contentPane);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(205, 92, 92));
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		//PUSH dugme - dodavanje na stek
		JButton btnPush = new JButton("PUSH");
		btnPush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = 0; 
				DlgStack dlg = new DlgStack();
				dlg.setTitle("Add new rectangle");
				dlg.setVisible(true);
				if(dlg.isOk())
				{
					String x = dlg.getTxtX().getText().toString();
					int intX = Integer.parseInt(x);
					String y = dlg.getTxtY().getText().toString();
					int intY = Integer.parseInt(y);
					String width = dlg.getTxtWidth().getText().toString();
					int intWidth = Integer.parseInt(width);
					String height = dlg.getTxtHeight().getText().toString();
					int intHeight = Integer.parseInt(height);
					
					Rectangle r = new Rectangle(new Point(intX, intY), intWidth, intHeight);
					dlm.add(index, r);
					index = 0;
				}
			}
		});
		pnlSouth.add(btnPush);
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(205, 92, 92));
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		//POP dugme - za izuzimanje sa steka
		JButton btnPop = new JButton("POP");
		btnPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listRectList.getFirstVisibleIndex();
				try {
					DlgStack dlg = new DlgStack();	
					dlg.setTitle("Remove the rectangle");
					dlg.getTxtX().setText(String.valueOf(dlm.elementAt(index).getUpperLeftPoint().getX()));
					dlg.getTxtY().setText(String.valueOf(dlm.elementAt(index).getUpperLeftPoint().getY()));
					dlg.getTxtWidth().setText(String.valueOf(dlm.elementAt(index).getWidth()));
					dlg.getTxtHeight().setText(String.valueOf(dlm.elementAt(index).getHeight()));
					dlg.getTxtX().setEditable(false);
					dlg.getTxtY().setEditable(false);
					dlg.getTxtWidth().setEditable(false);
					dlg.getTxtHeight().setEditable(false);
					dlg.setVisible(true);
					if(dlg.isOk())
					{
						int question = JOptionPane.showConfirmDialog(null, "Are you sure?",
								"Rectangle remove", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(question==0)
							dlm.remove(index);
					}
					
				}
				catch (ArrayIndexOutOfBoundsException ex)
				{
					JOptionPane.showMessageDialog(null, "The list is empty!");
				}
				
			}
		});
		pnlNorth.add(btnPop);
		
		JScrollPane spScroll = new JScrollPane();
		contentPane.add(spScroll, BorderLayout.CENTER);
		
		
		listRectList.setBackground(new Color(192, 192, 192));
		spScroll.setViewportView(listRectList);
		listRectList.setModel(dlm);
	}

}
