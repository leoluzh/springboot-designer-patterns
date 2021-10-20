package com.lambdasys.designer.patterns.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.lambdasys.designer.patterns.exceptions.ClienteNotFoundException;
import com.lambdasys.designer.patterns.exceptions.EnderecoNotFoundException;
import com.lambdasys.designer.patterns.model.Cliente;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cliente API" , description = "API para gerenciar Clientes")
public interface ClientControllerDocs {

	    @Operation(description = "Cliente creating operation")
	    @ApiResponses(value={
	            @ApiResponse(responseCode = "201", description = "Success Cliente creation.",
	                    content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = Cliente.class))}),
	            @ApiResponse(responseCode = "400", description = "Missing required fields or wrong field range value.", content = @Content)
	    })
	    ResponseEntity<Cliente> create(Cliente Cliente) throws EnderecoNotFoundException;

	    @Operation(description = "Return a list of all Clientes registered in the system.")
	    @ApiResponses(value={
	            @ApiResponse(responseCode = "200" , description = "List of all movies registered in the system."),
	    })
	    ResponseEntity<Page<Cliente>> findAll(Pageable pageable);

	    @Operation(description = "Returns Cliente found by a given id")
	    @ApiResponses(value={
	            @ApiResponse(responseCode = "200", description = "Success Cliente found in the system.",
	                    content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = Cliente.class))}),
	            @ApiResponse(responseCode = "404", description = "Cliente with given id not found.", content = @Content)
	    })
	    ResponseEntity<Cliente> findById(Long id) throws ClienteNotFoundException;

	    @Operation(description="Delete an Cliente found by a given valid id")
	    @ApiResponses(value={
	            @ApiResponse(responseCode = "204", description = "Success Cliente deleted in the system." , content = @Content(mediaType = "application/json")) ,
	            @ApiResponse(responseCode = "404", description = "Cliente with given id not found.", content = @Content)
	    })
	    void deleteById(Long id) throws ClienteNotFoundException;

	    @Operation(description = "Update Cliente by a given id and with request body")
	    @ApiResponses(value={
	            @ApiResponse(responseCode = "200", description = "Success Cliente found in the system and updated.",
	                    content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = Cliente.class))}),
	            @ApiResponse(responseCode = "404", description = "Cliente with given id not found.", content = @Content)
	    })
	    ResponseEntity<Cliente> update(Long id, Cliente Cliente) throws ClienteNotFoundException , EnderecoNotFoundException;	
	
}
