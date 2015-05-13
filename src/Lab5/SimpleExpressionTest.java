package Lab5;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleExpressionTest {

	@Test
	public void testSimpleExpression() {
		SimpleExpression simpleE = new SimpleExpression();
		assertEquals("", simpleE.toString());
		assertEquals(false, simpleE.isValid());
		assertEquals(false, simpleE.hasVariables());
		assertEquals(simpleE.INVALID, simpleE.getFormat());
	}

	@Test
	public void testSetInfixExpression() {
		SimpleExpression simpleE = new SimpleExpression();
		String prefix = "5 + 5";
		simpleE.setInfixExpression(prefix);
		assertEquals(prefix, simpleE.toString());
		assertEquals(simpleE.INFIX, simpleE.getFormat());
		
		prefix = "";
		simpleE.setInfixExpression(prefix);
		assertEquals(prefix, simpleE.toString());
		assertEquals(simpleE.INFIX, simpleE.getFormat());
		
		prefix = null;
		simpleE.setInfixExpression(prefix);
		assertEquals(prefix, simpleE.toString());
		assertEquals(simpleE.INFIX, simpleE.getFormat());
	}

	@Test
	public void testConvertInfixToPostfix() {
		SimpleExpression simpleE = new SimpleExpression();
		String prefix = "5 + 5";
		String postfix = "5 5 + ";
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(simpleE.POSTFIX, simpleE.getFormat());
		assertEquals(false, simpleE.hasVariables());
		assertEquals(true, simpleE.isValid());
		assertEquals(5.0 + 5.0, simpleE.solvePostFix(), .1);
		
		prefix = "3 * X + ( Y - 12 ) - Z";
		postfix = "3 X * Y 12 - + Z - ";
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(postfix, simpleE.toString());
		assertEquals(simpleE.POSTFIX, simpleE.getFormat());
		assertEquals(true, simpleE.hasVariables());
		assertEquals(true, simpleE.isValid());
		
		prefix = " 5   +    6        ";
		postfix = "5 6 + ";
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(postfix, simpleE.toString());
		assertEquals(simpleE.POSTFIX, simpleE.getFormat());
		assertEquals(false, simpleE.hasVariables());
		assertEquals(true, simpleE.isValid());
		
		prefix = "5 5 + + ";
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(simpleE.INVALID, simpleE.getFormat());
		assertEquals(false, simpleE.isValid());
		
		prefix = "+";
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(simpleE.INVALID, simpleE.getFormat());
		assertEquals(false, simpleE.isValid());
	}

	@Test
	public void testIsValid() {
		SimpleExpression simpleE = new SimpleExpression();
		String prefix = "3 * X + ( Y - 12 ) - Z";
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(true, simpleE.isValid());
		
		prefix = "3 * X + +";
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(false, simpleE.isValid());
	}

	@Test
	public void testHasVariables() {
		SimpleExpression simpleE = new SimpleExpression();
		String prefix = "3 * X + ( Y - 12 ) - Z";
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(true, simpleE.hasVariables());
		
		prefix = "3 * 5 + ( 6 - 12 ) - 7";
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(false, simpleE.hasVariables());
	}

	@Test
	public void testEnsureFormat() {
		SimpleExpression simpleE = new SimpleExpression();
		String prefix = " 3 * X   +    ( Y -    12 ) - Z";
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(true, simpleE.toString().charAt(0) != ' ');
		assertEquals(' ', simpleE.toString().charAt(simpleE.toString().length()-1));
		assertEquals(false, simpleE.toString().matches(" \\s+"));
		
		prefix = "5      +      5  +    5   ( 5 /   5   )    *  5           ";
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(true, simpleE.toString().charAt(0) != ' ');
		assertEquals(' ', simpleE.toString().charAt(simpleE.toString().length()-1));
		assertEquals(false, simpleE.toString().matches(" \\s+"));
	}

	@Test
	public void testSolvePostFix() {
		double epsilon = .01;
		SimpleExpression simpleE = new SimpleExpression();
		String prefix = "5 + 5";
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(5.0 + 5.0, simpleE.solvePostFix(), epsilon);
		
		prefix = "5 / 5";
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(5.0 / 5.0, simpleE.solvePostFix(), epsilon);
		
		prefix = "3 * 10 + ( 7 / 3 ) * 4 - 2";;
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(3.0 * 10.0 + ( 7.0 / 3.0 ) * 4.0 - 2.0, simpleE.solvePostFix(), epsilon);
		
		prefix = "-3.7 * -.05 + ( 7.67 / -5.5 ) * 4 ^ 2 - 2";;
		simpleE.setInfixExpression(prefix);
		simpleE.ensureFormat();
		simpleE.convertInfixToPostfix();
		assertEquals(-3.7 * -.05 + ( 7.67 / -5.5 ) * Math.pow(4, 2) - 2, simpleE.solvePostFix(), epsilon);
	}

	@Test
	public void testToString() {
		SimpleExpression simpleE = new SimpleExpression();
		String prefix = "5 + 5";
		simpleE.setInfixExpression(prefix);
		assertEquals(prefix, simpleE.toString());
		
		prefix = null;
		simpleE.setInfixExpression(prefix);
		assertEquals(prefix, simpleE.toString());
		
		prefix = "   ^(^&#(^& b3^&*()@&#*YFD()Y843f9j$)U*F YH& $     ";
		simpleE.setInfixExpression(prefix);
		assertEquals(prefix, simpleE.toString());
		
		prefix = "waldo";
		simpleE.setInfixExpression(prefix);
		assertEquals(prefix, simpleE.toString());
		
	}

}
