package validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import com.entities.Usuario;
import com.entities.enums.Departamento;
import com.entities.enums.EstadoUsuario;
import com.entities.enums.Genero;

import validation.Validaciones.ValidacionesFecha;

public class ValidacionesUsuario {

	public ValidacionesUsuario() {
	}

	public enum TipoUsuarioEmail {
		UTEC("UTEC"), GENERAL("General");

		private String tipo;

		@Override
		public String toString() {
			return tipo;
		}

		private TipoUsuarioEmail(String tipo) {
			this.tipo = tipo;
		}
	}

	public enum TipoUsuarioDocumento {
		URUGUAYO("Uruguayo"), NO_URUGAUYO("No Uruguayo");

		private String tipo;

		@Override
		public String toString() {
			return tipo;
		}

		private TipoUsuarioDocumento(String tipo) {
			this.tipo = tipo;
		}
	}

	public static ValidationObject ValidarUsuario(Usuario usuario, TipoUsuarioDocumento tipoDocumento, TipoUsuarioEmail tipoEmail) {

		ValidationObject error;
		if (tipoDocumento == TipoUsuarioDocumento.URUGUAYO) {
			error = validarDocumentoUruguayo(usuario.getDocumento());
			if (!error.isValid()) {
				return error;
			}
		} else {
			error = validarDocumentoNoUruguayo(usuario.getDocumento());
			if (!error.isValid()) {
				return error;
			}
		}

		error = validarNombreUsuario(usuario.getNombreUsuario());
		if (!error.isValid()) {
			return error;
		}

		error = validarContrasena(usuario.getContrasena());
		if (!error.isValid()) {
			return error;
		}

		if (tipoEmail == TipoUsuarioEmail.UTEC) {
			error = validarEmailUTEC(usuario.getEmail());
			if (!error.isValid()) {
				return error;
			}
		} else {
			error = validarEmail(usuario.getDocumento());
			if (!error.isValid()) {
				return error;
			}
		}

		error = validarNombres(usuario.getNombres());
		if (!error.isValid()) {
			return error;
		}

		error = validarApellido(usuario.getApellidos());
		if (!error.isValid()) {
			return error;
		}

		error = validarGenero(usuario.getGenero());
		if (!error.isValid()) {
			return error;
		}

		error = validarFechaNacimiento(usuario.getFecNacimiento());
		if (!error.isValid()) {
			return error;
		}

		error = validarDepartamento(usuario.getDepartamento());
		if (!error.isValid()) {
			return error;
		}

		error = validarLocalidad(usuario.getLocalidad());
		if (!error.isValid()) {
			return error;
		}

		error = validarTelefono(usuario.getTelefono());
		if (!error.isValid()) {
			return error;
		}

		error = validarEstadoUsuario(usuario.getEstadoUsuario());
		if (!error.isValid()) {
			return error;
		}

		return ValidationObject.VALID;
	}

	public static ValidationObject validarDocumentoUruguayo(String documento) {
		return Validaciones.ValidarCedulaUruguaya(documento) ? ValidationObject.VALID
				: new ValidationObject("La cedula uruguaya debe contener los puntos, guiones y el digito verificador");
	}

	public static ValidationObject validarDocumentoNoUruguayo(String documento) {
		return Validaciones.ValidarLargo(documento, 20) ? ValidationObject.VALID
				: new ValidationObject("El documento debe contener un maximo de 20 caracteres");
	}

	public static ValidationObject validarNombreUsuario(String nombreUsuario) {
		if (!Pattern.matches("[a-z]+(\\.)[a-z]+", nombreUsuario)
				&& !Pattern.matches("[a-z]+(\\.)[a-z]+(\\.)[a-z]+", nombreUsuario)) {
			return new ValidationObject("El nombre de usuario es invalido, debe tener el formato \"nombre.apellido\"");
		}

		if (!Validaciones.ValidarLargo(nombreUsuario, 3, 64)) {
			return new ValidationObject("El nombre de usuario debe tener un minimo de 3 caracteres y un maximo de 64");
		}

		return ValidationObject.VALID;
	}

