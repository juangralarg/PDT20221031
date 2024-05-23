package validation;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.entities.Tutor;
import com.entities.enums.TipoTutor;

import validation.ValidacionesUsuario.TipoUsuarioDocumento;
import validation.ValidacionesUsuario.TipoUsuarioEmail;

/**
 * Session Bean implementation class ValidacionesUsuarioTutor
 */
@Stateless
@LocalBean
public class ValidacionesUsuarioTutor {

	/**
	 * Default constructor.
	 */
	public ValidacionesUsuarioTutor() {
		// TODO Auto-generated constructor stub
	}

	public static ValidationObject validarTutor(Tutor tutor, TipoUsuarioDocumento tipoDocumento,
			TipoUsuarioEmail tipoEmail) {
		
		ValidationObject valid = ValidacionesUsuario.ValidarUsuario(tutor, tipoDocumento, tipoEmail);
		if (!valid.isValid())
			return valid;

		valid = validarTipo(tutor.getTipo());
		if (!valid.isValid())
			return valid;

		valid = validarArea(tutor.getArea());
		if (!valid.isValid())
			return valid;
		
		return ValidationObject.VALID;
	}

	public static ValidationObject validarTipo(TipoTutor tipo) {
		if (tipo != null) {
			return ValidationObject.VALID;
		} else {
			return new ValidationObject("El tipo de Tutor es obligatorio");
		}
	}

	public static ValidationObject validarArea(String area) {

		if (Validaciones.ValidarNumerosYLetras(area, false)) {
			if (area.length() <= 50) {
				return ValidationObject.VALID;
			} else {
				return new ValidationObject("El Area deben tener un maximo de 50 caracteres");
			}
		} else {
			return new ValidationObject("El Area solo puede contener letras y numeros");
		}

	}

}
