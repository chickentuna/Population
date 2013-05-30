package test;

import static org.junit.Assert.*;
import kernel.Point;

import org.junit.Test;

public class PointTest {

	@Test
	public void directionToTest() {
		Point o = new Point(0,0);
		Point a = new Point(5,5);
		Point b = new Point(-5,-5);
		Point c = new Point(5,-5);
		Point d = new Point(-5,0);
		
		float delta = 0.0001f;
		assertEquals("O to 5;5",Math.PI/4,o.directionTo(a),delta);
		assertEquals("O to -5;-5",-3*Math.PI/4,o.directionTo(b),delta);
		assertEquals("O to 5;-5",-Math.PI/4,o.directionTo(c),delta);
		assertEquals("O to -5;0",Math.PI,o.directionTo(d),delta);
		
		
	}

}
