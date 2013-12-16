package behaviour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import main.Puzzle;
import model.Node;
import model.Position;

import java.util.List;

import util.Utils;

public class ThinkBehaviour extends OneShotBehaviour{

	private static final long serialVersionUID = 1L;
	private final int COST = 1; //Custo Constant
	private Node nEntrada; // No de entrada
	private Puzzle agent;
	private String lS = "----------------------------------------------------";
	private Utils util = new Utils();

	public ThinkBehaviour(){

	}

	public ThinkBehaviour(Agent agent, Node nEntrada){
		this.myAgent = agent;
		this.nEntrada = nEntrada;
		this.agent = (Puzzle) agent;
	}

	@Override
	public void action(){

		int count = getNumberInversions(nEntrada.getState());
		if (count%2 == 0)
		{
			System.out.println(lS);
			System.out.println("Iniciado");
			System.out.println(lS);
			//Verificando se a entrada passada eh um estado objetivo
			if(!this.isObjetiveState(this.nEntrada.getState())){
				addInOrderByManhattanDistance(agent.fronteira,this.nEntrada);
				agent.explorado = new ArrayList<Node>();
				if(!agent.fronteira.isEmpty()){
					Node atual = nEntrada;
					while(!agent.fronteira.isEmpty() && atual.getCost() <= 60){
						char [] acoes = this.getAvailableAction(atual.getState());
						List<Node> possibilidade = this.getAllResult(agent.fronteira,agent.explorado,acoes,atual);
						agent.fronteira.remove(atual);
						for(Node node : possibilidade){
							addInOrderByManhattanDistance(agent.fronteira,node);
						}
						agent.explorado.add(atual);
						if(!agent.fronteira.isEmpty()){
							atual = agent.fronteira.get(0);
						}
						if(this.isObjetiveState(atual.getState())){
							break;
						}
					}
					if(this.isObjetiveState(atual.getState())){
						myAgent.addBehaviour(new MoveBehaviour(agent, atual));						
					}else{
						System.out.println("#UNSOLVABLE");
						System.out.println(lS);
						if(atual.getCost() > 60){
							System.out.println("Depth is greater than 60.");
						}else{
							System.out.println(nEntrada.stateToString());
						}
					}
				}				
			}
			else{
				myAgent.addBehaviour(new MoveBehaviour(agent, nEntrada));
			}
		}
		else{
			System.out.println("#UNSOLVABLE");
			if(count%2!=0){
				System.out.println(lS);
				System.out.println("Number of inversions is odd: " + count);
			}
		}
	}

	public int getNumberInversions(int [][] entrada)
	{
		int count = 0;
		//		Contando o numero de inversoes na entrada fornecida		
		for (int i = 0; i < 9; i++)
			for (int j = i; j < 9; j++)
				if ((entrada[i/3][i%3] != 0) &&
						(entrada[j/3][j%3] != 0) && 
						(entrada[i/3][i%3] > entrada[j/3][j%3]))
				{
					count++;
				}
		return count;
	}

	/**
	 * @param number, you want to known to its default position
	 * @return Position containing this default position
	 * 
	 */
	public Position getPositionDefault(int number){
		Position position = new Position();
		int line = number/3;
		int col = number%3;
		position.setI(line);
		position.setJ(col);
		return position;
	}

