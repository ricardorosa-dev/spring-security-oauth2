package dev.ricardorosa.Validations.exceptions;

public class IncompleteBodyException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public IncompleteBodyException(String entity, String attributes) {
		super("Incomplete body: "
				+ "the " + entity + " must have "
				+ attributes + " attributes.");
	}
}
