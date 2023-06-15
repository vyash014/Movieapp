package com.project.MovieBookingApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT, reason="Movie Name Exception raised- handled by custom exception")
public class MovieNameAlreadyExistsExceptions extends Exception {
	
}
