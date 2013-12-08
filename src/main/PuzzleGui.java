package main;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;


@SuppressWarnings("serial")
public class PuzzleGui extends JFrame {

	private JPanel contentPane;
	private JTextField input0;
	private JTextField input1;
	private JTextField input2;
	private JTextField input3;
	private JTextField input4;
	private JTextField input5;
	private JTextField input6;
	private JTextField input7;
	private JTextField input8;
	private Puzzle myAgent;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PuzzleGui frame = new PuzzleGui();
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
	public PuzzleGui(Puzzle a) {
		
		myAgent = a;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		Canvas canvas = new Canvas();
		canvas.setBounds(160, 10, 150, 150);
		contentPane.add(canvas);
		
		input0 = new JTextField();
		input0.setBounds(0, 10, 50, 50);
		contentPane.add(input0);
		input0.setColumns(10);
		
		input1 = new JTextField();
		input1.setColumns(10);
		input1.setBounds(50, 10, 50, 50);
		contentPane.add(input1);
		
		input2 = new JTextField();
		input2.setColumns(10);
		input2.setBounds(100, 10, 50, 50);
		contentPane.add(input2);
		
		input3 = new JTextField();
		input3.setColumns(10);
		input3.setBounds(0, 60, 50, 50);
		contentPane.add(input3);
		
		input4 = new JTextField();
		input4.setColumns(10);
		input4.setBounds(50, 60, 50, 50);
		contentPane.add(input4);
		
		input5 = new JTextField();
		input5.setColumns(10);
		input5.setBounds(100, 60, 50, 50);
		contentPane.add(input5);
		
		input6 = new JTextField();
		input6.setColumns(10);
		input6.setBounds(0, 110, 50, 50);
		contentPane.add(input6);
		
		input7 = new JTextField();
		input7.setColumns(10);
		input7.setBounds(50, 110, 50, 50);
		contentPane.add(input7);
		
		input8 = new JTextField();
		input8.setColumns(10);
		input8.setBounds(100, 110, 50, 50);
		contentPane.add(input8);
		
		JButton StartGame = new JButton("Begin");
		StartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int[][] entrada = new int[3][3];
				
				entrada[0][0] = getInput0();
				entrada[0][1] = getInput1();
				entrada[0][2] = getInput2();
				entrada[1][0] = getInput3();
				entrada[1][1] = getInput4();
				entrada[1][2] = getInput5();
				entrada[2][0] = getInput6();
				entrada[2][1] = getInput7();
				entrada[2][2] = getInput8();
				
				myAgent.setEntrada(entrada);
				myAgent.showEntrada();
				myAgent.start();
			}
		});
		StartGame.setBounds(110, 168, 89, 23);
		contentPane.add(StartGame);			
	}
	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		this.setSize(330, 230);		
		super.setVisible(true);
	}
	
	public int getInput8() {
		return Integer.parseInt(input8.getText());
	}
	public int getInput3() {
		return Integer.parseInt(input3.getText());
	}
	public int getInput4() {
		return Integer.parseInt(input4.getText());
	}
	public int getInput5() {
		return Integer.parseInt(input5.getText());
	}
	public int getInput7() {
		return Integer.parseInt(input7.getText());
	}
	public int getInput2() {
		return Integer.parseInt(input2.getText());
	}
	public int getInput6() {
		return Integer.parseInt(input6.getText());
	}
	public int getInput0() {
		return Integer.parseInt(input0.getText());
	}
	public int getInput1() {
		return Integer.parseInt(input1.getText());
	}
}
