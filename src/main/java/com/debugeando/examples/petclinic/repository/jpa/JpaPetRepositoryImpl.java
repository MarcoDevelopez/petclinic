package com.debugeando.examples.petclinic.repository.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.debugeando.examples.petclinic.model.Pet;
import com.debugeando.examples.petclinic.model.PetType;
import com.debugeando.examples.petclinic.repository.PetRepository;

/**
 * JPA implementation of the {@link PetRepository} interface.
 * 
 * @author Marco Lopez
 */
@Repository
public class JpaPetRepositoryImpl implements PetRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Pet findById(int id) throws DataAccessException {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("pkg_pets.sp_find_by_id", Pet.class);
		query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
		query.registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN);
		query.setParameter("p_id", id);
		return (Pet) query.getSingleResult();
	}

	@Override
	public void insert(Pet pet) throws DataAccessException {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("pkg_pets.sp_insert", Pet.class);
		query.registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_birth_date", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_type_id", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_owner_id", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_profile_description", String.class, ParameterMode.IN);
		query.setParameter("p_name", pet.getName());
		query.setParameter("p_birth_date", pet.getBirthDate());
		query.setParameter("p_type_id", pet.getType().getId());
		query.setParameter("p_owner_id", pet.getOwner().getId());
		query.setParameter("p_profile_description", null);
		query.execute();
		pet.setId((Integer) query.getOutputParameterValue("p_id"));
	}

	@Override
	public void update(Pet pet) throws DataAccessException {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("pkg_pets.sp_update", Pet.class);
		query.registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_birth_date", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_type_id", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_owner_id", Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_profile_description", String.class, ParameterMode.IN);
		query.setParameter("p_id", pet.getId());
		query.setParameter("p_name", pet.getName());
		query.setParameter("p_birth_date", pet.getBirthDate());
		query.setParameter("p_type_id", pet.getType().getId());
		query.setParameter("p_owner_id", pet.getOwner().getId());
		query.setParameter("p_profile_description", null);
		query.execute();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pet> findAll() throws DataAccessException {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("pkg_pets.sp_find_all", Pet.class);
		query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pet> findByName(String query) throws DataAccessException {
		StoredProcedureQuery q = this.em.createStoredProcedureQuery("pkg_pets.sp_find_by_name", Pet.class);
		q.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
		q.registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN);
		q.setParameter("p_name", query);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PetType> findPetTypes() throws DataAccessException {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("pkg_types.sp_find_pet_types", PetType.class);
		query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
		return query.getResultList();
	}

}
