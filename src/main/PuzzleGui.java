package main;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JTextField output1;
	private JTextField output2;
	private JTextField output0;
	private JTextField output3;
	private JTextField output4;
	private JTextField output5;
	private JTextField output6;
	private JTextField output7;
	private JTextField output8;
	
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
				try{
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
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		StartGame.setBounds(110, 168, 89, 23);
		contentPane.add(StartGame);			
		
		output1 = new JTextField();
		output1.setColumns(10);
		output1.setBounds(214, 10, 50, 50);
		contentPane.add(output1);
		
		output2 = new JTextField();
		output2.setColumns(10);
		output2.setBounds(264, 10, 50, 50);
		contentPane.add(output2);
		
		output0 = new JTextField();
		output0.setColumns(10);
		output0.setBounds(164, 10, 50, 50);
		contentPane.add(output0);
		
		output3 = new JTextField();
		output3.setColumns(10);
		output3.setBounds(164, 60, 50, 50);
		contentPane.add(output3);
		
		output4 = new JTextField();
		output4.setColumns(10);
		output4.setBounds(214, 60, 50, 50);
		contentPane.add(output4);
		
		output5 = new JTextField();
		output5.setColumns(10);
		output5.setBounds(264, 60, 50, 50);
		contentPane.add(output5);
		
		output6 = new JTextField();
		output6.setColumns(10);
		output6.setBounds(164, 110, 50, 50);
		contentPane.add(output6);
		
		output7 = new JTextField();
		output7.setColumns(10);
		output7.setBounds(214, 110, 50, 50);
		contentPane.add(output7);
		
		output8 = new JTextField();
		output8.setColumns(10);
		output8.setBounds(264, 110, 50, 50);
		contentPane.add(output8);
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
	
	public void setOutput(int[][] o)
	{
		output0.setText(Integer.toString(o[0][0]));
		output1.setText(Integer.toString(o[0][1]));
		output2.setText(Integer.toString(o[0][2]));
		
		output3.setText(Integer.toString(o[1][0]));
		output4.setText(Integer.toString(o[1][1]));
		output5.setText(Integer.toString(o[1][2]));
		
		output6.setText(Integer.toString(o[2][0]));
		output7.setText(Integer.toString(o[2][1]));
		output8.setText(Integer.toString(o[2][2]));		
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
