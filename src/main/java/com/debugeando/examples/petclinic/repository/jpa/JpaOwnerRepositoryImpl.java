package com.debugeando.examples.petclinic.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.debugeando.examples.petclinic.model.Owner;
import com.debugeando.examples.petclinic.repository.OwnerRepository;

/**
 * JPA implementation of the {@link OwnerRepository} interface.
 * 
 * @author Marco López
 */
@Repository
public class JpaOwnerRepositoryImpl implements OwnerRepository {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Owner> findByLastName(String lastName) throws DataAccessException {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("pkg_owners.sp_find_by_last_name", Owner.class);
		query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
		query.registerStoredProcedureParameter("p_last_name", String.class, ParameterMode.IN);
		query.setParameter("p_last_name", lastName);
		return query.getResultList();
	}

	@Override
	public Owner findById(int id) throws DataAccessException {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("pkg_owners.sp_find_by_id", Owner.class);
		query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
		query.registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN);
		query.setParameter("p_id", id);
		return (Owner) query.getSingleResult();
	}

	@Override
	public void insert(Owner owner) throws DataAccessException {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("pkg_owners.sp_insert", Owner.class);
		query.registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_first_name", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_last_name", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_address", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_city", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_telephone", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_profile_description", String.class, ParameterMode.IN);
		query.setParameter("p_first_name", owner.getFirstName());
		query.setParameter("p_last_name", owner.getLastName());
		query.setParameter("p_address", owner.getAddress());
		query.setParameter("p_city", owner.getCity());
		query.setParameter("p_telephone", owner.getTelephone());
		query.setParameter("p_profile_description", owner.getDescription());
		query.execute();
		owner.setId((Integer) query.getOutputParameterValue("p_id"));
	}
	
	@Override
	public void update(Owner owner) throws DataAccessException {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("pkg_owners.sp_update", Owner.class);
		query.registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_first_name", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_last_name", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_address", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_city", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_telephone", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_profile_description", String.class, ParameterMode.IN);
		query.setParameter("p_id", owner.getId());
		query.setParameter("p_first_name", owner.getFirstName());
		query.setParameter("p_last_name", owner.getLastName());
		query.setParameter("p_address", owner.getAddress());
		query.setParameter("p_city", owner.getCity());
		query.setParameter("p_telephone", owner.getTelephone());
		query.setParameter("p_profile_description", owner.getDescription());
		query.execute();
	}

}
