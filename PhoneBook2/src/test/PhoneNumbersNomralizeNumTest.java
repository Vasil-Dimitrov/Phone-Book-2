package test;
import static org.junit.Assert.*;

import org.junit.Test;

import mainPackage.PhoneNumbers;

public class PhoneNumbersNomralizeNumTest {

	@Test
	public void testNormalizeNum() {
//		fail("Not yet implemented");
		assertEquals("+359878111000", PhoneNumbers.normalizeNum("0878111000"));
		assertEquals("+359878111000", PhoneNumbers.normalizeNum("00359878111000"));
		assertEquals("+359878111000", PhoneNumbers.normalizeNum("+359878111000"));		
	}

}
