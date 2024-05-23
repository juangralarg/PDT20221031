package swingutils;

import javax.swing.JOptionPane;

public class Mensajes {
	
	public static void MostrarError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Operacion fallida", JOptionPane.ERROR_MESSAGE);
	}

	public static void MostrarSioNo(String msg) {
		JOptionPane.showConfirmDialog(null, msg, "Confirmacion", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
	}
	
	public static void MostrarAdvertencia(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Advertencia", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void MostrarExito(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);
	}
}
