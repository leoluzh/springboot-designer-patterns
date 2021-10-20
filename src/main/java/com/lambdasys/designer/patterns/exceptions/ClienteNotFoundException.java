package com.lambdasys.designer.patterns.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@SuppressWarnings("serial")
public class ClienteNotFoundException extends Exception {
	
	public ClienteNotFoundException(Long id) {
		super(String.format("Cliente com id %s nao localizado.", id));
	}

}
