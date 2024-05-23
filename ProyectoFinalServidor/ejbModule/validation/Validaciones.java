package validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

@LocalBean
@Singleton
public final class Validaciones {
	
	public static String Trim(String s) {
		return s.trim();
	}
	
	public static boolean ValidarNoVacio(String s) {
		if (s.equals(null))
			return false;

		return !s.isBlank();
	}

	public static boolean ValidarLargo(String s, int largoMax) {
		if (!ValidarNoVacio(s))
			return false;

		return s.length() <= largoMax;
	}

	public static boolean ValidarLargo(String s, int largoMin, int largoMax) {
		if (!ValidarNoVacio(s)) {
			return false;
		}
		
		return s.length() <= largoMax && s.length() >= largoMin;
	}

	public static boolean ValidarSoloLetras(String s, boolean espacios) {
		if (!ValidarNoVacio(s))
			return false;

		if (espacios) {
			return Pattern.matches("[a-zA-Z ]+", s);
		} else {
			return Pattern.matches("[a-zA-Z]+", s);

		}
	}

	public static boolean ValidarSoloNumeros(String s, boolean espacios) {
		if (!ValidarNoVacio(s))
			return false;

		if (espacios) {
			return Pattern.matches("[0-9 ]+", s);
		} else {
			return Pattern.matches("[0-9]+", s);
		}
	}

	public static boolean ValidarNumerosYLetras(String s, boolean espacios) {
		if (!ValidarNoVacio(s))
			return false;

		return ValidarSoloLetras(s, espacios) || ValidarSoloNumeros(s, espacios);
	}
	
	// Validacion de Mail 
	public static boolean ValidarMail(String s) {
		if(!ValidarNoVacio(s))
			return false;
		
		// RFC 5322
		String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		return Pattern.matches(regx, s);
	}	
	
	// Validaciones de Fechas
	public enum ValidacionesFecha {
		ESTRICTAMENTE, NO_ESTRICTAMENTE;
	}

	public static boolean ValidarFechaMin(LocalDate date, LocalDate fechaMin, ValidacionesFecha validacion) {
		if (date == null || fechaMin == null)
			return false;

		// Si la validacion no es menor estrictamente
		// entoces igual sirve
		if (validacion == ValidacionesFecha.NO_ESTRICTAMENTE) {
			if (date.isEqual(fechaMin))
				return true;
		}

		return date.isAfter(fechaMin);
	}

	public static boolean ValidarFechaMax(LocalDate date, LocalDate fechaMax, ValidacionesFecha validacion) {
		if (date == null || fechaMax == null)
			return false;

		// Si la validacion no es mayor estrictamente
		// entoces igual sirve
		if (validacion == ValidacionesFecha.NO_ESTRICTAMENTE) {
			if (date.isEqual(fechaMax))
				return true;
		}
		
		return date.isBefore(fechaMax);
	}

	public static boolean ValidarFecha(LocalDate date, LocalDate fechaMin, LocalDate fechaMax,
			ValidacionesFecha validacion) {
		
		if (date == null || fechaMin == null || fechaMax == null)
			return false;

		if (validacion == ValidacionesFecha.NO_ESTRICTAMENTE) {
			if (date.isEqual(fechaMin) || date.isEqual(fechaMax))
				return true;
		}

		return ValidarFechaMin(date, fechaMin, validacion) && ValidarFechaMax(date, fechaMax, validacion);
	}
	
	public static boolean IsValid(String date) {
		try {
			LocalDate.parse(date, Formatos.DateFormat);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public static boolean ValidarCedulaUruguaya(String s) {
		if (!ValidarNoVacio(s))
			return false;

		return Pattern.matches("[0-9](\\.)[0-9][0-9][0-9](\\.)[0-9][0-9][0-9](-)[0-9]", s)
				|| Pattern.matches("[0-9][0-9][0-9](\\.)[0-9][0-9][0-9](-)[0-9]", s);
	}
}
