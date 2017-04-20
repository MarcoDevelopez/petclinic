package com.debugeando.examples.petclinic.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.debugeando.examples.petclinic.model.Owner;
import com.debugeando.examples.petclinic.model.Pet;
import com.debugeando.examples.petclinic.model.PetType;
import com.debugeando.examples.petclinic.model.Vet;
import com.debugeando.examples.petclinic.model.Visit;

/**
 * Mostly used as a facade for all PetClinic controllers
 * 
 * @author Marco Lopez
 */
public interface ClinicService {

	// Owners
	
	List<Owner> findOwnerByLastName(String lastName) throws DataAccessException;
	
	Owner findOwnerById(int id) throws DataAccessException;
	
	void saveOwner(Owner owner) throws DataAccessException;
	
	// Pets
	
	Pet findPetById(int id) throws DataAccessException;
	
	void savePet(Pet pet) throws DataAccessException;
	
	List<Pet> findPets() throws DataAccessException;
	
	List<Pet> findPetByName(String name) throws DataAccessException;
	
	// PetTypes
	
	List<PetType> findPetTypes() throws DataAccessException;
	
	// Visits
	
	void saveVisit(Visit visit) throws DataAccessException;
	
	// Vets
	
	List<Vet> findVets() throws DataAccessException;
	
}
