package main;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.util.Iterator;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class Puzzle extends Agent {

	protected void setup() {
		System.out.println( " Hello World . Eu sou um agente !" ) ;
		System.out.println( " Todas as minhas informacoes : \n" + getAID());
		System.out.println( "Meu nome local eh "+ getAID().getLocalName());
		System.out.println( "Meu nome global (GUID)  eh  "+ getAID().getName());
		System.out.println( "Meus endereços são: ") ;
		
		Iterator it = getAID().getAllAddresses();
		
		while(it.hasNext()){
			System.out.println("- "+it.next());
		}

		// Adicionando novo behaviour ao agente
		this.addBehaviour(new StartBehaviour(this));
	}
}

private class StartBehaviour extends OneShotBehaviour()
{
	// Construtor para setar a variavel myAgent (representação do agente no behaviour)
	public StartBehaviour(Agent a)
	{
		this.myAgent = a;
	}

	public void action() 
	{
		// Cria um template de mensagem (baseado no Performative INFORM)
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		
		// Loop ate que uma mensagem do tipo INFORM seja recebida pelo agente
		do
		{
			ACLMessage msg = receive(mt);
		}while (msg != null)

		// Adição do primeiro behaviour de processamento do agente
		// myAgent.addBehaviour(new xxxxxBehaviour())
	}
}