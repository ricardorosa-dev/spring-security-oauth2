package dev.ricardorosa.Validations.exceptions;

public class AlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AlreadyExistsException(
			String item, String attributeName, String attribute) {
		super(
				"The " + item + " with the " + attributeName + 
				" '" + attribute + "' already exists!"
		);
	}
}
