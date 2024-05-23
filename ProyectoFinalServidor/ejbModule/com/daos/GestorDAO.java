package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.Gestor;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class GestorDAO
 */
@Stateless
@LocalBean
public class GestorDAO {
	
	

	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public GestorDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public Gestor insert(Gestor entidad) throws DAOException {
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
	public Gestor findById(Long id) {
		return em.find(Gestor.class, id);
	}

	/*
	 * Retorna todos los ITR.
	 * 
	 */
	public List<Gestor> findAll() {
		return em.createQuery("Select i FROM Evento i", Gestor.class).getResultList();

	}

	/*
	 * Verificamos que exista una ITR por ID y luego realizamos un Update de los
	 * campos que lleguen por parametro.
	 * 
	 */
	public Gestor update(Long id, Gestor entidad) throws DAOException, NotFoundEntityException {
		try {
			entidad = em.merge(entidad);
			em.flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException("Ocurrio un error al hacer el update del Itr ", e);
		}

	}   
}
