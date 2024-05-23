package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.TipoEvento;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class TipoEventoDAO
 */
@Stateless
@LocalBean
public class TipoEventoDAO {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public TipoEventoDAO() {
        // TODO Auto-generated constructor stub
    }
    
	/*
	 * Periste de un TipoEvento en la Base de datos y retorna la Entidad persistida.
	 */

	public TipoEvento insert(TipoEvento entidad) throws DAOException {
		try {
			em.persist(entidad);
			em.flush();
			return entidad;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta un Evento: " + e.getMessage());
		}
	}

	/*
	 * Retorna un TipoEvento en base al ID.
	 * 
	 */
	public TipoEvento findById(Long id) {
		return em.find(TipoEvento.class, id);
	}

	/*
	 * Retorna todos los TipoEvento.
	 * 
	 */
	public List<TipoEvento> findAll() {
		return em.createQuery("Select i FROM Evento i", TipoEvento.class).getResultList();

	}

	/*
	 * Verificamos que exista una TipoEvento por ID y luego realizamos un Update de los
	 * campos que lleguen por parametro.
	 * 
	 */
	public TipoEvento update(Long id, TipoEvento entidad) throws DAOException, NotFoundEntityException {
		try {
			entidad = em.merge(entidad);
			em.flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException("Ocurrio un error al hacer el update del Itr ", e);
		}

	}


}
