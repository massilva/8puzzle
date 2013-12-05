package main;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

import java.util.Iterator;

import behaviour.ThinkBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class Puzzle extends Agent{
	private String lineSeparator = "\n------------------------------------------------";
	private int [][] entrada = {{4,1,3},{0,2,6},{7,5,8}};
		
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
		
		// Adicionando novo behaviour ao agente
		this.addBehaviour(new StartBehaviour(this,this.entrada));
	}

	public class StartBehaviour extends SimpleBehaviour
	{
		private boolean end;
		private int [][] entrada;
		
		// Construtor para setar a variavel myAgent (representação do agente no behaviour)
		public StartBehaviour(Agent a, int [][] entrada)
		{
			this.myAgent = (Puzzle)a;
			this.entrada = entrada;
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
			// Adição do primeiro behaviour de processamento do agente
			addBehaviour(new ThinkBehaviour(this.myAgent,this.entrada));
			return end;
		}
		
		public int takeDown()
		{
			return 0;
		}
		
	}

}