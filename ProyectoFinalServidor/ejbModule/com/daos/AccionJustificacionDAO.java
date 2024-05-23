package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.AccionJustificacion;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class AccionJustificacionDAO
 */
@Stateless
@LocalBean
public class AccionJustificacionDAO {
	
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public AccionJustificacionDAO() {
        // TODO Auto-generated constructor stub
    }
    
    
    /*
	 * Periste de un AccionJustificacion en la Base de datos y retorna la Entidad persistida.
	 */

	public AccionJustificacion insert(AccionJustificacion entidad) throws DAOException {
		try {
			em.persist(entidad);
			em.flush();
			return entidad;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta un Evento: " + e.getMessage());
		}
	}

	/*
	 * Retorna un AccionJustificacion en base al ID.
	 * 
	 */
	public AccionJustificacion findById(Long id) {
		return em.find(AccionJustificacion.class, id);
	}

	/*
	 * Retorna todos los AccionJustificacion.
	 * 
	 */
	public List<AccionJustificacion> findAll() {
		return em.createQuery("Select i FROM Evento i", AccionJustificacion.class).getResultList();

	}

	/*
	 * Verificamos que exista una ITR por ID y luego realizamos un Update de los
	 * campos que lleguen por parametro.
	 * 
	 */
	public AccionJustificacion update(Long id, AccionJustificacion entidad) throws DAOException, NotFoundEntityException {
		try {
			entidad = em.merge(entidad);
			em.flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException("Ocurrio un error al hacer el update del Itr ", e);
		}

	}

}
