package com.exceptions;

import javax.ejb.ApplicationException;

/**
 * <h1>Uso de la Exception</h1> <br>
 * Esta Exception se arroja para indicar que hay una entidad que ya esta
 * persitida en la base de datos. Es decir, que ya hay un registro en la base de
 * datos con el ID de la Entidad que se desea perisitir.
 * <p>
 * Esta Exception esta diseñada para ser utilizada unicamente en los Services
 * (Beans)
 */
@ApplicationException
public class EntityAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistsException(String message) {
		super(message);
	}
}
