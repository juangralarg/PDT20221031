package com.services;

import java.util.List;
import com.exceptions.EntityAlreadyExistsException;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;

public interface ServiceInterface<T> {
	T save(T entity) throws ServiceException, EntityAlreadyExistsException;

	T remove(Long id) throws ServiceException, NotFoundEntityException;

	T update(T entity) throws ServiceException, NotFoundEntityException;

	T findById(Long id);

	List<T> findAll();
}
