package com.sidd.proj.PersonAddress.CustomErrorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseExceptionHandler {

	@ExceptionHandler(DataMatchedException.class)
	public ResponseEntity<ErrorMessage> handleDataNotFoundException(DataMatchedException message, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FOUND, message.getMessage(),
				request.getDescription(false));

		return ResponseEntity.status(HttpStatus.FOUND).body(errorMessage);
	}

	@ExceptionHandler(NoRecordsAvaliable.class)
	public ResponseEntity<ErrorMessage> handleNoRecordsAvaliable(NoRecordsAvaliable message, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NO_CONTENT, message.getMessage(),
				request.getDescription(false));

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorMessage);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleDataNotFoundException(DataNotFoundException message, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, message.getMessage(),
				request.getDescription(false));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

	
}
