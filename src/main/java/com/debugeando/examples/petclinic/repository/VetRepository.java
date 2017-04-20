package com.debugeando.examples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.debugeando.examples.petclinic.model.Vet;

public interface VetRepository {

	/**
	 * Retrieve all <code>Vet</code> from the data store.
	 * 
	 * @return a <code>List</code> of <code>Vet</code>s
	 */
	List<Vet> findAll() throws DataAccessException;
	
}
