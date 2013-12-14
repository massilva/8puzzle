package behaviour;

import main.Puzzle;
import model.Node;
import jade.core.behaviours.OneShotBehaviour;

public class MoveBehaviour extends OneShotBehaviour{

	private static final long serialVersionUID = 1L;
	private Node nSaida = null;
	private Puzzle agent = null;
	private String lS = "----------------------------------------------------";
		
	public MoveBehaviour(Puzzle agent, Node nSaida){
		this.nSaida = nSaida;
		this.agent = (Puzzle) agent;
	}
	
	@Override
	public void action() {
		reconstructSuccess(nSaida);
		agent.gui.setOutput(nSaida.getState());
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
