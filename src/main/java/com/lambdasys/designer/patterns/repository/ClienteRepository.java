package com.lambdasys.designer.patterns.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lambdasys.designer.patterns.model.Cliente;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente,Long> {

}
