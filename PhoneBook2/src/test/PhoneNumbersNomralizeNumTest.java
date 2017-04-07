package test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mainPackage.PhoneNumbers;

public class PhoneNumbersNomralizeNumTest {
	@BeforeClass
	public static void beforeClass() {
		System.out.println("\nPhoneNumbersNomralizeNumTest class started!\n");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("\nPhoneNumbersNomralizeNumTest class ended!\n");
	}

	@Before
	public void before() {
		System.out.println("Test method started!");
	}

	@After
	public void after() {
		System.out.println("Test method ended");
	}

	@Test(timeout=2)
	public void testNormalizeNum() {
//		fail("Not yet implemented");
		testCorrectPrefixes();
		testWrongInput();
	}
	
	public void testCorrectPrefixes() {
		// starting with 08...
		for (int i = 7; i <= 9; i++) {
			for (int u = 2; u <= 9; u++) {
				assertEquals("Error at i: " + i + " and u: " + u,"+3598" + i + u + "111999", PhoneNumbers.normalizeNum("08" + i + u + "111999"));
			}
		}
		// starting with +3598...
		for (int i = 7; i <= 9; i++) {
			for (int u = 2; u <= 9; u++) {
				assertEquals("+3598" + i + u + "111999", PhoneNumbers.normalizeNum("+3598" + i + u + "111999"));
			}
		}
		// starting with 003598...
		for (int i = 7; i <= 9; i++) {
			for (int u = 2; u <= 9; u++) {
				assertEquals("+3598" + i + u + "111999", PhoneNumbers.normalizeNum("003598" + i + u + "111999"));
			}
		}
	}


	public void testWrongInput() {
		assertEquals(null, PhoneNumbers.normalizeNum(null));
		assertEquals(null, PhoneNumbers.normalizeNum(""));
		assertEquals(null, PhoneNumbers.normalizeNum(" "));
		assertEquals(null, PhoneNumbers.normalizeNum("087811199"));
	}

}
