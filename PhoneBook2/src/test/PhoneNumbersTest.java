package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mainPackage.PhoneNumbers;

public class PhoneNumbersTest {

	@Test
	public void testCompileString() {
		testIfPrefixChange();
		testEmptySpaceRemoval();
		testDashRemoval();
		testDashAndSpaceRemoval();
		testDifferentLength();
		testInvalidNumbers();
	}

	public void testIfPrefixChange() {
		assertEquals("+359878000555", PhoneNumbers.compile("0878000555"));
		assertEquals("+359878000555", PhoneNumbers.compile("00359878000555"));
		assertEquals("+359878000555", PhoneNumbers.compile("+359878000555"));
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

	public void testDifferentLength() {
		assertEquals(null, PhoneNumbers.compile("+3598780005555"));
		assertEquals(null, PhoneNumbers.compile("+35987800055555555"));
		assertEquals(null, PhoneNumbers.compile("+35987800055"));
		assertEquals(null, PhoneNumbers.compile("+359878000"));
	}
	
	public void testInvalidNumbers() {
		assertEquals(null, PhoneNumbers.compile("0778000555"));
		assertEquals(null, PhoneNumbers.compile("0988000555")); // starts as 098 instead of 0(87|88/89){1}
		assertEquals(null, PhoneNumbers.compile("0868000555")); // starts as 086 instead of 0(87|88/89){1}
		assertEquals(null, PhoneNumbers.compile("0871000555")); // starts as 0871 instead of 088(2-9){1}
		assertEquals(null, PhoneNumbers.compile("0880000555")); // starts as 0880 instead of 088(2-9){1}
		assertEquals(null, PhoneNumbers.compile("1878000555")); // starts with 1 instead of 0
		assertEquals(null, PhoneNumbers.compile("-359878000555")); // starts with -359 instead of +359
		assertEquals(null, PhoneNumbers.compile("359878000555")); // starts with 359 instead of +359
		assertEquals(null, PhoneNumbers.compile("0359878000555")); // starts with 0359 instead of +359
		assertEquals(null, PhoneNumbers.compile("+878000555")); // starts with + instead of 0
		assertEquals(null, PhoneNumbers.compile("00878000555")); // starts with 00 instead of 0
	}

}