package main;

import jade.core.Agent;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Node;
import behaviour.ThinkBehaviour;

@SuppressWarnings("serial")
public class Puzzle extends Agent{
	private int [][] entrada;
	public PuzzleGui gui = null;	
	public List <Node> fronteira, explorado;
	
	protected void setup(){
		try {
			gui = new PuzzleGui(this);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gui.showGui();
		// test;
	}
	
	public void start()
	{
		this.fronteira = new ArrayList<Node>();
		this.explorado = new ArrayList<Node>();
		Node nEntrada = new Node(this.entrada, null,'N', 0);
		this.addBehaviour(new ThinkBehaviour(this,nEntrada));		
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