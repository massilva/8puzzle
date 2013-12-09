package main;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

import java.util.ArrayList;
import java.util.List;

import model.Node;
import behaviour.ThinkBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class Puzzle extends Agent{
	private int [][] entrada;
	private PuzzleGui gui = null;	
	private List <Node> fronteira, explorado;
	
	protected void setup(){
		this.fronteira = new ArrayList<Node>();
		this.explorado = new ArrayList<Node>();
		gui = new PuzzleGui(this);
		gui.showGui();
	}
	
	public void start()
	{
		Node nEntrada = new Node(this.entrada, null,'N', 0);
		this.addBehaviour(new StartBehaviour(this,nEntrada,this.fronteira,this.explorado));
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

	public class StartBehaviour extends SimpleBehaviour
	{
		private boolean end;
		private Node nEntrada;
		private List<Node>fronteira, explorado;
		
		// Construtor para setar a variavel myAgent (representação do agente no behaviour)
		public StartBehaviour(Agent a, Node nEntrada, List<Node>fronteira, List<Node>explorado)
		{
			this.myAgent = (Puzzle)a;
			this.nEntrada = nEntrada;
			this.fronteira = fronteira;
			this.explorado = explorado;
			end = false;
		}
	
		public void action() 
		{
			// Cria um template de mensagem (baseado no Performative INFORM)
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
			ACLMessage msg = blockingReceive(mt);
			
			if (msg != null)
				end = true;
		}

		@Override
		public boolean done(){
			return end;
		}
		
		@Override
		public int onEnd()
		{
			// Adição do primeiro behaviour de processamento do agente
			addBehaviour(new ThinkBehaviour(this.myAgent,this.nEntrada,this.fronteira,this.explorado));
			return 0;
		}
		
	}
}