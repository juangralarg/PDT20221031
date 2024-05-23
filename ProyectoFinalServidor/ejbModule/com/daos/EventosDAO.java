package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import com.entities.Evento;
import com.entities.Itr;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class EventosDAO
 */
@Stateless
@LocalBean
public class EventosDAO {

	@PersistenceContext
	private EntityManager em;

	public EventosDAO() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Periste de un Evento en la Base de datos y retorna la Entidad persistida.
	 */

	public Evento insert(Evento entidad) throws DAOException {
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
	public Evento findById(Long id) {
		return em.find(Evento.class, id);
	}

	/*
	 * Retorna todos los ITR.
	 * 
	 */
	public List<Evento> findAll() {
		return em.createQuery("Select i FROM Evento i", Evento.class).getResultList();

	}

	/*
	 * Verificamos que exista una ITR por ID y luego realizamos un Update de los
	 * campos que lleguen por parametro.
	 * 
	 */
	public Evento update(Long id, Evento entidad) throws DAOException, NotFoundEntityException {
		try {
			entidad = em.merge(entidad);
			em.flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException("Ocurrio un error al hacer el update del Itr ", e);
		}

	}

}
