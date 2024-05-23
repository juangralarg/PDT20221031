package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.Tutor;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class TutorDAO
 */
@Stateless
@LocalBean
public class TutorDAO {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public TutorDAO() {
        // TODO Auto-generated constructor stub
    }
    
	/*
	 * Periste de un Tutor en la Base de datos y retorna la Entidad persistida.
	 */

	public Tutor insert(Tutor entidad) throws DAOException {
		try {
			em.persist(entidad);
			em.flush();
			return entidad;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta un Evento: " + e.getMessage());
		}
	}

	/*
	 * Retorna un Tutor en base al ID.
	 * 
	 */
	public Tutor findById(Long id) {
		return em.find(Tutor.class, id);
	}

	/*
	 * Retorna todos los Tutor.
	 * 
	 */
	public List<Tutor> findAll() {
		return em.createQuery("Select i FROM Evento i", Tutor.class).getResultList();

	}

	/*
	 * Verificamos que exista una Tutor por ID y luego realizamos un Update de los
	 * campos que lleguen por parametro.
	 * 
	 */
	public Tutor update(Long id, Tutor entidad) throws DAOException, NotFoundEntityException {
		try {
			entidad = em.merge(entidad);
			em.flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException("Ocurrio un error al hacer el update del Itr ", e);
		}

	}

}
