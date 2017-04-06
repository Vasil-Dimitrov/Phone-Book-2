package test;
import static org.junit.Assert.*;

import org.junit.Test;

import mainPackage.PhoneNumbers;

public class PhoneNumbersFormatNumTest {

	@Test
	public void testFormatNum() {
		//fail("Not yet implemented");
		testDashRemoval();
		testEmptySpaceRemoval();
		testDashAndSpaceRemoval();
	}
	
	public void testEmptySpaceRemoval() {
		assertEquals(null, PhoneNumbers.compile(""));
		assertEquals(null, PhoneNumbers.compile(" "));
		assertEquals(null, PhoneNumbers.compile("         "));
		assertEquals(null, PhoneNumbers.compile("				")); // tabs
		// starting with 0878
		assertEquals("+359878000555", PhoneNumbers.compile("0878000555 "));
		assertEquals("+359878000555", PhoneNumbers.compile("0878	000555")); // tab
		assertEquals("+359878000555", PhoneNumbers.compile(" 0878000555"));
		assertEquals("+359878000555", PhoneNumbers.compile(" 0878000555 "));
		assertEquals("+359878000555", PhoneNumbers.compile("08780 00555"));
		assertEquals("+359878000555", PhoneNumbers.compile(" 08780 00555 "));
		assertEquals("+359878000555", PhoneNumbers.compile(" 0 8 7 8 0 0 0 5 5 5 "));
		// starting with 00359
		assertEquals("+359878000555", PhoneNumbers.compile("00359878000555 "));
		assertEquals("+359878000555", PhoneNumbers.compile("00359	878000555")); // tab
		assertEquals("+359878000555", PhoneNumbers.compile(" 00359878000555"));
		assertEquals("+359878000555", PhoneNumbers.compile(" 00359878000555 "));
		assertEquals("+359878000555", PhoneNumbers.compile("003598780 00555"));
		assertEquals("+359878000555", PhoneNumbers.compile(" 003598780 00555 "));
		assertEquals("+359878000555", PhoneNumbers.compile(" 0 0 3 5 9 8 7 8 0 0 0 5 5 5 "));
		// starts with +359
		assertEquals("+359878000555", PhoneNumbers.compile("+359878000555 "));
		assertEquals("+359878000555", PhoneNumbers.compile("+359	878000555")); // tab
		assertEquals("+359878000555", PhoneNumbers.compile(" +359878000555"));
		assertEquals("+359878000555", PhoneNumbers.compile(" +359878000555 "));
		assertEquals("+359878000555", PhoneNumbers.compile("+3598780 00555"));
		assertEquals("+359878000555", PhoneNumbers.compile(" +3598780 00555 "));
		assertEquals("+359878000555", PhoneNumbers.compile(" + 3 5 9 8 7 8 0 0 0 5 5 5 "));
	}

	public void testDashRemoval() {
		assertEquals(null, PhoneNumbers.compile("-"));
		assertEquals(null, PhoneNumbers.compile("--"));
		assertEquals(null, PhoneNumbers.compile("-------------------"));
		// starting with 0878
		assertEquals("+359878000555", PhoneNumbers.compile("0878000555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("-0878000555"));
		assertEquals("+359878000555", PhoneNumbers.compile("-0878000555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("08780-00555"));
		assertEquals("+359878000555", PhoneNumbers.compile("-08780-00555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("-0-8-7-8-0-0-0-5-5-5-"));
		// starting with 00359
		assertEquals("+359878000555", PhoneNumbers.compile("00359878000555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("-00359878000555"));
		assertEquals("+359878000555", PhoneNumbers.compile("-00359878000555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("003598780-00555"));
		assertEquals("+359878000555", PhoneNumbers.compile("-003598780-00555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("-0-0-3-5-9-8-7-8-0-0-0-5-5-5-"));
		// starts with +359
		assertEquals("+359878000555", PhoneNumbers.compile("+359878000555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("-+359878000555"));
		assertEquals("+359878000555", PhoneNumbers.compile("-+359878000555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("+3598780-00555"));
		assertEquals("+359878000555", PhoneNumbers.compile("-+3598780-00555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("-+-3-5-9-8-7-8-0-0-0-5-5-5-"));
	}

	public void testDashAndSpaceRemoval() {
		assertEquals(null, PhoneNumbers.compile("- "));
		assertEquals(null, PhoneNumbers.compile(" - - "));
		assertEquals(null, PhoneNumbers.compile("- - -"));
		assertEquals(null, PhoneNumbers.compile(" - - - - - - - - - - - - - - - - - - - "));
		// starting with 0878
		assertEquals("+359878000555", PhoneNumbers.compile(" 0878000555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("-0878000555 "));
		assertEquals("+359878000555", PhoneNumbers.compile("- 0878000555- "));
		assertEquals("+359878000555", PhoneNumbers.compile("08 780-005 55"));
		assertEquals("+359878000555", PhoneNumbers.compile("-087 80-005 55-"));
		assertEquals("+359878000555", PhoneNumbers.compile("-0- 8-7-8-0-0 -0-5-5- 5-"));
		// starting with 00359
		assertEquals("+359878000555", PhoneNumbers.compile(" 00359878000555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("-00359878000555 "));
		assertEquals("+359878000555", PhoneNumbers.compile(" -003598 78000555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("00359 8780-00 555"));
		assertEquals("+359878000555", PhoneNumbers.compile("-0035 98780-005 55-"));
		assertEquals("+359878000555", PhoneNumbers.compile("- 0-0-3- 5-9-8-7- 8-0-0-0-5-5- 5-"));
		// starts with +359
		assertEquals("+359878000555", PhoneNumbers.compile(" +359878000555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("-+359878000555 "));
		assertEquals("+359878000555", PhoneNumbers.compile("-+35987 8000555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("+359 8780 00555"));
		assertEquals("+359878000555", PhoneNumbers.compile("-+359 8780 00555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("-+- 3-5 -9-8 -7- 8-0- 0-0-5-5  -5-"));
	}

}
