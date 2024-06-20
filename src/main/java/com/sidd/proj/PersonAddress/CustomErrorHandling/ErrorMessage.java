package com.sidd.proj.PersonAddress.CustomErrorHandling;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

	private HttpStatus status;
	private String message;
	private String detail;
	
}
