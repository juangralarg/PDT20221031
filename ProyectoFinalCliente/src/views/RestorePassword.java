package views;

import java.awt.BorderLayout;
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

public class RestorePassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestorePassword frame = new RestorePassword();
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
	public RestorePassword() {
			setTitle("Login");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 711, 572);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			for (Roles rol : Roles.values()) {
				comboRol.addItem(rol);
			}

			JLabel lblNewLabel = new JLabel("Nueva Contraseña");
			lblNewLabel.setBounds(132, 219, 102, 13);
			contentPane.add(lblNewLabel);

			JLabel lblNewLabel_1 = new JLabel("Reingresar Contraseña");
			lblNewLabel_1.setBounds(132, 250, 122, 13);
			contentPane.add(lblNewLabel_1);

			VTextBox textboxUsuario = new VTextBox();
			textboxUsuario.setBounds(264, 215, 222, 21);
			contentPane.add(textboxUsuario);
			textboxUsuario.setValidationFunc(texto -> ValidacionesUsuario.validarNombreUsuario(texto));

			JLabel lblNewLabel_4 = new JLabel("");
			lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/images/logo utec (2).png")));
			lblNewLabel_4.setBounds(570, 10, 117, 105);
			contentPane.add(lblNewLabel_4);

			JLabel lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setIcon(new ImageIcon(RestorePassword.class.getResource("/images/olvido.png")));
			lblNewLabel_3.setBounds(419, 85, 67, 64);
			contentPane.add(lblNewLabel_3);

			JLabel lblNewLabel_5 = new JLabel("Restablecer contrasena");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_5.setBounds(214, 119, 171, 30);
			contentPane.add(lblNewLabel_5);
			
			JButton btnRestorePassword = new JButton("Restablecer Contrasena");
			btnRestorePassword.setBounds(344, 297, 141, 21);
			contentPane.add(btnRestorePassword);
			
			textPassword = new JPasswordField();
			textPassword.setBounds(264, 246, 222, 21);
			contentPane.add(textPassword);

		}
	}


