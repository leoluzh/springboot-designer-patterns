package com.lambdasys.designer.patterns.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lambdasys.designer.patterns.exceptions.ClienteNotFoundException;
import com.lambdasys.designer.patterns.exceptions.EnderecoNotFoundException;
import com.lambdasys.designer.patterns.model.Cliente;
import com.lambdasys.designer.patterns.repository.ClienteRepository;
import com.lambdasys.designer.patterns.service.ClienteService;
import com.lambdasys.designer.patterns.service.EnderecoService;

import lombok.RequiredArgsConstructor;

//Utlizado para determinar strategy e para ser um bean gerenciado pelo IoC do Spring.
@Service
//Utilizar para incluir as dependencias via construtor ...
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteServiceImpl implements ClienteService {

	//injetar singleton de uma implementacao de strategy de cliente repository.
	private final ClienteRepository repository;
	
	//injetar singleton de uma implmentacao de strategy/fachade de endereco service. 
	private final EnderecoService enderecoService;
	
	@Override
	public Iterable<Cliente> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	@Override
	public Cliente findById(Long id) throws ClienteNotFoundException {
		//localizar registro
		return repository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id) );
	}

	@Override
	public Cliente create(Cliente cliente) throws EnderecoNotFoundException {
		//fazer orquestacao ... localizar endereco
		final var endereco = this.enderecoService.findByCep(cliente.getEndereco().getCep());
		//adicionar informacoes adicionais de endereco para retorno
		cliente.setEndereco(endereco);
		return repository.save(cliente);
	}

	@Override
	public Cliente update(Long id , Cliente cliente) throws ClienteNotFoundException , EnderecoNotFoundException {
		findById(id);
		cliente.setId(id);
		//fazer orquestacao ... localizar endereco
		final var endereco = this.enderecoService.findByCep(cliente.getEndereco().getCep());
		//adicionar informacoes adicionais de endereco para retorno.
		cliente.setEndereco(endereco);
		return repository.save(cliente);
	}

	@Override
	public void delete(Long id) throws ClienteNotFoundException {
		findById(id);
		repository.deleteById(id);
	}
	
	

}
