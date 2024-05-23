package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.AccionConstancia;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class AccionConstanciaDAO
 */
@Stateless
@LocalBean
public class AccionConstanciaDAO {
	
	
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public AccionConstanciaDAO() {
        // TODO Auto-generated constructor stub
    }

	/*
	 * Periste de un AccionConstancia en la Base de datos y retorna la Entidad persistida.
	 */

	public AccionConstancia insert(AccionConstancia entidad) throws DAOException {
		try {
			em.persist(entidad);
			em.flush();
			return entidad;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta un Evento: " + e.getMessage());
		}
	}

	/*
	 * Retorna un AccionConstancia en base al ID.
	 * 
	 */
	public AccionConstancia findById(Long id) {
		return em.find(AccionConstancia.class, id);
	}

	/*
	 * Retorna todas las AccionConstancia.
	 * 
	 */
	public List<AccionConstancia> findAll() {
		return em.createQuery("Select i FROM Evento i", AccionConstancia.class).getResultList();

	}

	/*
	 * Verificamos que exista una AccionConstancia por ID y luego realizamos un Update de los
	 * campos que lleguen por parametro.
	 * 
	 */
	public AccionConstancia update(Long id, AccionConstancia entidad) throws DAOException, NotFoundEntityException {
		try {
			entidad = em.merge(entidad);
			em.flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException("Ocurrio un error al hacer el update del Itr ", e);
		}

	}
    
    
}
