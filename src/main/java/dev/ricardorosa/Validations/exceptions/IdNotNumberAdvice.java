package dev.ricardorosa.Validations.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class IdNotNumberAdvice {
	
	@ResponseBody
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	ResponseEntity<String> idNotNumberHandler() {
	    	return new ResponseEntity<String>(
	    			"The ID must be a number.", 
	    			HttpStatus.BAD_REQUEST);
	}
}



