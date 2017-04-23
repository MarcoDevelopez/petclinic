package com.debugeando.examples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.debugeando.examples.petclinic.model.Owner;
import com.debugeando.examples.petclinic.model.Pet;
import com.debugeando.examples.petclinic.model.PetType;
import com.debugeando.examples.petclinic.model.User;
import com.debugeando.examples.petclinic.model.Vet;
import com.debugeando.examples.petclinic.model.Visit;
import com.debugeando.examples.petclinic.repository.OwnerRepository;
import com.debugeando.examples.petclinic.repository.PetRepository;
import com.debugeando.examples.petclinic.repository.UserRepository;
import com.debugeando.examples.petclinic.repository.VetRepository;
import com.debugeando.examples.petclinic.repository.VisitRepository;

/**
 * Mostly used as a facade for all PetClinic controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 * 
 * @author Marco Lopez
 */
@Service
public class ClinicServiceImpl implements ClinicService {
	
	private OwnerRepository ownerRepository;
	private PetRepository petRepository;
	private VisitRepository visitRepository;
	private VetRepository vetRepository;
	private UserRepository userRepository;
	
	@Autowired
	public ClinicServiceImpl(OwnerRepository ownerRepository, 
													 PetRepository petRepository, 
													 VisitRepository visitRepository,
													 VetRepository vetRepository,
													 UserRepository userRepository) {
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
		this.visitRepository = visitRepository;
		this.vetRepository = vetRepository;
		this.userRepository = userRepository;
	}

	public void setOwnerRepository(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	public List<Owner> findOwnerByLastName(String lastName) throws DataAccessException {
		return ownerRepository.findByLastName(lastName);
	}

	@Override
	public Owner findOwnerById(int id) throws DataAccessException {
		return ownerRepository.findById(id);
	}

	@Override
	@Transactional
	public void saveOwner(Owner owner) throws DataAccessException {
		if (owner.getId() == null) {
			ownerRepository.insert(owner);
		} else {
			ownerRepository.update(owner);
		}
	}

	@Override
	public Pet findPetById(int id) throws DataAccessException {
		return petRepository.findById(id);
	}

	@Override
	public void savePet(Pet pet) throws DataAccessException {
		if (pet.getId() == null) {
			petRepository.insert(pet);
		} else {
			petRepository.update(pet);
		}
	}

	@Override
	public List<Pet> findPets() throws DataAccessException {
		return petRepository.findAll();
	}

	@Override
	public List<Pet> findPetByName(String name) throws DataAccessException {
		return petRepository.findByName(name);
	}

	@Override
	public List<PetType> findPetTypes() throws DataAccessException {
		return petRepository.findPetTypes();
	}

	@Override
	public void saveVisit(Visit visit) throws DataAccessException {
		if (visit.getId() == null) {
			visitRepository.insert(visit);
		} else {
			visitRepository.update(visit);
		}
	}

	@Override
	public List<Vet> findVets() throws DataAccessException {
		return vetRepository.findAll();
	}

	@Override
	public User findByUsername(String username) throws DataAccessException {
		return userRepository.findByUsername(username);
	}

}
