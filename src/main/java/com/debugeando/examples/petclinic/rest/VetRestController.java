package com.debugeando.examples.petclinic.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.debugeando.examples.petclinic.model.Vet;
import com.debugeando.examples.petclinic.service.ClinicService;

/**
 * @author Marco Lopez
 */
@RestController
@RequestMapping(value = "/api")
public class VetRestController {

	private final ClinicService clinicService;

	@Autowired
	public VetRestController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@RequestMapping(value = "/vets", method = RequestMethod.GET)
	public List<Vet> getVets() {
		return this.clinicService.findVets();
	}
}
