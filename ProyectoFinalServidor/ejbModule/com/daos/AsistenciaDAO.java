package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.Asistencia;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class Asistencia
 */
@Stateless
@LocalBean
public class AsistenciaDAO {
	
	
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public AsistenciaDAO() {
        // TODO Auto-generated constructor stub
    }
    
    
	/*
	 * Periste de una Asistencia en la Base de datos y retorna la Entidad persistida.
	 */

	public AsistenciaDAO insert(AsistenciaDAO entidad) throws DAOException {
		try {
			em.persist(entidad);
			em.flush();
			return entidad;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta un Evento: " + e.getMessage());
		}
	}

	/*
	 * Retorna un Asistencia en base al ID.
	 * 
	 */
	public AsistenciaDAO findById(Long id) {
		return em.find(AsistenciaDAO.class, id);
	}

	/*
	 * Retorna todas las Asistencia.
	 * 
	 */
	public List<AsistenciaDAO> findAll() {
		return em.createQuery("Select i FROM Evento i", AsistenciaDAO.class).getResultList();

	}

	/*
	 * Verificamos que exista una Asistencia por ID y luego realizamos un Update de los
	 * campos que lleguen por parametro.
	 * 
	 */
	public AsistenciaDAO update(Long id, AsistenciaDAO entidad) throws DAOException, NotFoundEntityException {
		try {
			entidad = em.merge(entidad);
			em.flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException("Ocurrio un error al hacer el update del Itr ", e);
		}

	}


}
