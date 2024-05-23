package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.Responsable;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class ResponsableDAO
 */
@Stateless
@LocalBean
public class ResponsableDAO {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ResponsableDAO() {
        // TODO Auto-generated constructor stub
    }
    
    

	/*
	 * Periste de un Responsable en la Base de datos y retorna la Entidad persistida.
	 */

	public Responsable insert(Responsable entidad) throws DAOException {
		try {
			em.persist(entidad);
			em.flush();
			return entidad;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta un Evento: " + e.getMessage());
		}
	}

	/*
	 * Retorna un Responsable en base al ID.
	 * 
	 */
	public Responsable findById(Long id) {
		return em.find(Responsable.class, id);
	}

	/*
	 * Retorna todos los Responsable.
	 * 
	 */
	public List<Responsable> findAll() {
		return em.createQuery("Select i FROM Evento i", Responsable.class).getResultList();

	}

	/*
	 * Verificamos que exista una Responsable por ID y luego realizamos un Update de los
	 * campos que lleguen por parametro.
	 * 
	 */
	public Responsable update(Long id, Responsable entidad) throws DAOException, NotFoundEntityException {
		try {
			entidad = em.merge(entidad);
			em.flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException("Ocurrio un error al hacer el update del Itr ", e);
		}

	}


}
