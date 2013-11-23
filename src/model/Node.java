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
	private String parentAction; //Action executed by parent for to arrive the this node
	private int cost; //sum of costs to the current state
	
	/** Constructors **/
	
	public Node(){
		this.state = new int[3][3];
		this.parent = null;
		this.parentAction = null;
		this.cost = 0;
	}

	public Node(int[][] state, Node parent, String parentAction, int cost) {
		super();
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

	public String getParentAction() {
		return parentAction;
	}

	public void setParentAction(String parentAction) {
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
		return "Node [state=" + Arrays.toString(state) + ", parent=" + parent
				+ ", parentAction=" + parentAction + ", cost=" + cost + "]";
	}
	
}
