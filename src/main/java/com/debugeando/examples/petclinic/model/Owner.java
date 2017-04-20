package com.debugeando.examples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

/**
 * Simple JavaBean domain object representing an owner.
 * 
 * @author Marco Lopez
 */
@Entity
@Table(name = "owners")
@Cacheable(false)
public class Owner extends Person {

	@Column(name = "address")
	private String	address;
	
	@Column(name = "city")
	private String	city;
	
	@Digits(fraction = 0, integer = 10)
	@Column(name = "telephone")
	private String	telephone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private List<Pet> pets;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	protected List<Pet> getPetsInternal() {
		return pets;
	}

	protected void setPetsInternal(List<Pet> pets) {
		this.pets = pets;
	}
	
	public List<Pet> getPets() {
		List<Pet> sortedPets = new ArrayList<Pet>(getPetsInternal());
		PropertyComparator.sort(sortedPets, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedPets);
	}
	
	public void addPet(Pet pet) {
		getPetsInternal().add(pet);
		pet.setOwner(this);
	}
	
	/**
	 * Return the Pet with the given name, or null if none found for this Owner.
	 * 
	 * @param name to test
	 * @return true if pet name is already in use
	 */
	public Pet getPet(String name) {
		return getPet(name, false);
	}
	
	/**
	 * Return the Pet with the given name, or null if none found for this Owner.
	 * 
	 * @param name to test
	 * @return true if pet name is already in use
	 */
	public Pet getPet(String name, boolean ignoreNew) {
		name = name.toLowerCase();
		for (Pet pet : getPetsInternal()) {
			if (!ignoreNew || !pet.isNew()) {
				String compName = pet.getName();
				compName = compName.toLowerCase();
				if (compName.equals(name)) {
					return pet;
				}
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
								.append("id", this.getId())
								.append("new", this.isNew())
								.append("lastName", this.getLastName())
								.append("firstName", this.getFirstName())
								.append("address", this.address)
								.append("city", this.city)
								.append("telephone", this.telephone)
								.toString();
	}
	
}
