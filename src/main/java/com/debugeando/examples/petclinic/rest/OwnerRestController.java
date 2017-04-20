package com.debugeando.examples.petclinic.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.debugeando.examples.petclinic.model.Owner;
import com.debugeando.examples.petclinic.service.ClinicService;

/**
 * @author Marco Lopez
 */
@RestController
@RequestMapping("/api")
public class OwnerRestController {

	private final ClinicService clinicService;

	@Autowired
	public OwnerRestController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}
	
	@RequestMapping(value = "/owners", method = RequestMethod.POST)
	public Owner create (@Valid @RequestBody Owner owner) {
		if (owner.getId()!=null && owner.getId()>0) {
			Owner existingOwner = clinicService.findOwnerById(owner.getId());
			BeanUtils.copyProperties(owner, existingOwner, "pets", "id");
			clinicService.saveOwner(existingOwner);
		} else {
			this.clinicService.saveOwner(owner);
		}
		return owner;
	}
	
	@RequestMapping(value = "/owners", method = RequestMethod.PUT)
	public List<Owner> create(@RequestBody List<Owner> owners) {
		for (Owner owner : owners) {
			this.clinicService.saveOwner(owner);
		}
		return owners;
	}
	
	@RequestMapping(value = "/owners/{id}", method = RequestMethod.GET)
	public Owner find(@PathVariable Integer id) {
		return this.clinicService.findOwnerById(id);
	}
	
	@RequestMapping(value = "/owners", method = RequestMethod.GET)
	public List<Owner> findByLastName(@RequestParam(defaultValue="") String lastName) {
		return this.clinicService.findOwnerByLastName(lastName);
	}
	
}
