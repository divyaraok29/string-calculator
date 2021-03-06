package calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

import calculator.*;

public class TestStringCalculator {
    @Test
	public void testStringCalculator() throws NegativesNotAllowedException{
		// Basic test cases
		assertEquals(0,StringCalculator.add(""));
		assertEquals(1,StringCalculator.add("1"));
		assertEquals(4,StringCalculator.add("1,3"));
		assertEquals(354,StringCalculator.add("21,333"));

		// New line delimiter for the basic test cases
		assertEquals(354,StringCalculator.add("21\n333"));

		// Multiple numbers in the string
		assertEquals(360,StringCalculator.add("21\n333,1,2,3"));
		assertEquals(360,StringCalculator.add("21,333\n1,2\n3"));

		// Default delimiter cases
		assertEquals(3,StringCalculator.add("//;\n1;2"));
		assertEquals(3,StringCalculator.add("//\n\n1\n2"));
		assertEquals(6,StringCalculator.add("//#\n1\n2#3"));
		assertEquals(360,StringCalculator.add("//#\n21,333\n1,2\n3"));

		// To check if the getCalledCount method is counting
		// the invoke when add method throws an error.
		System.out.println("The no. of invokes before any exceptions are thrown: "+ StringCalculator.getCalledCount());
		// try{
		// 	StringCalculator.add("//#\n21,333\n-1,2\n3");
		// }
		// finally{
		// 	System.out.println("The no. of invokes after exception is thrown: " + StringCalculator.getCalledCount());
		// }

		// Ignoring numbers greater than 1000
		assertEquals(27,StringCalculator.add("//#\n21,1001\n1,2\n3"));
		assertEquals(1027,StringCalculator.add("//#\n21,1000\n1,2\n3"));
		
		// Multilength delimiters
		assertEquals(360,StringCalculator.add("//[****]\n21,333****1,2****3"));
		assertEquals(60,StringCalculator.add("//[++]\n21,33++1,2++3"));
		assertEquals(60,StringCalculator.add("//[]]\n21,33]1,2]3"));
		assertEquals(60,StringCalculator.add("//[[]\n21[33[1,2[3"));
		assertEquals(60,StringCalculator.add("//[//]\n21//33//1,2//3"));
		assertEquals(60,StringCalculator.add("//[]\n]\n21]\n33]\n1,2]\n3"));
		assertEquals(60,StringCalculator.add("//[-]\n21-33-1,2\n3"));

		// Multiple delimiters
		assertEquals(60,StringCalculator.add("//[-][+]\n21-33+1,2\n3"));
		assertEquals(60,StringCalculator.add("//[[][]]\n21[33]1,2\n3"));
		// Assuming the below case is invalid
		// assertEquals(60,StringCalculator.add("//[][][[]]\n21][33[]1,2\n3"));

		// Multiple delimiters with length longer than 1 char
		assertEquals(6,StringCalculator.add("//[**][%%]\n1**2%%3"));
		assertEquals(6,StringCalculator.add("//[*][%]\n1*2%3"));
	}

	@Test
	public void testNegativesNotAllowedException() throws NegativesNotAllowedException {
		// This method tests the NegativesNotAllowedException thrown by
		// add method.
		String message = "negatives not allowed - ";
		
		Throwable exception = assertThrows(NegativesNotAllowedException.class, () -> StringCalculator.add("//#\n-4\n2#-3"));
		assertEquals(message+"[-4,-3]", exception.getMessage());
		
		exception = assertThrows(NegativesNotAllowedException.class, () -> StringCalculator.add("//#\n4\n2#-3"));
		assertEquals(message+"[-3]", exception.getMessage());

		exception = assertThrows(NegativesNotAllowedException.class, () -> StringCalculator.add("//[-]\n-21-33-1,2\n3"));
		assertEquals(message+"[-21]", exception.getMessage());

		System.out.println("The no. of invokes when the thrown exceptions are handled: " + StringCalculator.getCalledCount());
	}
}
