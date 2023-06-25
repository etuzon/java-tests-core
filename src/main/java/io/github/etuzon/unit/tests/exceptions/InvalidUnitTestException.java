package io.github.etuzon.unit.tests.exceptions;

import java.io.Serial;

/***
 * Exception for unit tests.
 */
public class InvalidUnitTestException extends Exception {
	@Serial
	private static final long serialVersionUID = 1L;

	/***
	 * Constructor.
	 */
	public InvalidUnitTestException() {
		super();
	}

	/***
	 * Constructor.
	 *
	 * @param message Message.
	 */
	public InvalidUnitTestException(String message) {
		super(message);
	}
}