package com.lambdasys.designer.patterns.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lambdasys.designer.patterns.exceptions.ClienteNotFoundException;
import com.lambdasys.designer.patterns.exceptions.EnderecoNotFoundException;
import com.lambdasys.designer.patterns.model.Cliente;

@Service
public interface ClienteService {

	Iterable<Cliente> findAll();
	Page<Cliente> findAll(Pageable pageable);
	Cliente findById(Long id) throws ClienteNotFoundException;
	Cliente create(Cliente cliente) throws EnderecoNotFoundException;
	Cliente update(Long id,Cliente cliente) throws ClienteNotFoundException, EnderecoNotFoundException;
	void delete(Long id) throws ClienteNotFoundException;
	
}
