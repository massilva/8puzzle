package test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import model.*;

import org.junit.Test;

import behaviour.ThinkBehaviour;

public class ThinkBehaviourTest extends TestCase{

	ThinkBehaviour nl = new ThinkBehaviour();
	//linha 1
	int [][] s1 = {{0,1,2},{3,4,5},{6,7,8}};
	int [][] s2 = {{3,0,5},{7,8,6},{1,2,4}};
	int [][] s3 = {{5,3,0},{4,1,6},{8,2,7}};
	//linha 2
	int [][] s4 = {{4,1,3},{0,2,6},{7,5,8}};
	int [][] s5 = {{7,2,4},{5,0,6},{8,3,1}};
	int [][] s6 = {{7,8,6},{3,5,0},{1,2,4}};
	//linha 3
	int [][] s7 = {{2,3,7},{5,4,8},{0,6,1}};
	int [][] s8 = {{8,6,7},{2,5,4},{3,0,1}};
	int [][] s9 = {{1,2,3},{4,5,6},{8,7,0}}; //Não resolvivel
	
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
		assertEquals(0, nl.manhattanDistance(s1));
		assertEquals(18, nl.manhattanDistance(s5));
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
		char [] a1 = {'L','U'};
		availableActionAux(nl.getAvailableAction(s1),a1);
		char [] a2 = {'R','U','L'};
		availableActionAux(nl.getAvailableAction(s2),a2);
		char [] a3 = {'R','U'};
		availableActionAux(nl.getAvailableAction(s3),a3);
		char [] a4 = {'L','U','D'};
		availableActionAux(nl.getAvailableAction(s4),a4);
		char [] a5 = {'D','R','L','U'};
		availableActionAux(nl.getAvailableAction(s5),a5);
		char [] a6 = {'D','R','U'};
		availableActionAux(nl.getAvailableAction(s6),a6);
		char [] a7 = {'L','D'};
		availableActionAux(nl.getAvailableAction(s7),a7);
		char [] a8 = {'R','D','L'};
		availableActionAux(nl.getAvailableAction(s8),a8);
		char [] a9 = {'R','D'};
		availableActionAux(nl.getAvailableAction(s9),a9);
	}
	
	@Test
	public void testResult(){
		Node p1 = new Node(s1, null, 'N', 0);
		int [][] e1 = {{1,0,2},{3,4,5},{6,7,8}};
		Node n1 = new Node(e1, p1, 'L', 1);
		Node t1 = nl.result('L',p1);
		assertEquals(p1, t1.getParent());
		assertEquals(n1,t1);
	}
	
	@Test
	public void testGetAllResult(){
		Node nState = new Node(s2, null, 'N', 0);
		char [] actions = nl.getAvailableAction(nState.getState());
		List<Node> nodes = nl.getAllResult(actions, nState);
		List<Node> lista = new ArrayList<Node>();
		int [][] e1 = {{0,3,5},{7,8,6},{1,2,4}};
		lista.add(new Node(e1,nState,'R',1));
		int [][] e2 = {{3,8,5},{7,0,6},{1,2,4}};
		lista.add(new Node(e2,nState,'U',1));
		int [][] e3 = {{3,5,0},{7,8,6},{1,2,4}};
		lista.add(new Node(e3,nState,'L',1));
		assertEquals(lista,nodes);
	}
	
	@Test
	public void testQuickSort(){
		List<Node> lista = new ArrayList<Node>();
		lista.add(new Node(s3, null, 'N', 2));
		lista.add(new Node(s2, null, 'N', 21));
		lista.add(new Node(s1, null, 'N', 0));
		nl.quick_sort(lista, 0,lista.size()-1);
		List<Node> ordenado = new ArrayList<Node>();
		ordenado.add(new Node(s1, null, 'N', 0));
		ordenado.add(new Node(s3, null, 'N', 2));
		ordenado.add(new Node(s2, null, 'N', 21));
		assertEquals(ordenado, lista);
	}
	
	@Test
	public void testAddInOrder(){
		List<Node> esperado = new ArrayList<Node>();
		List<Node> lista = new ArrayList<Node>();
		esperado.add(new Node(s1, null, 'N', 0)); //md = 0 
		esperado.add(new Node(s3, null, 'N', 2)); //md = 16 
		esperado.add(new Node(s2, null, 'N', 21));//md = 17
		nl.addInOrderByManhattanDistance(lista, new Node(s3, null, 'N', 2));
		nl.addInOrderByManhattanDistance(lista,new Node(s2, null, 'N', 21));
		nl.addInOrderByManhattanDistance(lista,new Node(s1, null, 'N', 0));
		assertEquals(esperado, lista);
	}
	
	@Test
	public void testInList(){
		List<Node> lista = new ArrayList<Node>();
		lista.add(new Node(s1, null, 'N', 0));
		lista.add(new Node(s2, null, 'N', 0));
		lista.add(new Node(s3, null, 'N', 0));
		lista.add(new Node(s4, null, 'N', 0));
		lista.add(new Node(s4, null, 'N', 0));
		lista.add(new Node(s5, null, 'N', 0));
		lista.add(new Node(s6, null, 'N', 0));
		assertEquals(true,nl.inList(lista,new Node(s1, null, 'N', 0)));
		assertEquals(true,nl.inList(lista,new Node(s2, null, 'N', 0)));
		assertEquals(true,nl.inList(lista,new Node(s3, null, 'N', 0)));
		assertEquals(true,nl.inList(lista,new Node(s4, null, 'N', 0)));
		assertEquals(true,nl.inList(lista,new Node(s5, null, 'N', 0)));
		assertEquals(true,nl.inList(lista,new Node(s6, null, 'N', 0)));
		assertEquals(false,nl.inList(lista,new Node(s7, null, 'N', 0)));
		assertEquals(false,nl.inList(lista,new Node(s8, null, 'N', 0)));
		assertEquals(false,nl.inList(lista,new Node(s9, null, 'N', 0)));
	}
	
	@Test
	public void testReconstructPath(){
		Node n1 = new Node(s1, null, 'N', 0);
		Node n2 = new Node(s3, n1, 'L', 1);
		Node n3 = new Node(s4, n2, 'R', 0);
		assertEquals("StateI: [0, 1, 2][3, 4, 5][6, 7, 8]\n", nl.reconstructPath(n1));
		assertEquals("StateI: [0, 1, 2][3, 4, 5][6, 7, 8]\nAction: L ==> State: [5, 3, 0][4, 1, 6][8, 2, 7]\n", nl.reconstructPath(n2));
		assertEquals("StateI: [0, 1, 2][3, 4, 5][6, 7, 8]\nAction: L ==> State: [5, 3, 0][4, 1, 6][8, 2, 7]\nAction: R ==> State: [4, 1, 3][0, 2, 6][7, 5, 8]\n", nl.reconstructPath(n3));
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
