package main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class PuzzleGui2 extends JFrame {

	private JPanel contentPane;
	private JTable tableOutput;
	private JButton btnNewButton;
	private Puzzle myAgent;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PuzzleGui2 frame = new PuzzleGui2();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public PuzzleGui2(Puzzle a) {
		
		myAgent = a;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 95);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTable tableInput = new JTable(new DefaultTableModel(
				new Object[][] {
								{null, null, null},
								{null, null, null},
								{null, null, null},
							   },	
				new String[] { "C1", "C2", "C3" } ) 
		{
	 		@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
	 				Integer.class, Integer.class, Integer.class
	 		};
	 		public Class<?> getColumnClass(int columnIndex) {
	 			return columnTypes[columnIndex];
	 		}
	 	});
		
		tableInput.setBounds(304, 0, 215, 48);
		 
		contentPane.add(tableInput, BorderLayout.WEST);
		
		tableOutput = new JTable();
		tableOutput.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"C1", "C2", "C3"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, Integer.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableOutput.setSize(215, 48);
		contentPane.add(tableOutput, BorderLayout.EAST);
		
		btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[][] entrada = new int[3][3];
				
				entrada[0][0] = (int) tableInput.getValueAt(0, 0);
				entrada[0][1] = (int) tableInput.getValueAt(0, 1);
				entrada[0][2] = (int) tableInput.getValueAt(0, 2);
				
				entrada[1][0] = (int) tableInput.getValueAt(1, 0);
				entrada[1][1] = (int) tableInput.getValueAt(1, 1);
				entrada[1][2] = (int) tableInput.getValueAt(1, 2);
				
				entrada[2][0] = (int) tableInput.getValueAt(2, 0);
				entrada[2][1] = (int) tableInput.getValueAt(2, 1);
				entrada[2][2] = (int) tableInput.getValueAt(2, 2);
				
				myAgent.setEntrada(entrada);
				myAgent.showEntrada();
				myAgent.start();
			}
		});
		btnNewButton.setLocation(225, 0);
		btnNewButton.setSize(69, 48);
		contentPane.add(btnNewButton, BorderLayout.CENTER);
	}
	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		this.setSize(535, 95);		
		super.setVisible(true);
	}
}