package dev.ricardorosa.Validations.exceptions;

public class NotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public NotFoundException(String entity, String attribute, String value) {
		super("The " + entity + 
				" with the " + attribute + 
				" " + value + " doesn't exist.");
	}
}
