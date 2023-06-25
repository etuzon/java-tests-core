package io.github.etuzon.unit.tests.exceptions;

import io.github.etuzon.unit.tests.asserts.SoftAssertUnitTest;

import java.io.Serial;

/***
 * Exception for unit tests.
 */
public class AutomationUnitTestException extends Exception {

	@Serial
	private static final long serialVersionUID = 1L;

	/***
	 * Constructor.
	 */
	public AutomationUnitTestException() {
		this("");
	}

	/***
	 * Constructor.
	 *
	 * @param e Exception.
	 */
	public AutomationUnitTestException(Exception e) {
		this(e.getMessage() + "\n" + getExceptionStacktrace(e));
	}

	/***
	 * Constructor.
	 *
	 * @param message Message.
	 */
	public AutomationUnitTestException(String message) {
		super(message);
		super.addSuppressed(new Throwable(SoftAssertUnitTest.emptyErrors()));
	}

	/***
	 * Constructor.
	 *
	 * @param e Exception.
	 */
	private static String getExceptionStacktrace(Exception e) {
		StringBuilder sb = new StringBuilder();

		for (StackTraceElement stackElement : e.getStackTrace()) {
			sb.append(stackElement.getClassName())
			  .append(".")
			  .append(stackElement.getMethodName())
			  .append("(")
			  .append(stackElement.getFileName())
			  .append(":")
			  .append(stackElement.getLineNumber())
			  .append(")\n");
		}

		return sb.toString();
	}
}