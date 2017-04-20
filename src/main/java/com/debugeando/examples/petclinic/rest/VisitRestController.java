package com.debugeando.examples.petclinic.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.debugeando.examples.petclinic.model.Pet;
import com.debugeando.examples.petclinic.model.Visit;
import com.debugeando.examples.petclinic.service.ClinicService;

/**
 * @author Marco Lopez
 */
@RestController
@RequestMapping("/api")
public class VisitRestController {

	private final ClinicService clinicService;

	@Autowired
	public VisitRestController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}
	
	@RequestMapping(value = "/pets/{petId}/visits", method = RequestMethod.POST)
	public Visit createVisit(@PathVariable int petId, @Valid @RequestBody Visit visit) {
		System.out.println(visit.getId());
		Pet pet = this.clinicService.findPetById(petId);
		visit.setPet(pet);
		System.out.println(visit.getId());
		this.clinicService.saveVisit(visit);
		return visit;
	}
	
	@RequestMapping(value = "/pets/{petId}/visits", method = RequestMethod.GET)
	public List<Visit> showVisits(@PathVariable int petId) {
		return this.clinicService.findPetById(petId).getVisits();
	}
}
