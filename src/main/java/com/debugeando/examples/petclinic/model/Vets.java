package com.debugeando.examples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple domain object representing a list of veterinarians. Mostly here to be used for the 'vets' {@link
 * org.springframework.web.servlet.view.xml.MarshallingView}.
 * 
 * @author Marco Lopez
 */
@XmlRootElement
public class Vets {

	public List<Vet> vets;

	@XmlElement
	public List<Vet> getVetsList() {
		if (vets == null) {
			vets = new ArrayList<Vet>();
		}
		return vets;
	}

}
