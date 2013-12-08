package main;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

import java.util.Iterator;

import model.Node;
import behaviour.ThinkBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class Puzzle extends Agent{
	private String lineSeparator = "\n------------------------------------------------";
	private int [][] entrada = {{4,1,3},{0,2,6},{7,5,8}};
	private PuzzleGui gui = null;	
		
	protected void setup(){
		System.out.println( " Hello World . Eu sou um agente !" ) ;
		System.out.println( " Todas as minhas informacoes : \n" + getAID());
		System.out.println( "Meu nome local eh "+ getAID().getLocalName());
		System.out.println( "Meu nome global (GUID)  eh  "+ getAID().getName());
		System.out.println(lineSeparator);
		System.out.println( "Meus endereços são: ") ;
		Iterator<?> it = getAID().getAllAddresses();
		while(it.hasNext()){
			System.out.println("- "+it.next());
		}
		System.out.println(lineSeparator);
		
		gui = new PuzzleGui(this);
		gui.showGui();		
	}
	
	public void start()
	{
		Node nEntrada = new Node(this.entrada, null,'N', 0);
		
		// Adicionando novo behaviour ao agente
		this.addBehaviour(new StartBehaviour(this,nEntrada));
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
		
		// Construtor para setar a variavel myAgent (representação do agente no behaviour)
		public StartBehaviour(Agent a, Node nEntrada)
		{
			this.myAgent = (Puzzle)a;
			this.nEntrada = nEntrada;
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
			addBehaviour(new ThinkBehaviour(this.myAgent,this.nEntrada));
			return 0;
		}		
	}
}