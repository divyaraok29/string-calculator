package calculator;

import static org.junit.Assert.assertEquals;
import calculator.StringCalculator;
import org.junit.Test;

public class TestStringCalculator {
    @Test
	public void testStringCalculator(){
		assertEquals(0,StringCalculator.add(""));
		assertEquals(1,StringCalculator.add("1"));
		assertEquals(4,StringCalculator.add("1,3"));
		assertEquals(354,StringCalculator.add("21,333"));
		assertEquals(354,StringCalculator.add("21\n333"));
		assertEquals(360,StringCalculator.add("21\n333,1,2,3"));
		assertEquals(360,StringCalculator.add("21,333\n1,2\n3"));
		assertEquals(3,StringCalculator.add("//;\n1;2"));
		assertEquals(3,StringCalculator.add("//\n\n1\n2"));
		assertEquals(6,StringCalculator.add("//#\n1\n2#3"));
		assertEquals(360,StringCalculator.add("//#\n21,333\n1,2\n3"));
	}
}
