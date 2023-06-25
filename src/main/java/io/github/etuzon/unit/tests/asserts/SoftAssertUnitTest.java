package io.github.etuzon.unit.tests.asserts;


import java.util.logging.Logger;

import org.testng.Assert;

/***
 * Soft assert class for unit tests.
 */
public class SoftAssertUnitTest {

	/***
	 * Enum for info message log level.
	 */
	public enum InfoMessageLogLevelEnum {
		TRACE, DEBUG, INFO;
	}

	private static final Logger logger =
			Logger.getLogger(SoftAssertUnitTest.class.getName());

	private static String errors = "";
	private static InfoMessageLogLevelEnum infoMessageLevel =
			InfoMessageLogLevelEnum.INFO;

	/***
	 * Assert true now.
	 *
	 * @param condition Assert condition.
	 * @param errorMessage Error message.
	 */
	public static void assertTrueNow(boolean condition, String errorMessage) {
		assertTrue(condition, errorMessage);
		assertAll();
	}

	/***
	 * Assert true now.
	 *
	 * @param condition Assert condition.
	 * @param errorMessage Error message.
	 * @param infoMessage Info message.
	 */
	public static void assertTrueNow(
			boolean condition, String errorMessage, String infoMessage) {
		assertTrue(condition, errorMessage, infoMessage);
		assertAll();
	}

	/***
	 * Assert not null now.
	 *
	 * @param object Assert object.
	 * @param errorMessage Error message.
	 */
	public static void assertNotNullNow(Object object, String errorMessage) {
		assertNotNullNow(object, errorMessage, null);
	}

	/***
	 * Assert not null now.
	 *
	 * @param object Assert object.
	 * @param errorMessage Error message.
	 * @param infoMessage Info message.
	 */
	public static void assertNotNullNow(
			Object object, String errorMessage, String infoMessage) {
		assertNotNull(object, errorMessage, infoMessage);
		assertAll();
	}

	/***
	 * Fail now.
	 *
	 * @param errorMessage Error message.
	 */
	public static void failNow(String errorMessage) {
		assertTrueNow(false, errorMessage);
	}

	/***
	 * Assert not null.
	 *
	 * @param object Assert object.
	 * @param errorMessage Error message.
	 * @return True if object is not null, otherwise false.
	 */
	public static boolean assertNotNull(Object object, String errorMessage) {
		return assertNotNull(object, errorMessage, null);
	}

	/***
	 * Assert not null.
	 *
	 * @param object Assert object.
	 * @param errorMessage Error message.
	 * @param infoMessage Info message.
	 * @return True if object is not null, otherwise false.
	 */
	public static boolean assertNotNull(
			Object object, String errorMessage, String infoMessage) {
		return assertTrue(object != null, errorMessage, infoMessage);
	}

	/***
	 * Assert true.
	 *
	 * @param condition Assert condition.
	 * @param errorMessage Error message.
	 * @return True if condition is true, otherwise false.
	 */
	public static boolean assertTrue(boolean condition, String errorMessage) {
		return assertTrue(condition, errorMessage, null);
	}

	/***
	 * Assert true.
	 *
	 * @param condition Assert condition.
	 * @param errorMessage Error message.
	 * @param infoMessage Info message.
	 * @return True if condition is true, otherwise false.
	 */
	public static boolean assertTrue(
			boolean condition, String errorMessage, String infoMessage) {
		if (infoMessage != null) {
			printInfoMessage(infoMessage);
		}

		if (!condition) {
			errorMessage = "[Assertion Error] " + errorMessage;

			logger.severe(errorMessage);

			if (!errors.isEmpty()) {
				errors += "\n";
			}

			errors += errorMessage;
		}

		return condition;
	}

	/***
	 * Fail.
	 *
	 * @param errorMessage Error message.
	 */
	public static void fail(String errorMessage) {
		assertTrue(false, errorMessage);
	}

	/***
	 * Assert all.
	 */
	public static void assertAll() {
		String temp = errors;
		errors = "";
		Assert.assertTrue(temp.isEmpty(), temp);
	}

	/***
	 * Get current errors.
	 *
	 * @return Current errors.
	 */
	public static String getCurrentErrors() {
		return errors;
	}

	/***
	 * Empty errors.
	 *
	 * @return Current errors.
	 */
	public static String emptyErrors() {
		String temp = errors;
		errors = "";
		return temp;
	}

	/***
	 * Set info message log level.
	 *
	 * @param level Info message log level.
	 */
	public static void setInfoMessageLogLevel(InfoMessageLogLevelEnum level) {
		infoMessageLevel = level;
	}

	/***
	 * Print info message.
	 *
	 * @param message Info message.
	 */
	private static void printInfoMessage(String message) {
		if (infoMessageLevel == InfoMessageLogLevelEnum.TRACE) {
			logger.fine(message);
		} else if (infoMessageLevel == InfoMessageLogLevelEnum.DEBUG) {
			logger.fine(message);
		} else if (infoMessageLevel == InfoMessageLogLevelEnum.INFO) {
			logger.info(message);
		}
	}
}