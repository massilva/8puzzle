package test.model;

import junit.framework.TestCase;
import model.*;

import org.junit.Test;

public class NamelessTest extends TestCase{

	Nameless nl = new Nameless();
	
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
		
		int [][] s1 = {{0,1,2},{3,4,5},{6,7,8}};
		assertEquals(0, nl.manhattanDistance(s1));
		
		int [][] s2 = {{7,2,4},{5,0,6},{8,3,1}};
		assertEquals(18, nl.manhattanDistance(s2));
		
	}
	
}
