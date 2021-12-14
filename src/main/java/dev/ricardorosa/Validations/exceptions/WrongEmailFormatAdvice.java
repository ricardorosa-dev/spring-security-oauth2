package dev.ricardorosa.Validations.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WrongEmailFormatAdvice {
	
	@ResponseBody
	@ExceptionHandler(WrongEmailFormatException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	String WrongEmailFormatHandler(WrongEmailFormatException e) {
		return e.getMessage();
	}
}
