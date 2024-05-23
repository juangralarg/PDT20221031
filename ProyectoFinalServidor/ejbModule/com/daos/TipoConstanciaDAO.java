package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.TipoConstancia;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class TipoConstanciaDAO
 */
@Stateless
@LocalBean
public class TipoConstanciaDAO {
	
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public TipoConstanciaDAO() {
        // TODO Auto-generated constructor stub
    }
    
    /*
	 * Periste de un TipoConstancia en la Base de datos y retorna la Entidad persistida.
	 */

	public TipoConstancia insert(TipoConstancia entidad) throws DAOException {
		try {
			em.persist(entidad);
			em.flush();
			return entidad;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta un Evento: " + e.getMessage());
		}
	}

	/*
	 * Retorna un TipoConstancia en base al ID.
	 * 
	 */
	public TipoConstancia findById(Long id) {
		return em.find(TipoConstancia.class, id);
	}

	/*
	 * Retorna todos los TipoConstancia.
	 * 
	 */
	public List<TipoConstancia> findAll() {
		return em.createQuery("Select i FROM Evento i", TipoConstancia.class).getResultList();

	}

	/*
	 * Verificamos que exista una TipoConstancia por ID y luego realizamos un Update de los
	 * campos que lleguen por parametro.
	 * 
	 */
	public TipoConstancia update(Long id, TipoConstancia entidad) throws DAOException, NotFoundEntityException {
		try {
			entidad = em.merge(entidad);
			em.flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException("Ocurrio un error al hacer el update del Itr ", e);
		}

	}


}
