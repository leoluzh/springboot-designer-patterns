package com.lambdasys.designer.patterns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lambdasys.designer.patterns.exceptions.ClienteNotFoundException;
import com.lambdasys.designer.patterns.exceptions.EnderecoNotFoundException;
import com.lambdasys.designer.patterns.model.Cliente;
import com.lambdasys.designer.patterns.service.ClienteService;

import lombok.RequiredArgsConstructor;

//determina que a classe ser um componente do tipo rest controller, seu ciclo de vida sera gerenciado pelo IoC do Spring.
@RestController
//configurando path para recurso
@RequestMapping("/api/v1/clientes")
//configurando injecao de dependencias pelo construtor.
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController implements ClientControllerDocs {
	
	//spring injeta uma instancia de clienteservice pelo construtor - essa instancia por default e um singleton 
	//spring tem outras estrategias - singleton e prototype.
	//spring tem mais outros tres tipos de escopos para web - application/session/request
	private final ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<Page<Cliente>> findAll(Pageable pageable){
		//delegate to service ...
		return ResponseEntity.ok(this.clienteService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable("id") Long id) throws ClienteNotFoundException{
		return ResponseEntity.ok(this.clienteService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) throws EnderecoNotFoundException{
		return ResponseEntity.ok(this.clienteService.create(cliente));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody Cliente cliente) throws ClienteNotFoundException , EnderecoNotFoundException {
		return ResponseEntity.ok(this.clienteService.update(id,cliente));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Long id) throws ClienteNotFoundException {
		this.clienteService.delete(id);
	}
	
}
