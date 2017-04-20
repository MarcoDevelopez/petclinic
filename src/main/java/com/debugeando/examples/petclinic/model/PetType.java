package com.debugeando.examples.petclinic.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Marco Lopez
 */
@Entity
@Table(name = "types")
@Cacheable(false)
public class PetType extends NamedEntity {

}
