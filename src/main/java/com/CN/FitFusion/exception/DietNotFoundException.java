package com.CN.FitFusion.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DietNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /*
        Extend the class with RuntimeException and create a constructor with
        (String message) as argument in order to handle custom exception message.
    */
	
	public DietNotFoundException(String msg) {
		super(msg);
	}

}
