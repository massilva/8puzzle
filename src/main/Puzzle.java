package main;

import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;

import model.Node;
import behaviour.ThinkBehaviour;

@SuppressWarnings("serial")
public class Puzzle extends Agent{
	private int [][] entrada;
	private PuzzleGui gui = null;	
	private List <Node> fronteira, explorado;
	
	protected void setup(){
		gui = new PuzzleGui(this);
		gui.showGui();
	}
	
	public void start()
	{
		this.fronteira = new ArrayList<Node>();
		this.explorado = new ArrayList<Node>();
		Node nEntrada = new Node(this.entrada, null,'N', 0);
		this.addBehaviour(new ThinkBehaviour(this,nEntrada,this.fronteira,this.explorado));
	}
	
	public int[][] getEntrada()
	{
		return this.entrada;
	}
	
	public void setEntrada(int[][] e)
	{
		this.entrada = e;
	}
	
	public void showEntrada()
	{
		for (int i = 0; i < 9; i++)
			System.out.println(entrada[i/3][i%3]);
	}

}