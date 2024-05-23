package validation;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.entities.Estudiante;

import validation.ValidacionesUsuario.TipoUsuarioDocumento;
import validation.ValidacionesUsuario.TipoUsuarioEmail;

/**
 * Session Bean implementation class ValidacionesUsuarioEstudiante
 */
@Stateless
@LocalBean
public class ValidacionesUsuarioEstudiante {

	/**
	 * Default constructor.
	 */
	public ValidacionesUsuarioEstudiante() {
	}

	public static ValidationObject validarEstudiante(Estudiante estudiante, TipoUsuarioDocumento tipoDocumento,
			TipoUsuarioEmail tipoEmail) {
		ValidationObject valid = ValidacionesUsuario.ValidarUsuario(estudiante, tipoDocumento, tipoEmail);
		if (!valid.isValid())
			return valid;

		valid = validarGeneracion(estudiante.getGeneracion());
		if (!valid.isValid())
			return valid;

		return ValidationObject.VALID;
	}

	public static ValidationObject validarGeneracion(String generacion) {

		if (Validaciones.ValidarSoloNumeros(generacion, false)) {
			if (generacion.length() == 4) {
				return ValidationObject.VALID;
			} else {
				return new ValidationObject("La generacion solo puede tener un maximo de 4 digitos");
			}
		} else {
			return new ValidationObject("La generacion solo puede contener numeros positivos");
		}
	}

	public static ValidationObject validarGeneracion(Integer generacion) {
		if (generacion >= 1000 && generacion <= 9999) {
			return ValidationObject.VALID;
		} else {
			return new ValidationObject("La generacion solo puede tener un maximo de 4 digitos");
		}
	}
}
