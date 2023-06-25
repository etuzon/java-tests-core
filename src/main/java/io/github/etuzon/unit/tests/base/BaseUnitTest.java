package io.github.etuzon.unit.tests.base;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.etuzon.unit.tests.asserts.SoftAssertUnitTest;
import io.github.etuzon.unit.tests.exceptions.InvalidUnitTestException;

public abstract class BaseUnitTest {
	public static final String CLASS_SEPARATOR =
			"================================================================";

	private final Logger logger = Logger.getLogger(BaseUnitTest.class.getName());

	/***
	 * Constructor.
	 */
	protected BaseUnitTest() {
	}

	/***
	 * Before class base test.
	 */
	@BeforeClass
	public void beforeClassBaseTest() {
		logger.info(
				"\n" + CLASS_SEPARATOR + "\n" +
					 "Starting Class [" + super.getClass().getSimpleName() + "]\n"
					 + CLASS_SEPARATOR);
	}

	/***
	 * Before method base test.
	 *
	 * @param method Method.
	 */
	@BeforeMethod
	public void beforeMethodBaseTest(Method method) {
		logger.info("Starting Test [" + super.getClass().getSimpleName()
			   	    + "." + method.getName() + "]");
	}

	/***
	 * After method base test.
	 *
	 * @param result Test result.
	 * @throws InvalidUnitTestException Invalid unit test exception.
	 */
	@AfterMethod
	public void afterMethodBaseTest(ITestResult result) throws InvalidUnitTestException {
		String errors = SoftAssertUnitTest.emptyErrors();

		if (result.isSuccess()) {
			if (errors.isEmpty()) {
				logger.info("Test [" + super.getClass().getSimpleName()
						    + "." + result.getName() + "] PASSED");
			} else {
				throw new InvalidUnitTestException(getInvalidTestExceptionMessage(result, errors));
			}
		} else {
			logger.info("Test [" + super.getClass().getSimpleName()
					    + "." + result.getTestName() + "] FAILED");
			if (!SoftAssertUnitTest.emptyErrors().isEmpty()) {
				throw new InvalidUnitTestException(getInvalidTestExceptionMessage(result, errors));
			}
		}
	}

	/***
	 * Get invalid test exception message.
	 *
	 * @param result Test result.
	 * @param errors Errors.
	 * @return Error message.
	 */
	private String getInvalidTestExceptionMessage(ITestResult result, String errors) {
		return "Missing SoftAssert.assertAll() in test ["
				+ super.getClass().getSimpleName() + "." + result.getName()
				+ "]\nAssert error list:\n" + errors;
	}
}