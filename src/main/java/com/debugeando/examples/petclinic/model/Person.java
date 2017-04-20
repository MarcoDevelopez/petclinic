package com.debugeando.examples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Simple JavaBean domain object representing an person.
 * 	
 * @author Marco Lopez
 */
@MappedSuperclass
public class Person extends BaseEntity {

	@Column(name = "first_name")
	protected String	firstName;
	
	@Column(name = "last_name")
	protected String	lastName;
	
	@Column(name = "profile_description")
	protected String	description;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
