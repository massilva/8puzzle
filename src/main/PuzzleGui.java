package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import model.Node;
import util.Utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private JButton btnStep;
	private JButton btnSolve;
	private JButton btnStep_;
	private JButton btnBegin;
	private JLabel steps, step;
	
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public PuzzleGui(Puzzle a) throws ParseException{
		myAgent = a;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		input0 = new JFormattedTextField(new MaskFormatter("#"));
		input0.setHorizontalAlignment(SwingConstants.CENTER);
		input0.setBounds(0, 10, 50, 50);
		contentPane.add(input0);
		input0.setColumns(1);

		input1 = new JFormattedTextField(new MaskFormatter("#"));
		input1.setHorizontalAlignment(SwingConstants.CENTER);
		input1.setColumns(10);
		input1.setBounds(50, 10, 50, 50);
		contentPane.add(input1);

		input2 = new JFormattedTextField(new MaskFormatter("#"));
		input2.setHorizontalAlignment(SwingConstants.CENTER);
		input2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				input3.grabFocus();
				input3.requestFocus();
			}
		});
		input2.setColumns(10);
		input2.setBounds(100, 10, 50, 50);
		contentPane.add(input2);

		input3 = new JFormattedTextField(new MaskFormatter("#"));
		input3.setHorizontalAlignment(SwingConstants.CENTER);
		input3.setColumns(10);
		input3.setBounds(0, 60, 50, 50);
		contentPane.add(input3);

		input4 = new JFormattedTextField(new MaskFormatter("#"));
		input4.setHorizontalAlignment(SwingConstants.CENTER);
		input4.setColumns(10);
		input4.setBounds(50, 60, 50, 50);
		contentPane.add(input4);

		input5 = new JFormattedTextField(new MaskFormatter("#"));
		input5.setHorizontalAlignment(SwingConstants.CENTER);
		input5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				input6.grabFocus();
				input6.requestFocus();
			}
		});
		input5.setColumns(10);
		input5.setBounds(100, 60, 50, 50);
		contentPane.add(input5);

		input6 = new JFormattedTextField(new MaskFormatter("#"));
		input6.setHorizontalAlignment(SwingConstants.CENTER);
		input6.setColumns(10);
		input6.setBounds(0, 110, 50, 50);
		contentPane.add(input6);

		input7 = new JFormattedTextField(new MaskFormatter("#"));
		input7.setHorizontalAlignment(SwingConstants.CENTER);
		input7.setColumns(10);
		input7.setBounds(50, 110, 50, 50);
		contentPane.add(input7);

		input8 = new JFormattedTextField(new MaskFormatter("#"));
		input8.setHorizontalAlignment(SwingConstants.CENTER);
		input8.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				btnBegin.grabFocus();
				btnBegin.requestFocus();
			}
		});
		input8.setColumns(10);
		input8.setBounds(100, 110, 50, 50);
		contentPane.add(input8);
		
		btnBegin = new JButton("Begin");
		btnBegin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
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
					myAgent.start();
					btnBegin.setText("New");
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		btnBegin.addActionListener(new ActionListener() {
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
					myAgent.start();
					btnBegin.setText("New");
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		btnBegin.setBounds(160, 40, 80, 80);
		contentPane.add(btnBegin);			
		
		output1 = new JTextField();
		output1.setHorizontalAlignment(SwingConstants.CENTER);
		output1.setEditable(false);
		output1.setColumns(10);
		output1.setBounds(300, 10, 50, 50);
		contentPane.add(output1);

		output2 = new JTextField();
		output2.setHorizontalAlignment(SwingConstants.CENTER);
		output2.setEditable(false);
		output2.setColumns(10);
		output2.setBounds(350, 10, 50, 50);
		contentPane.add(output2);

		output0 = new JTextField();
		output0.setHorizontalAlignment(SwingConstants.CENTER);
		output0.setEditable(false);
		output0.setColumns(10);
		output0.setBounds(250, 10, 50, 50);
		contentPane.add(output0);

		output3 = new JTextField();
		output3.setHorizontalAlignment(SwingConstants.CENTER);
		output3.setEditable(false);
		output3.setColumns(10);
		output3.setBounds(250, 60, 50, 50);
		contentPane.add(output3);

		output4 = new JTextField();
		output4.setHorizontalAlignment(SwingConstants.CENTER);
		output4.setEditable(false);
		output4.setColumns(10);
		output4.setBounds(300, 60, 50, 50);
		contentPane.add(output4);

		output5 = new JTextField();
		output5.setHorizontalAlignment(SwingConstants.CENTER);
		output5.setEditable(false);
		output5.setColumns(10);
		output5.setBounds(350, 60, 50, 50);
		contentPane.add(output5);

		output6 = new JTextField();
		output6.setHorizontalAlignment(SwingConstants.CENTER);
		output6.setEditable(false);
		output6.setColumns(10);
		output6.setBounds(250, 110, 50, 50);
		contentPane.add(output6);

		output7 = new JTextField();
		output7.setHorizontalAlignment(SwingConstants.CENTER);
		output7.setEditable(false);
		output7.setColumns(10);
		output7.setBounds(300, 110, 50, 50);
		contentPane.add(output7);

		output8 = new JTextField();
		output8.setHorizontalAlignment(SwingConstants.CENTER);
		output8.setEditable(false);
		output8.setColumns(10);
		output8.setBounds(350, 110, 50, 50);
		contentPane.add(output8);

		btnStep = new JButton(">");
		btnStep.setVisible(false);
		btnStep.setBounds(331, 165, 45, 23);
		contentPane.add(btnStep);

		btnSolve = new JButton("Solve");
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSolve.setVisible(false);
		btnSolve.setBounds(160, 165, 80, 23);
		contentPane.add(btnSolve);

		btnStep_ = new JButton("<");
		btnStep_.setVisible(false);
		btnStep_.setBounds(276, 165, 45, 23);
		contentPane.add(btnStep_);
		
		steps = new JLabel("Total steps: ");
		steps.setVisible(false);
		steps.setBounds(10, 165, 120, 23);
		contentPane.add(steps);

		step = new JLabel();
		step.setVisible(false);
		step.setBounds(160, 120, 80, 23);
		contentPane.add(step);
		
	}

	public void showGui(){
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		this.setSize(418, 230);		
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
	public JButton getBtnStep() {
		return btnStep;
	}
	public JButton getBtnSolve() {
		return btnSolve;
	}
	public JButton getBtnStep_() {
		return btnStep_;
	}	
	public JLabel getSteps() {
		return steps;
	}
	public JLabel getStep() {
		return step;
	}

	public void colorize(Node node,char action){
		Utils util = new Utils();
		int col = util.getTileEmpty(node.getState()).getI();
		int ln = util.getTileEmpty(node.getState()).getJ();
		
		setColor(col, ln, Color.WHITE);
		switch (action){
		case 'L':
			ln--;
			break;
		case 'R':
			ln++;
			break;
		case 'U':
			col--;
			break;
		case 'D':
			col++;
			break;
		}
		setColor(col, ln, Color.GREEN);
	}

	private void setColor(int col, int ln, Color color){
		if(col == 0){
			if(ln == 0){
				output0.setBackground(color);
			}else if(ln == 1){
				output1.setBackground(color);
			}else{
				output2.setBackground(color);
			}
		}else if(col == 1){
			if(ln == 0){
				output3.setBackground(color);
			}else if(ln == 1){
				output4.setBackground(color);
			}else{
				output5.setBackground(color);
			}
		}else{
			if(ln == 0){
				output6.setBackground(color);
			}else if(ln == 1){
				output7.setBackground(color);
			}else{
				output8.setBackground(color);
			}
		}

	}
}
