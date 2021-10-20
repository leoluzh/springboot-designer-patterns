package com.lambdasys.designer.patterns.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@SuppressWarnings("serial")
public class EnderecoNotFoundException extends Exception {

	public EnderecoNotFoundException(Long id) {
		super(String.format("Endereco com id %s nao localizado", id));
	}
	
	public EnderecoNotFoundException(String cep) {
		super(String.format("Endereco com cep %s nao localizado", cep));
	}
	
}
