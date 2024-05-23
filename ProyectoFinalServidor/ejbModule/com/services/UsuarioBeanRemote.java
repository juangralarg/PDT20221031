package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Estudiante;
import com.entities.Itr;
import com.entities.Usuario;
import com.entities.enums.EstadoUsuario;
import com.exceptions.InvalidEntityException;
import com.exceptions.ServiceException;

import validation.ValidacionesUsuario.TipoUsuarioDocumento;
import validation.ValidacionesUsuario.TipoUsuarioEmail;

@Remote
public interface UsuarioBeanRemote {
	/*
	 * <T extends Usuario> sigimica que se "define" el tipo T en esta funcion (T
	 * como podria ser cualquier nombre se estila usar T porque es Type).
	 * 
	 * T al ser declarado como <T extends Usuario>, sigmifica cualquier tipo que
	 * extienda de Usuario. En este caso, serian las Clases Estudiante, Analista y
	 * Tutor. Es decir que el tipo T, podria guardar un valor de cualquiera de estas
	 * 3 clases. Se utiliza los generics para no crear 3 metodos que hagan lo mismo.
	 * 
	 * 
	 * Class<T> sigmifciaria cualqueier Class de tipo T, osea el Estudiante.class,
	 * Tutor.class y Analista.class.
	 */

	<T extends Usuario> T register(T usuario, TipoUsuarioDocumento tipoDocumento, TipoUsuarioEmail tipoEmail) throws ServiceException, InvalidEntityException;

	<T extends Usuario> T login(String nombreUsuario, String password, Class<T> tipoUsu)
			throws ServiceException, InvalidEntityException;

	void updateEstadoUsuario(Long id, EstadoUsuario estadoUsuario) throws ServiceException;

	<T extends Usuario> List<T> findAll(Class<T> tipoUsu);

	<T extends Usuario> List<T> findAll(Class<T> tipoUsu, EstadoUsuario estado, Itr itr);

	List<Estudiante> findAllEstudiantes();

	<T extends Usuario> T findById(Class<T> tipoUsu, Long id);
}
