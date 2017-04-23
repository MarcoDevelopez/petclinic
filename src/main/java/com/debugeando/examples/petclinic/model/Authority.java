package com.debugeando.examples.petclinic.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority extends BaseEntity {

	@Column
	@Enumerated(EnumType.STRING)
	private AuthorityName name;
	
	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	private List<User> users;

	public AuthorityName getName() {
		return name;
	}

	public void setName(AuthorityName name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
