package com.debugeando.examples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.debugeando.examples.petclinic.model.Owner;

/**
 * Repository class for <code>Owner</code> domain objects All method names are compilant whit Spring Data naming
 * conventions so this interface can easily be extended for Spring Data.
 * 
 * @author Marco López
 */
public interface OwnerRepository {

	/**
	 * Retrieve <code>Owner</code> from the data store by last name, returning all owners whose last name <i>starts</i>
	 * with the given name.
	 * 
	 * @param lastName Value to search for
	 * @return a <code>Collection</code> of matching <code>Owner</code> (or an empty <code>Collection</code> if none
	 * 					found)
	 */
	List<Owner> findByLastName(String lastName) throws DataAccessException;
	
	/**
	 * Retrieve an <code>Owner</code> from the data store by id.
	 * 
	 * @param id the id to search for
	 * @return the <code>Owner</code> if foudn
	 */
	Owner findById(int id) throws DataAccessException;
	
	/**
	 * Save an <code>Owner</code> to the data store, either inserting.
	 * 
	 * @param owner the <code>Owner</code> to save
	 * @see BaseEntity#isNew
	 */
	void insert(Owner owner) throws DataAccessException;
	
	/**
	 * Save an <code>Owner</code> to the data store, either updating.
	 * 
	 * @param owner the <code>Owner</code> to save
	 * @see BaseEntity#isNew
	 */
	void update(Owner owner) throws DataAccessException;
}
