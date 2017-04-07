package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	PhoneNumbersFormatNumTest.class,
	PhoneNumbersNomralizeNumTest.class,
	PhoneNumbersValidateTest.class,
	PhoneNumbersTest.class
})

public class TestSuite {

}
