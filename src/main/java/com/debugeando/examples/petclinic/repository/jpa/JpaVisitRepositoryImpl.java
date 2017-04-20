package com.debugeando.examples.petclinic.repository.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.debugeando.examples.petclinic.model.Visit;
import com.debugeando.examples.petclinic.repository.VisitRepository;

/**
 * JPA implementation of the ClinicService interface using EntityManager
 * 
 * @author Marco Lopez
 */
@Repository
public class JpaVisitRepositoryImpl implements VisitRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void insert(Visit visit) throws DataAccessException {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("pkg_visits.sp_insert", Visit.class);
		query.registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_pet_id", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_visit_date", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_description", String.class, ParameterMode.IN);
		query.setParameter("p_pet_id", visit.getPet().getId());
		query.setParameter("p_visit_date", visit.getDate());
		query.setParameter("p_description", visit.getDescription());
		query.execute();
		visit.setId((Integer) query.getOutputParameterValue("p_id"));
	}

	@Override
	public void update(Visit visit) throws DataAccessException {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("pkg_visits.sp_update", Visit.class);
		query.registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_pet_id", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_visit_date", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_description", String.class, ParameterMode.IN);
		query.setParameter("p_id", visit.getId());
		query.setParameter("p_pet_id", visit.getPet().getId());
		query.setParameter("p_visit_date", visit.getDate());
		query.setParameter("p_description", visit.getDescription());
		query.execute();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Visit> findByPetId(Integer petId) throws DataAccessException {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("pkg_visits.sp_find_by_pet_id", Visit.class);
		query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
		query.registerStoredProcedureParameter("p_pet_id", Integer.class, ParameterMode.IN);
		query.setParameter("p_pet_id", petId);
		return query.getResultList();
	}

}
