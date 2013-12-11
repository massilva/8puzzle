package test;

import java.util.List;
import java.util.ArrayList;

import junit.framework.TestCase;
import model.Node;
import model.Position;

import org.junit.Test;

import behaviour.ThinkBehaviour;

public class ThinkBehaviourTest extends TestCase{

	ThinkBehaviour nl = new ThinkBehaviour();
	//linha 1
	int [][] s1 = {{0,1,2},{3,4,5},{6,7,8}};
	int [][] s2 = {{3,0,5},{7,8,6},{1,2,4}}; //Não resolvivel
	int [][] s3 = {{5,3,0},{4,1,6},{8,2,7}};
	//linha 2
	int [][] s4 = {{4,1,3},{0,2,6},{7,5,8}};
	int [][] s5 = {{7,2,4},{5,0,6},{8,3,1}};
	int [][] s6 = {{7,8,6},{3,5,0},{1,2,4}};
	//linha 3
	int [][] s7 = {{2,3,7},{5,4,8},{0,6,1}};
	int [][] s8 = {{8,6,7},{2,5,4},{3,0,1}}; //Não resolvivel
	int [][] s9 = {{1,2,3},{4,5,6},{8,7,0}}; //Não resolvivel
	
	List <Node> fronteira = new ArrayList<Node>();
	List <Node> explorado = new ArrayList<Node>();
	
	@Test
	public void testGetPositionDefault(){
		Position position = new Position(0,0);
		assertEquals(position, nl.getPositionDefault(0));
		position = new Position(0,1);
		assertEquals(position, nl.getPositionDefault(1));
		position = new Position(0,2);
		assertEquals(position, nl.getPositionDefault(2));
		position = new Position(1,0);
		assertEquals(position, nl.getPositionDefault(3));
		position = new Position(1,1);
		assertEquals(position, nl.getPositionDefault(4));
		position = new Position(1,2);
		assertEquals(position, nl.getPositionDefault(5));
		position = new Position(2,0);
		assertEquals(position, nl.getPositionDefault(6));
		position = new Position(2,1);
		assertEquals(position, nl.getPositionDefault(7));
		position = new Position(2,2);
		assertEquals(position, nl.getPositionDefault(8));
	}
	
	@Test
	public void testManhattanDistance(){	
		assertEquals(0, nl.manhattanDistance(new Node(s1, null, 'N', 0)));
		assertEquals(28, nl.manhattanDistance(new Node(s5, null, 'N', 10)));
	}
	
	@Test
	public void testIsObjetiveState(){
		assertEquals(true,  nl.isObjetiveState(s1));
		assertEquals(false, nl.isObjetiveState(s2));
		assertEquals(false, nl.isObjetiveState(s3));
		assertEquals(false, nl.isObjetiveState(s4));
		assertEquals(false, nl.isObjetiveState(s5));
		assertEquals(false, nl.isObjetiveState(s6));
		assertEquals(false, nl.isObjetiveState(s7));
		assertEquals(false, nl.isObjetiveState(s8));
		assertEquals(false, nl.isObjetiveState(s9));
		int [][] s10 = {{0,1,2},{3,4,5},{8,7,6}};
		assertEquals(false, nl.isObjetiveState(s10));
	}
	
	@Test
	public void testGetTileEmpty(){
		//linha 1
		assertEquals(new Position(0,0),nl.getTileEmpty(s1));
		assertEquals(new Position(0,1),nl.getTileEmpty(s2));
		assertEquals(new Position(0,2),nl.getTileEmpty(s3));
		//linha 2
		assertEquals(new Position(1,0),nl.getTileEmpty(s4));
		assertEquals(new Position(1,1),nl.getTileEmpty(s5));
		assertEquals(new Position(1,2),nl.getTileEmpty(s6));
		//linha 3
		assertEquals(new Position(2,0),nl.getTileEmpty(s7));
		assertEquals(new Position(2,1),nl.getTileEmpty(s8));
		assertEquals(new Position(2,2),nl.getTileEmpty(s9));
	}
	
	@Test
	public void testAvailableAction(){
		char [] a1 = {'R','D'};
		availableActionAux(nl.getAvailableAction(s1),a1);
		char [] a2 = {'L','D','R'};
		availableActionAux(nl.getAvailableAction(s2),a2);
		char [] a3 = {'L','D'};
		availableActionAux(nl.getAvailableAction(s3),a3);
		char [] a4 = {'R','D','U'};
		availableActionAux(nl.getAvailableAction(s4),a4);
		char [] a5 = {'U','L','R','D'};
		availableActionAux(nl.getAvailableAction(s5),a5);
		char [] a6 = {'U','L','D'};
		availableActionAux(nl.getAvailableAction(s6),a6);
		char [] a7 = {'R','U'};
		availableActionAux(nl.getAvailableAction(s7),a7);
		char [] a8 = {'L','U','R'};
		availableActionAux(nl.getAvailableAction(s8),a8);
		char [] a9 = {'L','U'};
		availableActionAux(nl.getAvailableAction(s9),a9);
	}
	
	@Test
	public void testResult(){
		Node p1 = new Node(s1, null, 'N', 0);
		int [][] e1 = {{1,0,2},{3,4,5},{6,7,8}};
		Node n1 = new Node(e1, p1, 'R', 1);
		Node t1 = nl.result(this.fronteira,this.explorado,'R',p1);
		assertEquals(p1, t1.getParent());
		assertEquals(n1,t1);
	}
	
