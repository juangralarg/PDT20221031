package com.daos;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import com.entities.Itr;
import com.entities.Usuario;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class ItrDAO
 */
@Stateless
@LocalBean
public class ItrDAO {

	@PersistenceContext
	private EntityManager em;

	public ItrDAO() {
		// TODO Auto-generated constructor stub

	}
	/*
	 * Periste un Itr en la Base de datos y retorna la Entidad persistida.
	 */
	public Itr insert(Itr itr) throws DAOException {
		try {
			em.persist(itr);
			em.flush();
			return itr;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta un Itr: " + e.getMessage());
		}
	}

	/*
	 * Retorna un ITR en base al ID.
	 * 
	 */
	public Itr findById(Long id) {
		return em.find(Itr.class, id);
	}

	/*
	 * Retorna todos los ITR.
	 */
	public List<Itr> findAll() {
		return em.createQuery("Select i FROM Itr i", Itr.class).getResultList();
	}

	/*
	 * Verificamos que exista una ITR por ID y luego realizamos un Update de los
	 * campos que lleguen por parametro.
	 * Si el ID no existe arroja un NotFoundEntityException.
	 * En caso de que ocurrar un error al hacer el update arroja un DAOException.
	 */
	public Itr update(Itr itr) throws DAOException, NotFoundEntityException {
		try {
			if (findById(itr.getIdItr()) == null)
				throw new NotFoundEntityException("No existe un Itr el con el ID: " + itr.getIdItr());
		
			itr = em.merge(itr);
			em.flush();
			return itr;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrio un error al hacer el update del Itr ", e);
		}
	}

}
