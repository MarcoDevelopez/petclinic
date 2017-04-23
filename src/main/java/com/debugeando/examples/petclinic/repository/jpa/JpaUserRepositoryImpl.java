package com.debugeando.examples.petclinic.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.debugeando.examples.petclinic.model.User;
import com.debugeando.examples.petclinic.repository.UserRepository;

/**
 * @author Marco Lopez
 */
@Repository
public class JpaUserRepositoryImpl implements UserRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public User findByUsername(String username) throws DataAccessException {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("pkg_users.sp_find_by_username", User.class);
		query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
		query.registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN);
		query.setParameter("p_username", username);
		User u = (User) query.getSingleResult();
		System.out.println(u.getAuthorities());
		return u;
	}
	
}
