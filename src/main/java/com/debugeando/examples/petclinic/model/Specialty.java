package com.debugeando.examples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Models a {@link Vet Vet's} specialty (for example, dentistry).
 * 
 * @author Marco Lopez
 */
@Entity
@Table(name= "specialties")
public class Specialty extends NamedEntity {

}
