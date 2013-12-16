package behaviour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import javax.swing.JButton;

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
	
	public MoveBehaviour(Puzzle agent, Node nSaida){
		this.nSaida = nSaida;
		this.agent = (Puzzle) agent;
		this.li = (ListIterator<Node>) path.listIterator();
	}
	
	@Override
	public void action() {
		createPath(nSaida);
//		reconstructSuccess(nSaida);
		JButton btnSolve = agent.gui.getBtnSolve();
		btnSolve.setVisible(true);
		JButton btnStep = agent.gui.getBtnStep();
		btnStep.setVisible(true);
		JButton btnStep_ = agent.gui.getBtnStep_();
		btnStep_.setVisible(true);
		
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Iterator<Node> it = path.iterator();
				while(it.hasNext()){
					Node atual = it.next();
					agent.gui.setOutput(atual.getState());	
				}
			}
		});
		
		this.li = (ListIterator<Node>) path.listIterator();
		printNext();
		
		btnStep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
				printNext();
			}
		});
		
		btnStep_.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
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
