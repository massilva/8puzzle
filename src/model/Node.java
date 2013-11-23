package model;

/**
 * 
 * @author massilva
 * This class ...
 */
public class Node {
	private int [][] state; //state who this node represents 
	private Node parent; //node to which this node is descended
	private String parentAction; //Action executed by parent for to arrive the this node
	private int cost; //sum of costs to the current state
	
}
