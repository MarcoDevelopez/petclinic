package com.debugeando.examples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.debugeando.examples.petclinic.model.Pet;
import com.debugeando.examples.petclinic.model.PetType;

/**
 * Repository class for <code>Pet</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interfaces can easily be exnteded for Spring Data.
 * 
 * @author Marco Lopez
 */
public interface PetRepository {

	/**
	 * Retrieve all <code>PetType</code> from the data store.
	 * 
	 * @return a <code>Collection</code> of <code>PetType</code>
	 */
	List<PetType> findPetTypes() throws DataAccessException;
	
	/**
	 * Retrieve a <code>Pet</code> from the data store by id.
	 * 
	 * @param id the id to search for
	 * @return the <code>Pet</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException
	 * 					if not found
	 */
	Pet findById(int id) throws DataAccessException;
	
	/**
	 * Insert a <code>Pet</code> to the data store.
	 * 
	 * @param pet the <code>Pet</code> to insert
	 * @see BaseEntity#isNew
	 */
	void insert(Pet pet) throws DataAccessException;
	
	/**
	 * Update a <code>Pet</code> to the data store.
	 * 
	 * @param pet the <code>Pet</code> to update
	 * @see BaseEntity#isNew
	 */
	void update(Pet pet) throws DataAccessException;
	
	/**
	 * Get all pets
	 * @return
	 */
	List<Pet> findAll() throws DataAccessException;
	
	/**
	 * Get all pets by name
	 * @return
	 */
	List<Pet> findByName(String query) throws DataAccessException;
	
}
