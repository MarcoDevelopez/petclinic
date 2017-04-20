package com.debugeando.examples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

/**
 * Simple JavaBean domain object representing a veterinarian.
 * 
 * @author Marco Lopez
 */
@Entity
@Table(name = "vets")
public class Vet extends Person {

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "vet_specialties", 
						 joinColumns = @JoinColumn(name = "vet_id"), 
						 inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private List<Specialty> specialties;

	protected List<Specialty> getSpecialtiesInternal() {
		if (this.specialties == null) {
			this.specialties = new ArrayList<Specialty>();
		}
		return specialties;
	}

	protected void setSpecialtiesInternal(List<Specialty> specialties) {
		this.specialties = specialties;
	}

	@XmlElement
	public List<Specialty> getSpecialties() {
		List<Specialty> sortedSpecs = new ArrayList<Specialty>(getSpecialtiesInternal());
		PropertyComparator.sort(sortedSpecs, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedSpecs);
	}
	
	public int getNrOfSpecialties() {
		return getSpecialtiesInternal().size();
	}
	
	public void addSpecialty(Specialty specialty) {
		getSpecialtiesInternal().add(specialty);
	}
	
}
