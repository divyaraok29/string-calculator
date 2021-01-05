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
	}
}
