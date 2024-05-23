package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.Reclamo;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class ReclamoDAO
 */
@Stateless
@LocalBean
public class ReclamoDAO {

    /**
     * Default constructor. 
     */
	
	
	@PersistenceContext
	private EntityManager em;

	
    public ReclamoDAO() {
        // TODO Auto-generated constructor stub
    }
    
    
    /*
	 * Periste de un Evento en la Base de datos y retorna la Entidad persistida.
	 */

	public Reclamo insert(Reclamo entidad) throws DAOException {
		try {
			em.persist(entidad);
			em.flush();
			return entidad;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta un Evento: " + e.getMessage());
		}
	}

	/*
	 * Retorna un ITR en base al ID.
	 * 
	 */
	public Reclamo findById(Long id) {
		return em.find(Reclamo.class, id);
	}

	/*
	 * Retorna todos los ITR.
	 * 
	 */
	public List<Reclamo> findAll() {
		return em.createQuery("Select i FROM Evento i", Reclamo.class).getResultList();

	}

	/*
	 * Verificamos que exista una ITR por ID y luego realizamos un Update de los
	 * campos que lleguen por parametro.
	 * 
	 */
	public Reclamo update(Long id, Reclamo entidad) throws DAOException, NotFoundEntityException {
		try {
			entidad = em.merge(entidad);
			em.flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException("Ocurrio un error al hacer el update del Itr ", e);
		}

	}

    
    

}
