package com.debugeando.examples.petclinic.repository.jpa;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.debugeando.examples.petclinic.model.Vet;
import com.debugeando.examples.petclinic.repository.VetRepository;

/**
 * JPA implementation of the {@link VetRepository} interface.
 * 
 * @author Marco Lopez
 */
@Repository
public class JpaVetRepositoryImpl implements VetRepository {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Vet> findAll() throws DataAccessException {
		//String sql = "SELECT distinct vet FROM Vet vet left join fetch vet.specialties ORDER BY vet.lastName, vet.firstName";
		//return this.em.createQuery(sql).getResultList();
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("pkg_vets.sp_find_all", Vet.class);
		query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
		return query.getResultList();
	}

}
