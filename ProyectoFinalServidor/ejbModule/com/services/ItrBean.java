package com.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import com.daos.ItrDAO;
import com.entities.Itr;
import com.exceptions.DAOException;
import com.exceptions.EntityAlreadyExistsException;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;

/**
 * Session Bean implementation class ItrBean
 */
@Stateless
@LocalBean
public class ItrBean implements ItrBeanRemote {

	@EJB
	private ItrDAO dao;

	public ItrBean() {
	}

	@Override
	public Itr save(Itr entity) throws ServiceException, EntityAlreadyExistsException {
		try {
			if(entity.getIdItr() != null){
				if (dao.findById(entity.getIdItr()) != null)
					throw new EntityAlreadyExistsException("Ya existe un ITR con el ID: " + entity.getIdItr());
			}

			return dao.insert(entity);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Itr remove(Long id) throws ServiceException, NotFoundEntityException {
		return null;
	}

	@Override
	public Itr update(Itr entity) throws ServiceException, NotFoundEntityException {
		return null;
	}

	@Override
	public Itr findById(Long id) {
		return null;
	}

	@Override
	public List<Itr> findAll() {
		return dao.findAll();
	}

}
