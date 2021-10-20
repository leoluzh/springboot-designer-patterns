package com.lambdasys.designer.patterns.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lambdasys.designer.patterns.model.Endereco;

/**
 * Criando uma fachada/strategy para um servico externo por meio do feing client.
 * Para utilizar o feing e necessario incluir anotacao enable na classe de start do projeto.
 * @author leoluzh
 *
 */

@FeignClient(name="viacep",url = "https://viacep.com.br/ws")
public interface ViaCepService {

	@GetMapping(path = "/{cep}/json")
	Endereco findByCep(@PathVariable("cep") String cep);
	
}
