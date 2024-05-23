package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Tutor;

import beans.BeanIntances;
import components.Roles;
import components.VTextBox;
import swingutils.Mensajes;
import validation.ValidacionesUsuario;
import validation.ValidationObject;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField textPassword;

	public static final String VERSION = "v1.1";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<Roles> comboRol = new JComboBox<Roles>();
		comboRol.setBounds(277, 184, 222, 21);
		contentPane.add(comboRol);
		for (Roles rol : Roles.values()) {
			comboRol.addItem(rol);
		}

		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Registrarse registro = new Registrarse();
				registro.setVisible(true);
			}
		});
		btnRegistrarse.setBounds(165, 343, 102, 21);
		contentPane.add(btnRegistrarse);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(165, 218, 102, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setBounds(165, 254, 102, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Rol");
		lblNewLabel_2.setBounds(165, 188, 102, 13);
		contentPane.add(lblNewLabel_2);

		VTextBox textboxUsuario = new VTextBox();
		textboxUsuario.setBounds(277, 215, 222, 21);
		contentPane.add(textboxUsuario);
		textboxUsuario.setValidationFunc(texto -> ValidacionesUsuario.validarNombreUsuario(texto));

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Valido el usuario
				if (!textboxUsuario.isValid()) {
					Mensajes.MostrarError(textboxUsuario.getErrorMessage());
					return;
				}
				// Valido la contrasena
				ValidationObject v = ValidacionesUsuario.validarContrasena(String.valueOf(textPassword.getPassword()));
				if (!v.isValid()) {
					Mensajes.MostrarError(v.getErrorMessage());
					return;
				}

				if (comboRol.getSelectedItem() == Roles.ANALISTA) {
					try {
						BeanIntances.user().login(textboxUsuario.getText(), String.valueOf(textPassword.getPassword()),
								Analista.class);
						setVisible(false);
						ViewAnalista viewAnalista = new ViewAnalista();
						viewAnalista.setVisible(true);
					} catch (Exception E) {
						JOptionPane.showMessageDialog(null,
								"No es posible loguearse al sistema. Compruebe las credenciales ingresadas.");
					}
				} else if (comboRol.getSelectedItem() == Roles.TUTOR) {
					try {
						BeanIntances.user().login(textboxUsuario.getText(), String.valueOf(textPassword.getPassword()),
								Tutor.class);
						setVisible(false);
						ViewTutor ViewTutor = new ViewTutor();
						ViewTutor.setVisible(true);
					} catch (Exception E) {
						JOptionPane.showMessageDialog(null,
								"No es posible loguearse al sistema. Compruebe las credenciales ingresadas.");
					}
				} else if (comboRol.getSelectedItem() == Roles.ESTUDIANTE) {
					try {
						BeanIntances.user().login(textboxUsuario.getText(), String.valueOf(textPassword.getPassword()),
								Estudiante.class);
						setVisible(false);
						ViewEstudiante ViewEstudiante = new ViewEstudiante();
						ViewEstudiante.setVisible(true);
					} catch (Exception E) {
						JOptionPane.showMessageDialog(null,
								"No es posible loguearse al sistema. Compruebe las credenciales ingresadas.");
					}
				}
			}
		});
		btnLogin.setBounds(397, 343, 102, 21);
		contentPane.add(btnLogin);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/images/logo utec (2).png")));
		lblNewLabel_4.setBounds(570, 10, 117, 105);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/images/usuario (3).png")));
		lblNewLabel_3.setBounds(398, 108, 32, 41);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("Login");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(324, 119, 45, 30);
		contentPane.add(lblNewLabel_5);

		textPassword = new JPasswordField();
		textPassword.setBounds(277, 250, 222, 21);
		contentPane.add(textPassword);

		JLabel lblNewLabel_6 = new JLabel("Ha olvidado su contrasena");
		lblNewLabel_6.setBounds(165, 307, 144, 13);
		contentPane.add(lblNewLabel_6);

		JButton btnRestorePassword = new JButton("Restablecer contrasena");
		btnRestorePassword.setBounds(355, 303, 144, 21);
		contentPane.add(btnRestorePassword);

	}
}
