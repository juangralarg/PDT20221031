package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.Justificacion;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class Justificacion
 */
@Stateless
@LocalBean
public class JustificacionDAO {
	
	
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public JustificacionDAO() {
        // TODO Auto-generated constructor stub
    }
    
    
	/*
	 * Periste de un Justificacion en la Base de datos y retorna la Entidad persistida.
	 */
    
	public JustificacionDAO insert(JustificacionDAO entidad) throws DAOException {
		try {
			em.persist(entidad);
			em.flush();
			return entidad;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta un Evento: " + e.getMessage());
		}
	}

	/*
	 * Retorna un Justificacion en base al ID.
	 * 
	 */
	public JustificacionDAO findById(Long id) {
		return em.find(JustificacionDAO.class, id);
	}

	/*
	 * Retorna todos los Justificacion.
	 * 
	 */
	public List<JustificacionDAO> findAll() {
		return em.createQuery("Select i FROM Evento i", JustificacionDAO.class).getResultList();

	}

	/*
	 * Verificamos que exista una Justificacion por ID y luego realizamos un Update de los
	 * campos que lleguen por parametro.
	 * 
	 */
	public JustificacionDAO update(Long id, JustificacionDAO entidad) throws DAOException, NotFoundEntityException {
		try {
			entidad = em.merge(entidad);
			em.flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException("Ocurrio un error al hacer el update del Itr ", e);
		}

	}

}
