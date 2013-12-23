package behaviour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;

import main.Puzzle;
import model.Node;
import jade.core.behaviours.OneShotBehaviour;

public class MoveBehaviour extends OneShotBehaviour{

	private static final long serialVersionUID = 1L;
	private Node nSaida = null;
	private ArrayList<Node> path = new ArrayList<Node>();
	private Puzzle agent = null;
	private String lS = "----------------------------------------------------";
	private ListIterator<Node> li = null;
	private boolean prev = false, next = false; 
	private int cont = 0;
	private JLabel step = new JLabel();
	
	public MoveBehaviour(Puzzle agent, Node nSaida){
		this.nSaida = nSaida;
		this.agent = (Puzzle) agent;
		this.li = (ListIterator<Node>) path.listIterator();
	}

	@Override
	public void action() {
		createPath(nSaida);
		JButton btnSolve = agent.gui.getBtnSolve();
		btnSolve.setVisible(true);
		JButton btnStep = agent.gui.getBtnStep();
		btnStep.setVisible(true);
		JButton btnStep_ = agent.gui.getBtnStep_();
		btnStep_.setVisible(true);
		JLabel steps = agent.gui.getSteps();
		steps.setText(steps.getText()+(path.size()-1));
		steps.setVisible(true);
		
		step = agent.gui.getStep();
		step.setVisible(true);

		this.li = (ListIterator<Node>) path.listIterator();
		
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Timer timer = new Timer();
				TimerTask tarefa = new TimerTask(){
					public void run()     
					{  
						if(!li.hasNext()){
							cancel();
						}else{
							Node atual = li.next();
							try{
								cont++;
								if(cont < path.size()){
									step.setText("Step: "+cont);
								}
								agent.gui.setOutput(new int[3][3]);
								agent.gui.setOutput(atual.getState());
							} catch (Exception e) {        
								e.printStackTrace();        
							}
						}
					}
				};
				timer.schedule(tarefa, 0,1000);
			}
		});

		printNext();
		btnStep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
				if(cont < (path.size()-1))
					cont++;
				step.setText("Step: "+cont);
				printNext();
			}
		});

		btnStep_.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
				if(cont > 0)
					cont--;
				step.setText("Step: "+cont);
				printPrevious();
			}
		});
	}

	public void printNext(){
		if(this.li.hasNext()){
			if(prev)
			{
				this.li.next();
				prev = false;
			}
			Node atual = this.li.next();
			agent.gui.setOutput(atual.getState());
			next = true;
		}
	}

	public void printPrevious(){
		if(this.li.hasPrevious()){
			if(next){
				this.li.previous();
				this.next = false;
			}
			Node atual = this.li.previous();
			agent.gui.setOutput(atual.getState());
			prev = true;
		}
	}
	public void createPath(Node a)
	{
		if (a.getParent() != null)
			createPath(a.getParent());

		this.path.add(a);
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	/**
	 * Print the reconstruct path of solution 
	 * @param lastNode 
	 */
	 public void reconstructSuccess(Node lastNode){
		System.out.println("#SUCCESS");
		System.out.println(lS);
		System.out.println("Nodes explored: "+agent.explorado.size());
		int nivel = 0;
		String string = "";
		while(lastNode != null){
			if(lastNode.getParentAction() != 'N'){
				string = "Action: "+lastNode.getParentAction()+" ==> State: "+lastNode.stateToString()+"\n"+string;
				nivel++;
			}else{
				string = "StateI: "+lastNode.stateToString()+"\n"+string;
			}
			lastNode = lastNode.getParent();
		}
		System.out.println("Depth: "+nivel);
		System.out.println(lS);
		System.out.println(string);
	 }	
}
