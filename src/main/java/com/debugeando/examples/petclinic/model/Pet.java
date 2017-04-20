package com.debugeando.examples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Simple business object representing a pet.
 * 
 * @author Marco Lopez
 */
@Entity
@Table(name = "pets")
@Cacheable(false)
public class Pet extends NamedEntity {

	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date birthDate;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private PetType	type;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	@JsonIgnore
	private Owner owner;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pet", fetch = FetchType.EAGER)
	private List<Visit>	visits;

	public Owner getOwner() {
		return owner;
	}

	public Date getBirthDate() {
		if (this.birthDate == null) {
			this.birthDate = new Date();
		}
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public PetType getType() {
		return type;
	}

	public void setType(PetType type) {
		this.type = type;
	}

	protected List<Visit> getVisitsInternal() {
		if (this.visits == null) {
			this.visits = new ArrayList<Visit>();
		}
		return this.visits;
	}

	protected void setVisits(List<Visit> visits) {
		this.visits = visits;
	}
	
	public List<Visit> getVisits() {
		List<Visit> sortedVisits = new ArrayList<Visit>(getVisitsInternal());
		PropertyComparator.sort(sortedVisits, new MutableSortDefinition("date", false, false));
		return Collections.unmodifiableList(sortedVisits);
	}
	
	public void addVisit(Visit visit) {
		getVisitsInternal().add(visit);
		visit.setPet(this);
	}
	
}
