package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.AccionReclamo;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class AccionReclamo
 */
@Stateless
@LocalBean
public class AccionReclamoDAO {
	
	
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public AccionReclamoDAO() {
        // TODO Auto-generated constructor stub
    }
    
    /*
	 * Periste de un AccionReclamo en la Base de datos y retorna la Entidad persistida.
	 */

	public AccionReclamoDAO insert(AccionReclamoDAO entidad) throws DAOException {
		try {
			em.persist(entidad);
			em.flush();
			return entidad;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta un Evento: " + e.getMessage());
		}
	}

	/*
	 * Retorna un AccionReclamo en base al ID.
	 * 
	 */
	public AccionReclamoDAO findById(Long id) {
		return em.find(AccionReclamoDAO.class, id);
	}

	/*
	 * Retorna todos los AccionReclamo.
	 * 
	 */
	public List<AccionReclamoDAO> findAll() {
		return em.createQuery("Select i FROM Evento i", AccionReclamoDAO.class).getResultList();

	}

	/*
	 * Verificamos que exista una AccionReclamo por ID y luego realizamos un Update de los
	 * campos que lleguen por parametro.
	 * 
	 */
	public AccionReclamoDAO update(Long id, AccionReclamoDAO entidad) throws DAOException, NotFoundEntityException {
		try {
			entidad = em.merge(entidad);
			em.flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException("Ocurrio un error al hacer el update del Itr ", e);
		}

	}

}
