package main;

import jade.core.Agent;

import java.util.Iterator;

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
	}
}