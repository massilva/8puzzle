package model;

import java.util.Arrays;

/**
 * 
 * This class ...
 * 
 */
public class Node {
	private int [][] state; //state who this node represents 
	private Node parent; //node to which this node is descended
	private char parentAction; //Action executed by parent for to arrive the this node
	private int cost; //sum of costs to the current state
	
	/** Constructors **/
	public Node(){
		this.state = new int[3][3];
		this.parent = null;
		this.parentAction = 'N';
		this.cost = 0;
	}

	public Node(int[][] state, Node parent, char parentAction, int cost) {
		this.state = state;
		this.parent = parent;
		this.parentAction = parentAction;
		this.cost = cost;
	}

	/** SETs and GETs **/
	
	public int[][] getState() {
		return state;
	}

	public void setState(int[][] state) {
		this.state = state;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public char getParentAction() {
		return parentAction;
	}

	public void setParentAction(char parentAction) {
		this.parentAction = parentAction;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString(){
		String st = "";
		for (int [] obj : state){
			st += Arrays.toString(obj);
		}
		
		return "Node [state=" + st + ", parent=" + parent
				+ ", parentAction=" + parentAction + ", cost=" + cost + "]";
	} 
	
	@Override
	public boolean equals(Object o){
		Node node = (Node)o;
		boolean resultado = (node.getCost() == this.cost);
		if(node.getParent() == null){
			resultado = resultado && node.getParent() == this.parent;
		}else{
			resultado = resultado && node.getParent().equals(this.parent);
		}
		resultado =  resultado && (node.getParentAction() == this.parentAction);
		int [][] estado = node.getState();
		int i = 0, j = 0;
		if(resultado){
			while((i < estado.length) && (estado[i][j] == state[i][j])){
				while((j < estado.length) && (estado[i][j] == state[i][j])){
					j++;
				}
				if(j == estado.length){
					i++;
					j = 0;
				}
				else{
					break;
				}
			}
		}
		return (resultado) && (i == estado.length) && (j == 0);
	}
}
