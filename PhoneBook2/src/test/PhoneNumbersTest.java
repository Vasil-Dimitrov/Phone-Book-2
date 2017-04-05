package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mainPackage.PhoneNumbers;

public class PhoneNumbersTest {

	@Test
	public void testCompileString() {
//		fail("Not yet implemented");
		assertEquals(null, PhoneNumbers.compile(""));
		assertEquals(null, PhoneNumbers.compile("Hello"));
		assertEquals("+359878000555", PhoneNumbers.compile("00 359 878 000 555 "));
		assertEquals("+359878000555", PhoneNumbers.compile(" 0878 000 555 "));
		assertEquals("+359878000555", PhoneNumbers.compile("-0878-000-555-"));
		assertEquals("+359878000555", PhoneNumbers.compile("- -0878----000  555 - "));
	}

}
