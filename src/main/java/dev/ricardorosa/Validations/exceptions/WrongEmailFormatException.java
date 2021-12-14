package dev.ricardorosa.Validations.exceptions;

public class WrongEmailFormatException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public WrongEmailFormatException() {
		super("The email address must be "
				+ "in the format 'email@something.com[.br]'!");
	}
}