	/**
	 * Without presentation
	 * @param state
	 * @return
	 */
	public int manhattanDistance(Node node){
		int some = 0;
		int [][] state = node.getState();
		//Assumes the order of number into state, instead of ascending order
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				if(state[i][j] != 0){ //DO NOT sum the default distance of empty space.
					Position positionDefault = getPositionDefault(state[i][j]);
					some += Math.abs(positionDefault.getI() - i) + Math.abs(positionDefault.getJ() - j);
				}
			}
		}
		some += node.getCost();
		return some;
	}

	/**
	 * 
	 * @param state 
	 * @return TRUE if @param state is the objetive state FALSE else
	 */
	public boolean isObjetiveState(int [][] state){
		int [][] objetiveState = {{0,1,2},{3,4,5},{6,7,8}};
		int  i = 0, j = 0;
		while(i < objetiveState.length){
			while(j < objetiveState.length && objetiveState[i][j] == state[i][j]){
				j++;
			}
			if(j != objetiveState.length){
				break;
			}
			i++;
			j=0;
		}
		if(i == objetiveState.length){
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param state 
	 * @return the actions available for @param state
	 */
	public char [] getAvailableAction(int [][] state){
		Position emBranco = util.getTileEmpty(state);
		//coluna
		if(emBranco.getJ() == 0){
			if(emBranco.getI() == 0){ //linha
				char [] acoes = {'R','D'};//direita, baixo;
				return acoes;
			}
			if(emBranco.getI() == 1){ //linha
				char [] acoes = {'R','D','U'};//direita, baixo e acima;
				return acoes;
			}
			if(emBranco.getI() == 2){//linha
				char [] acoes = {'R','U'};//direita e cima;
				return acoes;
			}
		}
		//coluna
		if(emBranco.getJ() == 1){
			if(emBranco.getI() == 0){ //linha
				char [] acoes = {'L','D','R'};//esquerda, baixo, direita;
				return acoes;
			}
			if(emBranco.getI() == 1){ //linha
				char [] acoes = {'U','L','R','D'};//cima, esquerda, direita, baixo;
				return acoes;
			}
			if(emBranco.getI() == 2){ //linha
				char [] acoes = {'L','U','R'};//esquerda, cima, direita;
				return acoes;
			}
		}
		//coluna
		if(emBranco.getJ() == 2){
			if(emBranco.getI() == 0){ //linha
				char [] acoes = {'L','D'};//esquerda, baixo;
				return acoes;
			}
			if(emBranco.getI() == 1){ //linha
				char [] acoes = {'U','L','D'};//cima, esquerda, baixo;
				return acoes;
			}
			if(emBranco.getI() == 2){ //linha
				char [] acoes = {'L','U'};//esquerda e cima;
				return acoes;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param action
	 * @param nState
	 * @return Node resultant of to run action in nState if not exist in closed list 
	 */
	public Node result(List<Node>fronteira,List<Node>explorado, char action, Node nState){
		Node node = new Node(), nEstado = new Node(nState.getState(),nState.getParent(),nState.getParentAction(),nState.getCost());
		node.setParent(nEstado);
		node.setCost(nEstado.getCost()+COST);
		node.setParentAction(action);

		Position emBranco = util.getTileEmpty(nEstado.getState());
		Position auxPosition = emBranco;
		switch (action){
		case 'R':
			auxPosition = new Position(emBranco.getI(),emBranco.getJ()+1);
			break;
		case 'L':
			auxPosition = new Position(emBranco.getI(),emBranco.getJ()-1);
			break;
		case 'D':
			auxPosition = new Position(emBranco.getI()+1,emBranco.getJ());
			break;
		case 'U':
			auxPosition = new Position(emBranco.getI()-1,emBranco.getJ());
			break;
		}

		if(auxPosition.getI() >= 3 || auxPosition.getJ() >= 3 || auxPosition.getI() < 0 || auxPosition.getJ() < 0){
			node = null;
		}else{
			int [][] estado = copy(nEstado.getState());
			int aux = estado[auxPosition.getI()][auxPosition.getJ()]; //get value in position where will be moved the empty tile. 
			estado[emBranco.getI()][emBranco.getJ()] = aux;
			estado[auxPosition.getI()][auxPosition.getJ()] = 0;
			node.setState(estado);
		}

		if(node == null)
			return null;

		return (explorado.contains(node)||fronteira.contains(node)) ? null : node;
	}

	/**
	 * 
	 * @param actions
	 * @param nState
	 * @return a list of results when run the method Result with all the actions available 
	 */
	public List<Node> getAllResult(List<Node>fronteira, List<Node>explorado, char [] actions, Node nState){
		List<Node> resultados = new ArrayList<Node>();
		for (char acao : actions){
			Node node = result(fronteira,explorado,acao, nState);
			if(node != null){
				resultados.add(node);
			}
		}
		return resultados;
	}

	/**
	 * 
	 * @param matriz
	 * @return a copy of matriz
	 */
	public int[][] copy(int[][]matriz){
		int [][] cp = new int[3][3];
		for (int i = 0; i < matriz.length; i++){
			for (int j = 0; j < matriz.length; j++){
				cp[i][j] = matriz[i][j];
			}
		}
		return cp;
	}

	/**
	 * 
	 * @param fronteira
	 * @param node
	 */
	public void addInOrderByManhattanDistance(List<Node>fronteira, Node node){
		fronteira.add(node);
		Collections.sort(fronteira, new Comparator<Node>(){ 
			@Override 
			public int compare(Node a, Node b){ 
				return manhattanDistance(a) - manhattanDistance(b); 
			}
		});
	}
}
