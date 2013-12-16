package test;

import junit.framework.TestCase;
import model.Node;

import org.junit.Test;

public class NodeTest extends TestCase{

	@Test
	public void testValueDefault() {
		Node node = new Node();
		assertEquals(0, node.getCost());
		assertEquals(null, node.getParent());
		assertEquals('N', node.getParentAction());
	}

}
