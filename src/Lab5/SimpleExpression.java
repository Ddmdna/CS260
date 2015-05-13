package Lab5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************************************************************
* Used to test expressions for validity. Converts infix to postfix. Can solve
* postfix that do not contain variables. Requires the use of LinkedStack
*
* @note
*   Expressions can be made of any length, limited only by the amount of
*   free memory in the heap. Might be limited to user input, String capacity, 
*   and LinkedStack capacity. Errors will be returned with invalid expressions are input.
*
* @author Drew Hamm 
*   <A HREF="mailto:hammdre@gmail.com"> (hammdre@gmail.com) </A>
*
* @version
*   May 8, 2015
******************************************************************************/
public class SimpleExpression {
	final int INVALID = 0;
	final int PREFIX = 1;
	final int INFIX = 2;
	final int POSTFIX = 3; 
	
	private String expression;
	private boolean isValidExpression;
	private boolean hasVariables;
	private int format;
	
	/**
	 * Constructor for SimpleExpresion
	 * @postcondition
	 *  expression == ""
	 *  isValidExpression == false
	 *  hasVariables == false
	 *  format == INVALID;
	 * @exception NullPointerException
	 * 	Indicates that input was null
	 **/
	public SimpleExpression(){
		expression = "";
		isValidExpression = false;
		hasVariables = false;
		format = INVALID;
	}

	
	/**
	 * Receives a string input to be set as the expression
	 * @param input
	 *  The String expression to be set for SimpleExpression
	 * @postcondition
	 *  expression is set to input
	 *  format is set to INFIX
	 **/
	public void setInfixExpression(String input) {
		expression = input;
		format = INFIX;
	}
	
	
	/**
	 * Tries to convert the current expression to the postfix form
	 * @precondition
	 *  format must be equal to INFIX
	 * @postcondition
	 *  If(isValidExpression)
	 *  	expression has been converted to postfix
	 *   	format = POSTFIX
	 *   	if(variableCount > 0)
	 *			hasVariables = true;
 	 *		else
	 *			hasVariables = false;
	 *  else
	 *  	format = INVALID;
	 *  
	 *  Is having some multiple state postcondition bad practice?
	 * @exception IllegalStateException
	 * 	Thrown if preconditions are not met
	 **/
	public void convertInfixToPostfix() {
		//Test preconditions
		if(format != INFIX) throw new IllegalStateException("current format is not INFIX");

		//Keep track of errors
		boolean noError = true;
		int operatorCount = 0;
		int operandCount = 0;
		int variableCount = 0;
		
		//Playing around with regular expressions
		//I will search from the begining of the string.
		//As I find matches I remove them from the string such that these patterns continue to function correctly.
		Pattern leftParenthese = Pattern.compile("^\\(\\s+");
		Pattern rightParenthese = Pattern.compile("^\\)\\s+");
		Pattern number = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+\\s+"); //a bit tricky with looking for digits before a decimal
		Pattern variable = Pattern.compile("^\\p{Alpha}+\\s+");
		Pattern operator = Pattern.compile("^[+-=*/^]\\s+");
		Matcher matcher;
		
		//Used to incrementally convert the expression
		String result = "";
		
		// The expression may contain integers, operators, parenthesis and variables.
		// There should be a space between each item in the input expression.
		
		//1. Initialize a stack of characters to hold the operation symbols and parentheses
		LinkedStack<Character> stack = new LinkedStack<Character>();
		
		//2. Try to convert infix to postfix
		String remainingExpression = expression;
		do
		{
			//if the next input is a left parentheses
			matcher = leftParenthese.matcher(remainingExpression);
			if(matcher.find()) //Left parentheses found
			{
				//Push a left parentheses unto the stack
				stack.push(matcher.group(0).charAt(0));
				
				//Remove the left parentheses from remainingExpression
				remainingExpression = matcher.replaceFirst(""); //Will this give me a blank string???
				
				continue;
			}
			
			//if the next input is a number
			matcher = number.matcher(remainingExpression);
			if(matcher.find()) //number found
			{
				//Write the number to the output.
				result += matcher.group(0);
				
				//Remove the operand from remainingExpression
				remainingExpression = matcher.replaceFirst(""); //Will this give me a blank string???
				
				//Keep track of operands
				operandCount++;
				
				continue;
			}
			
			//if the next input is a variable
			matcher = variable.matcher(remainingExpression);
			if(matcher.find()) //variable found
			{
				//Write the to the output.
				result += matcher.group(0);
				
				//Remove the operand from remainingExpression
				remainingExpression = matcher.replaceFirst(""); //Will this give me a blank string???
				
				//Keep track of operands
				operandCount++;
				
				//Keep track of the number of variables
				variableCount++;
				continue;
			}
			
			//if (the next input is one of the operation symbols)
			matcher = operator.matcher(remainingExpression);
			if(matcher.find()) //operator found
			{		
				// Pop and print operations off the stack until one of the three things occurs:
				// (1) The stack becomes empty
				// (2) The next symbol on the stack is a left parentheses
				// (3) The next symbol on the stack is an operation with lower precedence than the next input symbol.
				// *After one of the preceding outcomes
				while(!stack.isEmpty() && stack.peek() != '(' && isHigherPrecidence(stack.peek(), matcher.group(0).charAt(0)))
				{
					//Write operator to the output.
					result += stack.pop() + " ";
				}
				
				//Push the operator unto the stack
				stack.push(matcher.group(0).charAt(0));
				
				//Remove the operand from remainingExpression
				remainingExpression = matcher.replaceFirst(""); //Will this give me a blank string???
				
				//Keep track of operators
				operatorCount++;
				
				continue;
			}
			
			//if the next input is a right parentheses
			matcher = rightParenthese.matcher(remainingExpression);
			if(matcher.find()) //right parentheses found
			{
				//Remove the right parentheses from remainingExpression
				remainingExpression = matcher.replaceFirst(""); //Will this give me a blank string???
				
				//Pop and print operators off the stack until the next symbol on the stack is a left parentheses.
				while(!stack.isEmpty() && stack.peek() != '(')
				{
					//Write operator to the output
					result += stack.pop() + " ";
				}
				
				//(If no left parentheses is encountered, then print an error message indicating unbalanced parentheses and halt.)
				if(stack.isEmpty() || stack.peek() != '(')
				{
					noError = false;
				} else
				{
					//Finally, pop and discard the left parentheses.
					stack.pop();
				}
				
				continue;
			}
			
			noError = false;
			break;
		// while (there is more of the expression to read);
		} while(remainingExpression.length() > 0);
		
		//3. Pop and print any remaining operations on the stack. 
		while(!stack.isEmpty())
		{
			// If a left parentheses is encountered, then indicate unbalanced parentheses and break
			if(stack.peek() == '(')
			{
				noError = false;
				break;
			}
			
			//Write operator to the output.
			result += stack.pop() + " ";
		}
		
		//Check that there is a matching number of operators vs operands
		if(operandCount - operatorCount != 1)
		{
			noError = false;
		}
		
		//Establish the postconditions
		isValidExpression = noError;
		if(isValidExpression)
		{
			format = POSTFIX;
			expression = result;
			if(variableCount > 0)
			{
				hasVariables = true;
			} else
			{
				hasVariables = false;
			}
		}
		else
		{
			format = INVALID;
		}
	}