	public static ValidationObject validarContrasena(String contrasena) {
		return Validaciones.ValidarLargo(contrasena, 8, 64) ? ValidationObject.VALID
				: new ValidationObject(
						"La contraseña debe tener un minimo de 8 caracteres y un maxaimo de 64 caracteres");
	}

	public static ValidationObject validarNombres(String nombre) {
		if (!Validaciones.ValidarSoloLetras(nombre, true)) {
			return new ValidationObject("Los nombres solo deben contener letras y/o espacios");
		}

		if (!Validaciones.ValidarLargo(nombre, 100)) {
			return new ValidationObject("Los nombres deben tener un maximo de 100 caracteres");
		}

		return ValidationObject.VALID;
	}

	public static ValidationObject validarApellido(String apellido) {
		if (!Validaciones.ValidarSoloLetras(apellido, true)) {
			return new ValidationObject("Los apellidos solo deben contener letras y/o espacios");
		}

		if (!Validaciones.ValidarLargo(apellido, 100)) {
			return new ValidationObject("Los apellidos deben tener un maximo de 100 caracteres");
		}

		return ValidationObject.VALID;
	}

	public static ValidationObject validarFechaNacimiento(LocalDate fecNacimiento) {
		if (LocalDate.now().compareTo(fecNacimiento) < 17) {
			return new ValidationObject("La fecha de nacimiento debe ser de mayoria de edad (o 17 años inclusive)");
		}

		return Validaciones.ValidarFechaMax(fecNacimiento, LocalDate.now(), ValidacionesFecha.NO_ESTRICTAMENTE)
				? ValidationObject.VALID
				: new ValidationObject("La fecha de nacimiento debe ser menor a la fecha actual");
	}

	public static ValidationObject validarFechaNacimiento(String fecNacimiento) {
		try {
			LocalDate fecha = Formatos.ToLocalDate(fecNacimiento);
			return validarFechaNacimiento(fecha);
		} catch (DateTimeParseException e) {
			return new ValidationObject("La fecha debe seguir el formato de \"dia-mes-año\"");
		}
	}

	public static ValidationObject validarTelefono(String telefono) {
		if (!Pattern.matches("[0-9+-]+", telefono)) {
			return new ValidationObject("El telefono solo debe contener numeros, + o -");
		}

		if (!Validaciones.ValidarLargo(telefono, 20)) {
			return new ValidationObject("El telefono debe tener un maximo de 20 caracteres");
		}

		return ValidationObject.VALID;
	}

	public static ValidationObject validarEmailUTEC(String email) {
		if (!Validaciones.ValidarMail(email)) {
			return new ValidationObject("El email tiene un formato invalido");
		}

		String[] partes = email.split("@");

		if (!partes[1].contains("utec.edu.uy")) {
			return new ValidationObject("El email de UTEC debe pertener al dominio utec.edu.uy");
		}

		return ValidationObject.VALID;
	}

	public static ValidationObject validarEmail(String email) {
		if (!Validaciones.ValidarMail(email)) {
			return new ValidationObject("El email tiene un formato invalido");
		}

		return ValidationObject.VALID;
	}

	public static ValidationObject validarLocalidad(String localidad) {
		if (!Validaciones.ValidarLargo(localidad, 100)) {
			return new ValidationObject("La localidad debe tener un maximo de 100 caracteres");
		}

		return ValidationObject.VALID;
	}

	public static ValidationObject validarGenero(Genero genero) {
		return genero != null ? ValidationObject.VALID : new ValidationObject("El genero obligatorio");
	}

	public static ValidationObject validarDepartamento(Departamento departamento) {
		return departamento != null ? ValidationObject.VALID : new ValidationObject("El departamento obligatorio");
	}

	public static ValidationObject validarEstadoUsuario(EstadoUsuario estado) {
		return estado != null ? ValidationObject.VALID : new ValidationObject("El estado obligatorio");
	}
}
