package dev.ricardorosa.Validations.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IncompleteBodyAdvice {
	
	@ResponseBody
	@ExceptionHandler(IncompleteBodyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String incompleteBodyHandler(IncompleteBodyException e) {
		return e.getMessage();
	}

}
