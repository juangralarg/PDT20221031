package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.Constancia;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class Constancia
 */
@Stateless
@LocalBean
public class ConstanciaDAO {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ConstanciaDAO() {
        // TODO Auto-generated constructor stub
    }
    
	/*
	 * Periste de un Constancia en la Base de datos y retorna la Entidad persistida.
	 */

	public Constancia insert(Constancia entidad) throws DAOException {
		try {
			em.persist(entidad);
			em.flush();
			return entidad;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta un Evento: " + e.getMessage());
		}
	}

	/*
	 * Retorna un Constancia en base al ID.
	 * 
	 */
	public Constancia findById(Long id) {
		return em.find(Constancia.class, id);
	}

	/*
	 * Retorna todos los Constancia.
	 * 
	 */
	public List<Constancia> findAll() {
		return em.createQuery("Select i FROM Evento i", Constancia.class).getResultList();

	}

	/*
	 * Verificamos que exista una Constancia por ID y luego realizamos un Update de los
	 * campos que lleguen por parametro.
	 * 
	 */
	public Constancia update(Long id, Constancia entidad) throws DAOException, NotFoundEntityException {
		try {
			entidad = em.merge(entidad);
			em.flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException("Ocurrio un error al hacer el update del Itr ", e);
		}

	}

}
