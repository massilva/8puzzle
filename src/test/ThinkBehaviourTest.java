package test;

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
		Node t1 = nl.result('R',p1);
		assertEquals(p1, t1.getParent());
		assertEquals(n1,t1);
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
