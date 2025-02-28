import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Excercise_1 {

	// @Test
	void testAssertEqual() 
	{
		String expected = "codingeek";
		String actual = "codinggeek";
		assertEquals(expected, actual,"Test to see if values are equal");
	}  // left side becomes green if test passes, if not red

	
	@Test   // test pass
	void testAssertNotEquals() 
	{
	String expected = "codingeek";
	String actual = "codinggeek";
	assertNotEquals(expected, actual, "Test to see if values are not equal");
	}

}
