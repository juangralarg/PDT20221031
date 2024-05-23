package swingutils;

import java.awt.event.KeyEvent;

public class ValidarInputs {

	private static boolean esEspacio(KeyEvent e) {
		return e.getKeyChar() == KeyEvent.VK_SPACE;
	}

	private static boolean esEspacio(char c) {
		return !Character.isSpaceChar(c);
	}

	private static boolean esBorrar(KeyEvent e) {
		return e.getKeyChar() == KeyEvent.VK_BACK_SPACE;
	}

	// Validar si el caracter ingresado es un espacio, borrar o letra,sique no
	// permita el ingreso y muestre un error
	public static void ValidarSoloLetras(KeyEvent e) {
		if (!Character.isLetter(e.getKeyChar()) && !esBorrar(e) && !esEspacio(e)) {
			e.consume();
		}
	}

	public static boolean ValidarSoloLetras(char c) {
		if (!Character.isLetter(c) && !esEspacio(c)) {
			return false;
		}
		return true;
	}

	public static boolean ValidarSoloNumeros(char c) {
		if (!Character.isDigit(c)) {
			return false;
		}
		return true;
	}
	
	// Validar si el caracter ingresado es un numero o borrar, sino
	// que no permita el ingreso y muestre un error
	public static void ValidarSoloNumeros(KeyEvent e) {
		if (!Character.isDigit(e.getKeyChar()) && !esBorrar(e)) {
			e.consume();
			return;
		}
	}

	// Validar si el caracter ingresado es un numero, un punto (para double) o
	// borrar, sino
	// que no permita el ingreso y muestre un error
	public static void ValidarSoloNumerosConComa(KeyEvent e) {
		if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_PERIOD
				&& !esBorrar(e)) {
			e.consume();
			Mensajes.MostrarError("En este campo solo se pueden ingresar numeros");
			return;
		}
	}

	// Validar si el caracter ingresado es una letra, numero, espacio o borrar, sino
	// que no permita el ingreso y muestre un error
	public static void ValidarNumerosYLetras(KeyEvent e) {
		if (!Character.isLetterOrDigit(e.getKeyChar()) && !esBorrar(e) && !esEspacio(e)) {
			e.consume();
			return;
		}
	}
	
	public static boolean ValidarNumerosYLetras(char c) {
		if (!Character.isLetterOrDigit(c) && !esEspacio(c)) {
			return false;
		}
		return true;
	}

	// Validar si el caracter ingresado es un numero, un guion (para fecha) o
	// borrar, sino
	// que no permita el ingreso y muestre un error
	public static void ValidarFechas(KeyEvent e) {
		if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '-' && !esEspacio(e)) {
			e.consume();
			return;
		}
	}
}
