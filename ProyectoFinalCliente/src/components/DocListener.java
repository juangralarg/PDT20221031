package components;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

/*
 * Esta clase se encarga de controlar el Document de un compoente de texto de
 * Swing.
 * 
 * Lo que hace ejecutar la accion (action) luego de un X (waitTime) tiempo de
 * que el usuario haya terminado de escribir.
 * 
 * La accion se ejecuta luego de que no haya un cambio en el texto por de ese X
 * (waitTime) tiempo. Si hay un cambio antes el Timer es reseteado (la cuenta
 * vuelve a 0) y asi continuamente mientras no se llegue a ese X tiempo la
 * accion no se ejecutara.
 */
public class DocListener implements DocumentListener {

	// Creo un Timer que el que contralara la espera
	private final Timer timer;

	public DocListener(int waitTime, ActionListener action) {
		timer = new Timer(waitTime, action);

		// El atributo Reapeats indica que luego de que se ejecute una vez
		// el Timer volera a ejecutar la accion luego del proximo reset del tiempo
		timer.setRepeats(true);
	}

	// Aplica el Listener al JTextComponent que se le pida, en el FocusListener
	public static void applyDocListener(JTextComponent textField, int waitTime, ActionListener action) {
		DocListener listener = new DocListener(waitTime, action);

		// Al perder el Focus el Timer (dentro de Listener) se para que no siga
		// ejecutado la accion (actio) en segundo plano infinitamente
		
		// Y al recuperar el Focus el timer  vuelve a iniciar
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				listener.stop();
			}

			@Override
			public void focusGained(FocusEvent e) {
				listener.start();
			}
		});

		textField.getDocument().addDocumentListener(listener);
	}

	// Metodo para iniciar la escucha, es decir, iniciar el Timer
	public void start() {
		timer.start();
	}

	// Metodo para detern le escuchas, es decir, detenre el Timer
	public void stop() {
		timer.stop();
	}

	/*
	 * Que resetee el Tiemr cada vez que haya un cambio el texto del documento que
	 * se inserte, que se borre o que se cambie una letra
	 */

	@Override
	public void insertUpdate(DocumentEvent e) {
		timer.restart();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		timer.restart();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		timer.restart();
	}

}
