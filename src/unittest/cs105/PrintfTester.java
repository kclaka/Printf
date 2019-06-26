package unittest.cs105;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.IllegalFormatConversionException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.sbcc.cs105.Main;

public class PrintfTester {
	private static final int maximumScore = 20;
	private static final int maximumAssignmentScore = 30;
	private static int totalScore;
	private static int extraCreditScore;
	private static boolean lowerCase;
	private static boolean upperCase;
	
	@BeforeClass
	public static void beforeTesting() {
		totalScore = 0;
		extraCreditScore = 0;
		lowerCase = false;
		upperCase = false;
	}

	@AfterClass
	public static void afterTesting() {
		System.out.printf("Your program's functionality scores %d out of %d.\n\n", totalScore + extraCreditScore, maximumScore);

		int difference = maximumAssignmentScore - maximumScore;
		String correctedPoint = (difference == 1) ? "point" : "points";

		System.out.printf("The assignment is worth a total of %d where the remainder of %d %s\n",
				maximumAssignmentScore, difference, correctedPoint);
		System.out.println("comes from grading related to documentation, algorithms, and other");
		System.out.println("criteria.\n");
		
		if(extraCreditScore > 0) {
			System.out.printf("You also got %d points of extra credit for implementing Hex values.\n", extraCreditScore);
		}
	}

	@Test
	public void printfNoFormats() {
		assertEquals("Hello World!", Main.format("Hello World!"));
		
		totalScore += 1;
	}
	 
	@Test(expected = IllegalArgumentException.class)
	public void printfTooFewArgs() {
		
		try {
			Main.format("Hello World! %2d");
		} catch(IllegalArgumentException e) {
			totalScore += 1;
			throw e;
		}
		
		fail("Your program should thrown an illegalargumentexception for Main.format(\"Hello World! %2d\")");
	}
	
	@Test(expected = IllegalFormatConversionException.class) 
	public void printfBadArgumentForStringFormat() {
		try {
			Main.format("Hello %s", 32);
		} catch(IllegalArgumentException e) {
			totalScore += 1;
			throw e;
		}
		
		fail("Your program should thrown an illegalargumentexception for Main.format(\"Hello %2s\", 32)");
	}
	
	@Test(expected = IllegalFormatConversionException.class) 
	public void printfBadArgumentForIntegerFormat() {
		try {
			Main.format("Hello %d", "32");
		} catch(IllegalArgumentException e) {
			totalScore += 1;
			throw e;
		}
		
		fail("Your program should thrown an illegalargumentexception for Main.format(\"Hello %d\", \"32\")");
	}
	
	@Test(expected = IllegalFormatConversionException.class) 
	public void printfBadArgumentForDoubleFormat() {
		try {
			Main.format("Hello %f", 32);
		} catch(IllegalArgumentException e) {
			totalScore += 1;
			throw e;
		}
		
		fail("Your program should thrown an illegalargumentexception for Main.format(\"Hello %f\", 32)");
	}
	
	@Test
	public void printfFormattedIntegerNegative() {
		assertEquals("-32", Main.format("%d", -32));
		
		totalScore += 1;
	}

	@Test
	public void printfFormattedInteger() {
		assertEquals("32", Main.format("%d", 32));
		
		totalScore += 1;
	}

	@Test
	public void printfFormattedIntegerWithSpaces() {
		assertEquals("32 3.2 32 ", Main.format("%d %.1f %s ", 32, 3.2, "32"));
		assertEquals(" 32 3.2 32", Main.format(" %d %.1f %s", 32, 3.2, "32"));
		
		totalScore += 2;
	}
	
	@Test
	public void printfFormattedDouble() {
		assertEquals("32.1", Main.format("%.1f", 32.1));
		
		totalScore += 1;
	}

	@Test
	public void printfFormattedDoubleWithSpaces() {
		assertEquals(" 32.1 ", Main.format(" %.1f ", 32.1));
		
		totalScore += 1;
	}

	@Test
	public void printfFormattedDoubleZeroDecimals() {
		assertEquals("32", Main.format("%f", 32.1));
		
		totalScore += 2;
	}
	
	@Test
	public void printfFormattedString() {
		assertEquals("Hello World!", Main.format("Hello %s", "World!"));
		
		totalScore += 1;
	}

	@Test
	public void printfFormattedStringWithSpaces() {
		assertEquals(" Hello World! ", Main.format(" %s ", "Hello World!"));
		
		totalScore += 1;
	}
	
	@Test 
	public void printfPiNoWidth() {
		assertEquals("3.14159", Main.format("%.5f", Math.PI));
		
		totalScore += 1;
	}
	
	@Test 
	public void printfPiWidth() {
		assertEquals(10, "   3.14159".length());
		assertEquals("   3.14159", Main.format("%10.5f", Math.PI));
		
		totalScore += 1;
	}

	@Test 
	public void printfPiWidthNegative() {
		assertEquals(10, "  -3.14159".length());
		assertEquals("  -3.14159", Main.format("%10.5f", -Math.PI));
		
		totalScore += 1;
	}

	@Test 
	public void printfPiWidthWithRounding() {
		assertEquals("3.141593", Main.format("%.6f", Math.PI));
		
		totalScore += 2;
	}
	
	@Test
	public void printfHexLowercase() {
		if("0xcafe".equals(Main.format("0x%x", 0xCAFE))) {
			if(extraCreditScore == 0) {
				extraCreditScore += 5;
			}
			lowerCase = true;
			if(lowerCase && upperCase) {
				extraCreditScore += 2;
			}
		}
	}

	@Test
	public void printfHexUppercase() {
		if("0xCAFE".equals(Main.format("0x%X", 0xCAFE))) {
			if(extraCreditScore == 0) {
				extraCreditScore += 5;
			}
			upperCase = true;
			if(lowerCase && upperCase) {
				extraCreditScore += 2;
			}
		}
	}

}
