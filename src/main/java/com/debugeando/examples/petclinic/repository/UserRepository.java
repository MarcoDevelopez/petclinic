package com.debugeando.examples.petclinic.repository;

import org.springframework.dao.DataAccessException;

import com.debugeando.examples.petclinic.model.User;

/**
 * @author Marco Lopez
 */
public interface UserRepository {

	User findByUsername(String username) throws DataAccessException;
	
}
