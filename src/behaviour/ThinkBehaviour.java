package behaviour;

import java.util.ArrayList;
import java.util.Iterator;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import main.Puzzle;
import model.Node;
import model.Position;

import java.util.List;

public class ThinkBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 1L;
	private final int COST = 1; //Custo Constant
	private Node nEntrada; // Nó de entrada
	private List<Node>fronteira, explorado;
	
	public ThinkBehaviour(){
		
	}
	
	public ThinkBehaviour(Agent agent, Node nEntrada, List<Node>fronteira, List<Node>explorado){
		this.myAgent = (Puzzle)agent;
		this.nEntrada = nEntrada;
		this.fronteira = fronteira;
		this.explorado = explorado;
	}

	@Override
	public void action(){
		//Verificando se a entrada passada é um estado objetivo
		if(!this.isObjetiveState(this.nEntrada.getState())){
			addInOrderByManhattanDistance(this.fronteira,this.nEntrada);
			this.explorado = new ArrayList<Node>();
			if(!this.fronteira.isEmpty()){
				Node atual = nEntrada; 
				while(!this.fronteira.isEmpty()){
					char [] acoes = this.getAvailableAction(atual.getState());
					List<Node> possibilidade = this.getAllResult(acoes,atual);
					this.fronteira.remove(atual);
					for (Node node : possibilidade){
						addInOrderByManhattanDistance(this.fronteira,node);
					}
					this.explorado.add(atual);
					if(!this.fronteira.isEmpty()){
						atual = this.fronteira.get(0);
					}
				}
			}
			else{
				System.out.println("FAILURE");
			}
		}
		else{
			System.out.println("Solucao:");
			System.out.println(this.nEntrada);
		}
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
	public int manhattanDistance(int [][] state){
		int some = 0;
		//Assumes the order of number into state, instead of ascending order
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				if(state[i][j] != 0){ //DO NOT sum the default distance of empty space.
					Position positionDefault = getPositionDefault(state[i][j]);
					some += Math.abs(positionDefault.getI() - i) + Math.abs(positionDefault.getJ() - j);
				}
			}
		}
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
		}
		if(i == objetiveState.length && j == objetiveState.length){
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
		Position emBranco = getTileEmpty(state);
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
	 * @param state
	 * @return Position where is the empty tile 
	 */
	public Position getTileEmpty(int [][] state){
		int i = 0, j = 0;
		while(i < state.length){
			j = 0;
			while(j < state.length && state[i][j] != 0){
				j++;
			}
			if(j == state.length){
				i++;
			}
			else{
				break;
			}
		}
		return new Position(i,j);
	}
	
	/**
	 * 
	 * @param action
	 * @param nState
	 * @return Node resultant of to run action in nState if not exist in closed list 
	 */
	public Node result(char action, Node nState){
		Node node = new Node(), nEstado = new Node(nState.getState(),nState.getParent(),nState.getParentAction(),nState.getCost());
		node.setParent(nEstado);
		node.setCost(nEstado.getCost()+COST);
		node.setParentAction(action);
		
		Position emBranco = getTileEmpty(nEstado.getState());
		Position auxPosition = emBranco;
		switch (action){
		case 'L':
			auxPosition = new Position(emBranco.getI(),emBranco.getJ()-1);
			break;
		case 'R':
			auxPosition = new Position(emBranco.getI(),emBranco.getJ()+1);
			break;
		case 'U':
			auxPosition = new Position(emBranco.getI()-1,emBranco.getJ());
			break;
		case 'D':
			auxPosition = new Position(emBranco.getI()+1,emBranco.getJ());
			break;
		}
		
		if(auxPosition.getI() >= 3 || auxPosition.getJ() >= 3){
			node = null;
		}else{
			int [][] estado = copy(nEstado.getState());
			int aux = estado[auxPosition.getI()][auxPosition.getJ()]; //get value in position where will be moved the empty tile. 
			estado[emBranco.getI()][emBranco.getJ()] = aux;
			estado[auxPosition.getI()][auxPosition.getJ()] = 0;
			node.setState(estado);
		}
		return inArray(this.explorado,node) ? null : node;
	}
	
	/**
	 * 
	 * @param actions
	 * @param nState
	 * @return a list of results when run the method Result with all the actions available 
	 */
	public List<Node> getAllResult(char [] actions, Node nState){
		List<Node> resultados = new ArrayList<Node>();
		for (char acao : actions){
			Node node = result(acao, nState);
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
		quick_sort(fronteira, 0, fronteira.size()-1);
	}
	
	/**
	 * 
	 * @param lista
	 * @param ini
	 * @param fim
	 * @return a list order by cost
	 */
	public void quick_sort(List<Node>lista,int ini, int fim) {
	     int meio;	
	     if (ini < fim){
             meio = partition(lista, ini, fim);
             quick_sort(lista, ini, meio);
             quick_sort(lista, meio + 1, fim);
         }
	 }
	 
	 /**
	 * Helper Function
	 * @param lista
	 * @param ini
	 * @param fim
	 * @return
	 */
	 public int partition(List<Node>lista, int ini, int fim) {
         Node pivo;
         int topo, i;
         pivo = lista.get(ini);
         topo = ini;

         for (i = ini + 1; i <= fim; i++) {
             if(manhattanDistance(lista.get(i).getState()) < manhattanDistance(pivo.getState())) {
                 lista.set(topo,lista.get(i));
                 lista.set(i,lista.get(topo + 1));
                 topo++; 
             }
         }
         lista.set(topo,pivo);
         return topo;
	 }
	 
	 public boolean inArray(List<Node>lista, Node node){
		 if(lista != null){
			 Iterator<Node> it = lista.iterator();
			 Node no = (Node) it.next();
			 while (it.hasNext() && !no.equals(node)){
				no = (Node) it.next();
			 }
			 return no.equals(node);
		 }else{
			 return false;
		 }
	 }
	 
}
