package behaviour;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import main.Puzzle;
import model.Nameless;

public class ThinkBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 1L;
	private int [][] entrada;
	private Nameless nl;
	
	public ThinkBehaviour(Agent agent, int [][] entrada){
		this.myAgent = (Puzzle)agent;
		this.entrada = entrada;
		this.nl = new Nameless();
	}

	@Override
	public void action(){
		if(!this.nl.isObjetiveState(this.entrada)){
			System.out.println("Pensando..."+myAgent.getAID().getName());
		}
	}

}
