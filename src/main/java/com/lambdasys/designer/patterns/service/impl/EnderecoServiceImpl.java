package com.lambdasys.designer.patterns.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lambdasys.designer.patterns.exceptions.EnderecoNotFoundException;
import com.lambdasys.designer.patterns.model.Endereco;
import com.lambdasys.designer.patterns.repository.EnderecoRepository;
import com.lambdasys.designer.patterns.service.EnderecoService;
import com.lambdasys.designer.patterns.service.ViaCepService;

import lombok.RequiredArgsConstructor;

/**
 * Endereco service e uma implementacao de padrao strategy por causa da interface endereco service.
 * Mas endereco service tambem e uma fachada que faz uma orquestacao para chamar o viacepservice para localizar
 * endereco em um servico externo.
 * 
 * @author leoluzh
 *
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnderecoServiceImpl implements EnderecoService {

	//injecao de singleton
	private final EnderecoRepository enderecoRepository;
	//injecao de singleton da implenentacao de feign client.
	private final ViaCepService viaCepService;

	@Override
	public Endereco findByCep(String cep) throws EnderecoNotFoundException {
		//buscar no respositoriy (strategy)
		return this.enderecoRepository.findByCep(cep).or( () ->  {
			//buscar no feign - fachada e orquestacao ...
			final var novo = this.viaCepService.findByCep(cep);
			//caso ache salve
			if(Objects.nonNull(novo)) {
				this.enderecoRepository.save(novo);
				return Optional.of(novo);
			}else {
				return Optional.empty();
			}
			//caso na encontrar reportar para cliente via exception.
		}).orElseThrow(() -> new EnderecoNotFoundException(cep));
	}
	
}
