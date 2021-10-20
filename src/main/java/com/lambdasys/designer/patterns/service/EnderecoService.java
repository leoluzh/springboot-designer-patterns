package com.lambdasys.designer.patterns.service;

import org.springframework.stereotype.Service;

import com.lambdasys.designer.patterns.exceptions.EnderecoNotFoundException;
import com.lambdasys.designer.patterns.model.Endereco;

@Service
public interface EnderecoService {
	

	Endereco findByCep(String cep) throws EnderecoNotFoundException;
	
}
