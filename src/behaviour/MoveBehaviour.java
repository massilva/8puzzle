package behaviour;

import jade.core.behaviours.OneShotBehaviour;

public class MoveBehaviour extends OneShotBehaviour{

	private static final long serialVersionUID = 1L;

	@Override
	public void action() {
		System.out.println("Movendo...");
	}
	
}
