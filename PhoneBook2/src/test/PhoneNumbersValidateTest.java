package test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import mainPackage.PhoneNumbers;

public class PhoneNumbersValidateTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		assertTrue("Valid Number 0878", PhoneNumbers.validate("0878111999"));
		assertTrue("Valid Number +359878", PhoneNumbers.validate("+359878111664"));
		assertTrue("Valid Number 00359876", PhoneNumbers.validate("00359876111000"));
		
		assertFalse("Wrong Number, missing digit", PhoneNumbers.validate("087811100"));
		assertFalse(PhoneNumbers.validate("359876111000"));
		assertFalse("Not a number", PhoneNumbers.validate("Hello!"));
		assertFalse(PhoneNumbers.validate("359876111000"));
	
	}

}
