package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import mainPackage.PhoneNumbers;

public class PhoneNumbersValidateTest {

	@Test
	public void test() {
		// fail("Not yet implemented");
		testCorrectPrefixes();
		testWrongPrefixes();
		testWrongInput();
	}

	public void testCorrectPrefixes() {
		// starting with 08...
		for (int i = 7; i <= 9; i++) {
			for (int u = 2; u <= 9; u++) {
				assertTrue("Valid Number 08" + i + u, PhoneNumbers.validate("08" + i + u + "111999"));
			}
		}
		// starting with +3598...
		for (int i = 7; i <= 9; i++) {
			for (int u = 2; u <= 9; u++) {
				assertTrue("Valid Number +3598" + i + u, PhoneNumbers.validate("+3598" + i + u + "111999"));
			}
		}
		// starting with 003598...
		for (int i = 7; i <= 9; i++) {
			for (int u = 2; u <= 9; u++) {
				assertTrue("Valid Number 003598" + i + u, PhoneNumbers.validate("003598" + i + u + "111999"));
			}
		}
	}

	public void testWrongPrefixes() {
		assertFalse(PhoneNumbers.validate("++359878111333"));
		assertFalse(PhoneNumbers.validate("359878111333"));
		assertFalse(PhoneNumbers.validate("0359878111333"));

		assertFalse(PhoneNumbers.validate("+878111333"));
		assertFalse(PhoneNumbers.validate("878111333"));
		assertFalse(PhoneNumbers.validate("00878111333"));

		assertFalse(PhoneNumbers.validate("+00359878111333"));
		// starting with 0...
		for (int i = 0; i <= 9; i++) {
			for (int u = 0; u <= 6; u++) {
				for (int m = 0; m <= 1; m++) {
					if (i != 8) {
						assertFalse(PhoneNumbers.validate("0" + i + u + m + "111333"));
					}
				}
			}
		}
		// starting with +359...
		for (int i = 0; i <= 9; i++) {
			for (int u = 0; u <= 6; u++) {
				for (int m = 0; m <= 1; m++) {
					if (i != 8) {
						assertFalse(PhoneNumbers.validate("+359" + i + u + m + "111333"));
					}
				}
			}
		}
		// starting with 00359...
		for (int i = 0; i <= 9; i++) {
			for (int u = 0; u <= 6; u++) {
				for (int m = 0; m <= 1; m++) {
					if (i != 8) {
						assertFalse(PhoneNumbers.validate("00359" + i + u + m + "111333"));
					}
				}
			}
		}
	} // end of testWrongPrefixes()

	public void testWrongInput() {
		assertFalse(PhoneNumbers.validate(null));
		assertFalse(PhoneNumbers.validate(""));
		assertFalse(PhoneNumbers.validate(" "));
		assertFalse(PhoneNumbers.validate("Apple"));
		assertFalse(PhoneNumbers.validate("Apple Pie"));
		assertFalse(PhoneNumbers.validate("08781119990"));
		assertFalse(PhoneNumbers.validate("O878111999"));
		assertFalse(PhoneNumbers.validate("08781119990359"));
		assertFalse(PhoneNumbers.validate("087811199"));
	}

}