	@Test
	public void testGetAllResult(){
		Node nState = new Node(s2, null, 'N', 0);
		char [] actions = nl.getAvailableAction(nState.getState());
		List<Node> nodes = nl.getAllResult(this.fronteira,this.explorado,actions, nState);
		List<Node> lista = new ArrayList<Node>();
		int [][] e1 = {{0,3,5},{7,8,6},{1,2,4}};
		lista.add(new Node(e1,nState,'L',1));
		int [][] e2 = {{3,8,5},{7,0,6},{1,2,4}};
		lista.add(new Node(e2,nState,'D',1));
		int [][] e3 = {{3,5,0},{7,8,6},{1,2,4}};
		lista.add(new Node(e3,nState,'R',1));
		assertEquals(lista,nodes);
	}
	
	@Test
	public void testAddInOrder(){
		List<Node> esperado = new ArrayList<Node>();
		List<Node> lista = new ArrayList<Node>();
		esperado.add(new Node(s1, null, 'N', 0));  //md = 0 + 0 = 0
		esperado.add(new Node(s2, null, 'N', 2));  //md = 17 + 2 = 19
		esperado.add(new Node(s3, null, 'N', 20)); //md = 16 + 20 = 36
		nl.addInOrderByManhattanDistance(lista,new Node(s3, null, 'N', 20));
		nl.addInOrderByManhattanDistance(lista,new Node(s2, null, 'N', 2));
		nl.addInOrderByManhattanDistance(lista,new Node(s1, null, 'N', 0));
		assertEquals(esperado, lista);
	}
	
	@Test
	public void testListContains(){
		List<Node> list = new ArrayList<Node>();
		list.add(new Node(s1, null, 'N', 0));
		assertEquals(true, list.contains(new Node(s1, null, 'N', 0)));
		assertEquals(false, list.contains(new Node(s2, null, 'N', 0)));
	}
	
	@Test
	public void testExemplo(){
		
		/** Wikipedia and book example **/
		int [][] aux = {{7,2,4},{5,0,6},{8,3,1}};
		Node auxNode = new Node(aux, null, 'N', 0);
		assertEquals(18, nl.manhattanDistance(auxNode));
		
		/** check the available actions **/
		int [][] s = {{4,1,2},{3,0,5},{6,7,8}};
		char [] actions = nl.getAvailableAction(s);
		char [] ac = {'U','L','R','D'};
		availableActionAux(actions,ac);
		
		/** check all possible results of actions **/
		Node nState = new Node(s, null, 'N', 0);
		List<Node>lista = nl.getAllResult(this.fronteira,this.explorado, actions, nState);
		List<Node>esperado = new ArrayList<Node>();
		int [][] s1 = {{4,0,2},{3,1,5},{6,7,8}};
		Node f1 = new Node(s1, nState, 'U', 1);
		esperado.add(f1);
		int [][] s2 = {{4,1,2},{0,3,5},{6,7,8}};
		Node f2 = new Node(s2, nState, 'L', 1);
		esperado.add(f2);
		int [][] s3 = {{4,1,2},{3,5,0},{6,7,8}};
		Node f3 = new Node(s3, nState, 'R', 1);
		esperado.add(f3);
		int [][] s4 = {{4,1,2},{3,7,5},{6,0,8}};
		Node f4 = new Node(s4, nState, 'D', 1);
		esperado.add(f4);
		assertEquals(esperado, lista);
		
		/** check manhatthanDistance values of states above **/
		assertEquals(2, nl.manhattanDistance(nState)); //h = 2, c = 0
		assertEquals(4, nl.manhattanDistance(f1)); // h = 3, c = 1 
		assertEquals(4, nl.manhattanDistance(f2)); // h = 3, c = 1
		assertEquals(4, nl.manhattanDistance(f3)); // h = 3, c = 1
		assertEquals(4, nl.manhattanDistance(f4)); // h = 3, c = 1
		
	}
	
	@Test
	public void testNumberInvertions(){
		int [][] s = {{1,0,3},{2,4,5},{6,7,8}};
		assertEquals(1, nl.getNumberInversions(s));
		
		int [][] s1 = {{7,0,2},{8,5,3},{6,4,1}};
		assertEquals(19, nl.getNumberInversions(s1));
		
		int [][] s2 = {{2,0,7},{8,5,3},{6,4,1}};
		assertEquals(18, nl.getNumberInversions(s2));
		
		int [][] s3 = {{3,1,2},{0,4,5},{6,7,8}};
		assertEquals(2, nl.getNumberInversions(s3));
		
		int [][] s4 = {{0,1,2},{3,4,5},{6,7,8}};
		assertEquals(0, nl.getNumberInversions(s4));
		
		int [][] s5 = {{1,0,2},{3,4,5},{6,7,8}};
		assertEquals(0, nl.getNumberInversions(s5));		
	}
	
	/**
	 * Helper function
	 * @param result
	 * @param acoes
	 */
	private void availableActionAux(char [] result, char [] acoes){
		assertEquals(result.length,acoes.length);
		int i= 0;
		while(i < result.length){
			assertEquals(result[i],acoes[i]);
			i++;
		}
	}
	
}
