package com.debugeando.examples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.debugeando.examples.petclinic.model.Visit;

/**
 * Repository class for <code>Visit</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data
 * 
 * @author Marco Lopez
 */
public interface VisitRepository {

	/**
	 * Insert a <code>Visit</code> to the data store.
	 * 
	 * @param visit
	 * @see BaseEntity#isNew
	 */
	void insert(Visit visit) throws DataAccessException;
	
	/**
	 * Update a <code>Visit</code> to the data store.
	 * 
	 * @param visit
	 * @see BaseEntity#isNew
	 */
	void update(Visit visit) throws DataAccessException;
	
	List<Visit> findByPetId(Integer petId) throws DataAccessException;
	
}
