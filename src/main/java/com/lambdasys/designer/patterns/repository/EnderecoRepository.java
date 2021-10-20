package com.lambdasys.designer.patterns.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lambdasys.designer.patterns.model.Endereco;

@Repository
public interface EnderecoRepository extends PagingAndSortingRepository<Endereco,Long> {

	
	public Optional<Endereco> findByCep(String cep);
	
}
