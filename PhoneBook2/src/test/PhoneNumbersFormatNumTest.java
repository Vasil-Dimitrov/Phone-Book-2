package test;
import static org.junit.Assert.*;

import org.junit.Test;

import mainPackage.PhoneNumbers;

public class PhoneNumbersFormatNumTest {

	@Test
	public void testFormatNum() {
		//fail("Not yet implemented");
		assertEquals("0878111000", PhoneNumbers.formatNum("0-878-111-000"));
		assertEquals("0878111000", PhoneNumbers.formatNum("-0-878-111-000-"));
		assertEquals("0878111000", PhoneNumbers.formatNum("0 878 111 000"));
		assertEquals("0878111000", PhoneNumbers.formatNum(" 0-878-111-000 "));
		assertEquals("0878111000", PhoneNumbers.formatNum(" - - 0    878---111- - 000- - -"));
	}

}
