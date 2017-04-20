package com.debugeando.examples.petclinic.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.debugeando.examples.petclinic.model.Owner;
import com.debugeando.examples.petclinic.model.Pet;
import com.debugeando.examples.petclinic.model.PetType;
import com.debugeando.examples.petclinic.service.ClinicService;

/**
 * @author Marco Lopez
 */
@RestController
@RequestMapping("/api")
public class PetRestController {

	private final ClinicService clinicService;

	@Autowired
	public PetRestController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}
	
	@RequestMapping(value = "/pets/types", method = RequestMethod.GET)
	public List<PetType> populatePetTypes() {
		return this.clinicService.findPetTypes();
	}
	
	@RequestMapping(value = "/owners/{ownerId}/pets", method = RequestMethod.POST)
	public Pet addPet(@PathVariable("ownerId") int ownerId, @RequestBody Pet pet) {
		Owner owner = this.clinicService.findOwnerById(ownerId);
		owner.addPet(pet);
		this.clinicService.savePet(pet);
		return pet;
	}
	
	@RequestMapping(value = "/pets", method = RequestMethod.GET)
	public List<Pet> findAllPets() {
		return this.clinicService.findPets();
	}
	
	@RequestMapping(value = "/pets/search", method = RequestMethod.GET)
	public List<Pet> search(@RequestParam String q) {
		return this.clinicService.findPetByName(q);
	}
	
}
