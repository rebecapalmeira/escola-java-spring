package soulCode.escola.services.exceptions;

public class ObjectNotFoundCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundCustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFoundCustomException(String message) {
		super(message);
	}

}
