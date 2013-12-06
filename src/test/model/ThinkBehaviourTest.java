package test.model;

import junit.framework.TestCase;
import model.*;

import org.junit.Test;

import behaviour.ThinkBehaviour;

public class ThinkBehaviourTest extends TestCase{

	ThinkBehaviour nl = new ThinkBehaviour();

	int [][] s1 = {{0,1,2},{3,4,5},{6,7,8}};
	int [][] s2 = {{7,2,4},{5,0,6},{8,3,1}};
	int [][] s3 = {{4,1,3},{0,2,6},{7,5,8}};
	int [][] s4 = {{3,0,5},{7,8,6},{1,2,4}};
	int [][] s5 = {{1,2,3},{4,5,6},{8,7,0}}; //Não resolvivel
	int [][] s6 = {{2,3,7},{5,4,8},{0,6,1}};
	int [][] s7 = {{8,6,7},{2,5,4},{3,0,1}};
	
	@Test
	public void testGetPositionDefault() {
		
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
		assertEquals(18, nl.manhattanDistance(s2));
	}
	
	@Test
	public void testIsObjetiveState(){
		assertEquals(true, nl.isObjetiveState(s1));
		assertEquals(false, nl.isObjetiveState(s2));
		assertEquals(false, nl.isObjetiveState(s3));
	}
	
	@Test
	public void testGetTileEmpty(){
		assertEquals(new Position(0,0),nl.getTileEmpty(s1));
		assertEquals(new Position(1,1),nl.getTileEmpty(s2));
		assertEquals(new Position(1,0),nl.getTileEmpty(s3));
	}
	
	@Test
	public void testAvailableAction(){
		char [] a1 = {'R','D'};
		availableActionAux(nl.availableAction(s1),a1);
		
		char [] a2 = {'R','D','U'};
		availableActionAux(nl.availableAction(s3),a2);
		
		char [] a3 = {'U','L','R','D'};
		availableActionAux(nl.availableAction(s2),a3);
	}
	
	private void availableActionAux(char [] result, char [] acoes){
		assertEquals(result.length,acoes.length);
		int i= 0;
		while(i < result.length){
			assertEquals(result[i],acoes[i]);
			i++;
		}
	}
	
}