	/**
	 * Compares two operators and determines if the first has higher
	 * precedence than the second
	 * @param first
	 *  The first of two character operators
	 * @param second
	 *  The second of two character operators
	 * @return
	 *  returns true if first has higher precedence than second
	 **/
	private static boolean isHigherPrecidence(Character first, Character second) {
		boolean result = true;
		if(precedenceLevel(first) < precedenceLevel(second)) result = false;
		return result;
	}

	
	/**
	 * Determines the precedence level of the input operator character according to the standard
	 * @param op
	 *  The operator
	 * @return
	 *  returns true if first has higher precedence than second
	 *  else returns false
	 * @exception IllegalArgumentException
	 * 	Indicates that op was an unknown operator
	 **/
	private static int precedenceLevel(Character op) {
		int result = -1;
		switch(op)
		{
	        case '+':
	        case '-':
	            result = 0;
	            break;
	        case '*':
	        case '/':
	        	result = 1;
	        	break;
	        case '^':
	        	result = 2;
	        	break;
	        default:
	            throw new IllegalArgumentException("Operator unknown: " + op);
		}
		return result;
	}
	
	
	/**
	 * Gets the expressions state in regards to its validity.
	 * @return
	 *  returns isValidExpression if format != INVALID
	 **/
	public boolean isValid() {
		boolean result = false;
		if(format != INVALID)
			result = isValidExpression;
		return result;
	}

	
	/**
	 * Gets the expressions state in regards to it containing variables
	 * @return
	 *  returns hasVariables
	 **/
	public boolean hasVariables() {
		return hasVariables;
	}
	
	
	/**
	 * Takes the current expression and adds a space to the end.
	 * Removes all cases of multiples spaces with a single space.
	 * @postcondition
	 * 	A space is the last character in the expression string
	 * 	No two space characters are adjacent
	 *  No spaces preceding the expression
	 **/
	public void ensureFormat() {
		//Might be extra work but ensures that there is a whitespace at the end of the expression
		expression += " ";
		
		//Prepare and find all substrings with multiple spaces in a row
		Pattern multiSpace = Pattern.compile(" \\s+");
		Matcher matcher = multiSpace.matcher(expression);
		matcher.find();
		
		//Replace all multi-space substrings with a single space
		expression = matcher.replaceAll(" ");
		
		//Remove any and all spaces at the front of the expression
		Pattern frontSpace = Pattern.compile("^\\s+");
		matcher = frontSpace.matcher(expression);
		matcher.find();
		expression = matcher.replaceAll("");
	}
	
	
	/**
	 * Calculates a valid expression
	 * @precondition
	 *  hasVariables == false
	 *  isValid == true
	 *  format != INVALID
	 * @return
	 *  returns the result of the solved expression
	 * @exception IllegalStateException
	 * 	Indicates that op was an unknown operator
	 **/
	public double solvePostFix() {
		//Check precondition
		if(hasVariables == true) throw new IllegalStateException("The expression contains variables");
		if(isValidExpression == false) throw new IllegalStateException("The expression is not valid");
		if(format != POSTFIX) throw new IllegalStateException("The format is not valid");
		
		//Set up pattern
		Pattern numberPattern = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+\\s+"); //a bit tricky with looking for digits before a decimal
		Pattern operatorPattern = Pattern.compile("^[+-=*/^]\\s+");
		
		//Set up for operands
		LinkedStack<Double> operands = new LinkedStack<Double>();
		double operand1, operand2;
		
		//Set up a temp copy of the expression
		String remainingExpression = expression;
		
		//Set up matchers
		Matcher numberMatcher;
		Matcher operatorMatcher;
		
		//Fill each stack with their respective values from the remainingExpression
		//and calculate the result
		double result = 0.0;
		while(remainingExpression.length() != 0)
		{
			numberMatcher = numberPattern.matcher(remainingExpression);
			operatorMatcher = operatorPattern.matcher(remainingExpression);
			if(numberMatcher.find())
			{
				//Push the operand
				operands.push(Double.parseDouble(numberMatcher.group(0)));
				//Remove the operand from remainingExpression
				remainingExpression = numberMatcher.replaceFirst("");
			}
			else if(operatorMatcher.find())
			{
				operand2 = operands.pop();
				operand1 = operands.pop();
				switch(operatorMatcher.group(0).charAt(0))
				{
					case '+':
						operands.push(operand1 + operand2);
						break;
					case '-':
						operands.push(operand1 - operand2);
						break;
					case '*':
						operands.push(operand1 * operand2);
						break;
					case '/':
						operands.push(operand1 / operand2);
						break;
					case '^':
						operands.push(Math.pow(operand1, operand2));
						break;
				}

				//Remove the operator from remainingExpression
				remainingExpression = operatorMatcher.replaceFirst("");
			}
		}
		
		//Get the final value
		result = operands.pop();
		
		return result;
	}
	
	
	/**
	 * Basic toString method for expression
	 * @return
	 * 	returns the String expression
	 **/
	public String toString(){
		return expression;
	}
	
	/**
	 * Basic getter for the int format
	 * @return
	 * 	returns format
	 **/
	public int getFormat(){
		return format;
	}
}
