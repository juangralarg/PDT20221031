package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Itr;
import com.entities.Tutor;
import com.entities.enums.Departamento;
import com.entities.enums.EstadoUsuario;
import com.entities.enums.Genero;
import com.entities.enums.TipoTutor;
import com.toedter.calendar.JDateChooser;

import beans.BeanIntances;
import components.Roles;
import components.VTextBox;
import swingutils.Mensajes;
import validation.Formatos;
import validation.ValidacionesUsuario;
import validation.ValidacionesUsuario.TipoUsuarioDocumento;
import validation.ValidacionesUsuario.TipoUsuarioEmail;
import validation.ValidacionesUsuarioEstudiante;
import validation.ValidacionesUsuarioTutor;
import validation.ValidationObject;

public class Registrarse extends JFrame {

	private JPanel contentPane;

	private JDateChooser dtFechaDeNacimiento;
	private JPasswordField textpassword;
	private VTextBox textGeneracion;
	private VTextBox textArea;
	private VTextBox textLocalidad;
	private JComboBox<TipoTutor> cmbTipoTutor;
	private JComboBox<Genero> comboGenero;
	private JComboBox<Departamento> comboDepartamento;
	private JComboBox<Itr> comboItr;
	private JComboBox<Roles> comboRol;
	private JCheckBox chckbxInstitucional;
	private JCheckBox chckbxUruguayo;
	private VTextBox textDocumento;
	private VTextBox textUsuario;
	private VTextBox textNombres;
	private VTextBox textApellidos;
	private VTextBox textTel;
	private VTextBox textMail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrarse frame = new Registrarse();
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
	public Registrarse() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				for (Departamento departamento : Departamento.values()) {
					comboDepartamento.addItem(departamento);
				}

				for (Genero genero : Genero.values()) {
					comboGenero.addItem(genero);
				}

				for (Roles rol : Roles.values()) {
					comboRol.addItem(rol);
				}

				for (TipoTutor tipo : TipoTutor.values()) {
					cmbTipoTutor.addItem(tipo);
				}

				List<Itr> itrs = BeanIntances.itr().findAll();
				for (Itr itr : itrs) {
					comboItr.addItem(itr);
				}
			}
		});
		setTitle("Registrarse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Documento");
		lblNewLabel.setBounds(10, 118, 136, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre Usuario");
		lblNewLabel_1.setBounds(12, 163, 136, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		lblNewLabel_2.setBounds(12, 186, 136, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Nombres");
		lblNewLabel_3.setBounds(12, 209, 136, 13);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Apellidos");
		lblNewLabel_4.setBounds(12, 232, 136, 13);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Fec. de Nacimiento");
		lblNewLabel_5.setBounds(12, 256, 136, 13);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Telefono");
		lblNewLabel_6.setBounds(347, 118, 85, 13);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("E-Mail");
		lblNewLabel_7.setBounds(347, 141, 85, 13);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Localidad");
		lblNewLabel_8.setBounds(347, 189, 99, 13);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Departamento");
		lblNewLabel_9.setBounds(347, 212, 99, 13);
		contentPane.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Genero");
		lblNewLabel_10.setBounds(347, 235, 99, 13);
		contentPane.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("ITR");
		lblNewLabel_11.setBounds(347, 258, 99, 13);
		contentPane.add(lblNewLabel_11);

		textDocumento = new VTextBox();
		textDocumento.setBounds(138, 118, 110, 16);
		textDocumento.setValidationFunc(text -> ValidacionesUsuario.validarDocumentoUruguayo(text));
		contentPane.add(textDocumento);

		textUsuario = new VTextBox();
		textUsuario.setBounds(140, 163, 110, 16);
		textUsuario.setValidationFunc(text -> ValidacionesUsuario.validarNombreUsuario(text));
		contentPane.add(textUsuario);

		textNombres = new VTextBox();
		textNombres.setBounds(140, 209, 110, 16);
		textNombres.setValidationFunc(text -> ValidacionesUsuario.validarNombres(text));
		contentPane.add(textNombres);

		textApellidos = new VTextBox();
		textApellidos.setBounds(140, 232, 110, 16);
		textApellidos.setValidationFunc(text -> ValidacionesUsuario.validarApellido(text));
		contentPane.add(textApellidos);

		textTel = new VTextBox();
		textTel.setBounds(439, 97, 110, 16);
		textTel.setBounds(439, 115, 110, 16);
		textTel.setValidationFunc(text -> ValidacionesUsuario.validarTelefono(text));
		contentPane.add(textTel);

		textMail = new VTextBox();
		textMail.setBounds(440, 140, 110, 16);
		textMail.setBounds(439, 137, 110, 16);
		textMail.setValidationFunc(text -> ValidacionesUsuario.validarEmailUTEC(text));
		contentPane.add(textMail);

		JButton btnRegistrarme = new JButton("Registrarme");
		btnRegistrarme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// La siguiente sentencia es lo mismo que un If, si esta seleccionado es email
					// utec si no es general.
					TipoUsuarioEmail email = chckbxInstitucional.isSelected() ? TipoUsuarioEmail.UTEC
							: TipoUsuarioEmail.GENERAL;
					TipoUsuarioDocumento documento = chckbxUruguayo.isSelected() ? TipoUsuarioDocumento.URUGUAYO
							: TipoUsuarioDocumento.NO_URUGAUYO;

					
					Roles rol = ((Roles) comboRol.getSelectedItem());					
					LocalDate fecha = Formatos.ToLocalDate(dtFechaDeNacimiento.getDate());
					if (rol == Roles.ESTUDIANTE) {
						Estudiante estudiante = new Estudiante();
						estudiante.setNombres(textNombres.getText());
						estudiante.setApellidos(textApellidos.getText());
						estudiante.setNombreUsuario(textUsuario.getText());
						estudiante.setContrasena(String.valueOf(textpassword.getPassword()));
						estudiante.setEmail(textMail.getText());
						estudiante.setDocumento(textDocumento.getText());
						estudiante.setTelefono(textTel.getText());
						estudiante.setFecNacimiento(fecha);
						estudiante.setDepartamento((Departamento) comboDepartamento.getSelectedItem());
						estudiante.setGenero((Genero) comboGenero.getSelectedItem());
						estudiante.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
						estudiante.setLocalidad(textLocalidad.getText());
						estudiante.setItr((Itr) comboItr.getSelectedItem());
						estudiante.setEstado(true);
						estudiante.setGeneracion(Integer.parseInt(textGeneracion.getText()));

						ValidationObject error = ValidacionesUsuarioEstudiante.validarEstudiante(estudiante, documento,
								email);
						if (!error.isValid()) {
							Mensajes.MostrarError(error.getErrorMessage());
							return;
						}
						estudiante = BeanIntances.user().register(estudiante, documento, email);
						Mensajes.MostrarExito("Se dio de alta correctamente el Estudiante " + estudiante.getNombres());
						return;
					}

					if (rol == Roles.TUTOR) {
						Tutor tutor = new Tutor();
						tutor.setNombres(textNombres.getText());
						tutor.setApellidos(textApellidos.getText());
						tutor.setNombreUsuario(textUsuario.getText());
						tutor.setContrasena(String.valueOf(textpassword.getPassword()));
						tutor.setEmail(textMail.getText());
						tutor.setDocumento(textDocumento.getText());
						tutor.setTelefono(textTel.getText());
						tutor.setFecNacimiento(fecha);
						tutor.setDepartamento((Departamento) comboDepartamento.getSelectedItem());
						tutor.setGenero((Genero) comboGenero.getSelectedItem());
						tutor.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
						tutor.setLocalidad(textLocalidad.getText());
						tutor.setItr((Itr) comboItr.getSelectedItem());
						tutor.setEstado(true);
						tutor.setArea(textArea.getText());
						tutor.setTipo((TipoTutor) cmbTipoTutor.getSelectedItem());

						ValidationObject error = ValidacionesUsuarioTutor.validarTutor(tutor, documento, email);
						if (!error.isValid()) {
							Mensajes.MostrarError(error.getErrorMessage());
							return;
						}
						tutor = BeanIntances.user().register(tutor, documento, email);
						Mensajes.MostrarExito("Se dio de alta correctamente el Tutor " + tutor.getNombres());
						return;
					}

					Analista analista = new Analista();
					analista.setNombres(textNombres.getText());
					analista.setApellidos(textApellidos.getText());
					analista.setNombreUsuario(textUsuario.getText());
					analista.setContrasena(String.valueOf(textpassword.getPassword()));
					analista.setEmail(textMail.getText());
					analista.setDocumento(textDocumento.getText());
					analista.setTelefono(textTel.getText());
					analista.setFecNacimiento(fecha);
					analista.setDepartamento((Departamento) comboDepartamento.getSelectedItem());
					analista.setGenero((Genero) comboGenero.getSelectedItem());
					analista.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
					analista.setLocalidad(textLocalidad.getText());
					analista.setItr((Itr) comboItr.getSelectedItem());
					analista.setEstado(true);

					ValidationObject error = ValidacionesUsuario.ValidarUsuario(analista, documento, email);
					if (!error.isValid()) {
						Mensajes.MostrarError(error.getErrorMessage());
						return;
					}
					analista = BeanIntances.user().register(analista, documento, email);
					Mensajes.MostrarExito("Se dio de alta correctamente el Analista " + analista.getNombres());
				} catch (Exception ex) {
					Mensajes.MostrarError(ex.getMessage());
				}

			}
		});
		btnRegistrarme.setBounds(440, 388, 110, 21);

		contentPane.add(btnRegistrarme);

		JLabel lblNewLabel_12 = new JLabel("Registro de usuario");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setBounds(228, 39, 218, 13);
		contentPane.add(lblNewLabel_12);

		comboDepartamento = new JComboBox<Departamento>();
		comboDepartamento.setBounds(471, 181, 110, 17);

		comboDepartamento = new JComboBox<Departamento>();
		comboDepartamento.setBounds(439, 209, 110, 17);

		contentPane.add(comboDepartamento);

		comboGenero = new JComboBox<Genero>();
		comboGenero.setBounds(471, 204, 110, 17);

		comboGenero = new JComboBox<Genero>();
		comboGenero.setBounds(439, 232, 110, 17);

		contentPane.add(comboGenero);

		dtFechaDeNacimiento = new JDateChooser();
		dtFechaDeNacimiento.setBounds(140, 250, 110, 19);
		contentPane.add(dtFechaDeNacimiento);

		textpassword = new JPasswordField();
		textpassword.setBounds(140, 186, 110, 16);
		contentPane.add(textpassword);

		JLabel lblNewLabel_13 = new JLabel("Rol");
		lblNewLabel_13.setBounds(10, 304, 45, 13);
		contentPane.add(lblNewLabel_13);

		JLabel lblNewLabel_6_1 = new JLabel("Generacion");
		lblNewLabel_6_1.setBounds(346, 304, 94, 13);
		contentPane.add(lblNewLabel_6_1);

		textGeneracion = new VTextBox();
		textGeneracion.setBounds(438, 300, 111, 16);
		textGeneracion.setEnabled(false);
		textGeneracion.setValidationFunc(text -> ValidacionesUsuarioEstudiante.validarGeneracion(text));
		contentPane.add(textGeneracion);

		JLabel lblNewLabel_6_2 = new JLabel("Area");
		lblNewLabel_6_2.setBounds(346, 331, 94, 13);
		contentPane.add(lblNewLabel_6_2);

		JLabel lblNewLabel_7_2 = new JLabel("Tipo Tutor");
		lblNewLabel_7_2.setBounds(346, 353, 94, 13);
		contentPane.add(lblNewLabel_7_2);

		cmbTipoTutor = new JComboBox<TipoTutor>();
		cmbTipoTutor.setEnabled(false);
		cmbTipoTutor.setBounds(438, 349, 111, 16);
		contentPane.add(cmbTipoTutor);

		textArea = new VTextBox();
		textArea.setEnabled(false);
		textArea.setBounds(438, 327, 111, 16);
		textArea.setValidationFunc(text -> ValidacionesUsuarioTutor.validarArea(text));
		contentPane.add(textArea);

		comboRol = new JComboBox<Roles>();
		comboRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Roles rol = ((Roles) comboRol.getSelectedItem());
				if (rol == Roles.ESTUDIANTE) {
					textGeneracion.setEnabled(true);
					textArea.setEnabled(false);
					cmbTipoTutor.setEnabled(false);
				} else if (rol == Roles.TUTOR) {
					textArea.setEnabled(true);
					cmbTipoTutor.setEnabled(true);
					textGeneracion.setEnabled(false);
				} else {
					textArea.setEnabled(false);
					cmbTipoTutor.setEnabled(false);
					textGeneracion.setEnabled(false);
				}
			}
		});
		comboRol.setBounds(136, 300, 116, 21);
		contentPane.add(comboRol);

		chckbxInstitucional = new JCheckBox("Institucional");
		chckbxInstitucional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxInstitucional.isSelected()) {
					textMail.setValidationFunc(text -> ValidacionesUsuario.validarEmailUTEC(text));
				} else {
					textMail.setValidationFunc(text -> ValidacionesUsuario.validarEmail(text));
				}
				textMail.grabFocus();
			}
		});
		chckbxInstitucional.setSelected(true);
		chckbxInstitucional.setBounds(509, 163, 126, 13);
		contentPane.add(chckbxInstitucional);

		chckbxUruguayo = new JCheckBox("Uruguayo");
		chckbxUruguayo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxUruguayo.isSelected()) {
					textDocumento.setValidationFunc(text -> ValidacionesUsuario.validarDocumentoUruguayo(text));
				} else {
					textDocumento.setValidationFunc(text -> ValidacionesUsuario.validarDocumentoNoUruguayo(text));
				}
				textDocumento.grabFocus();
			}
		});
		chckbxUruguayo.setSelected(true);
		chckbxUruguayo.setBounds(211, 141, 87, 13);
		contentPane.add(chckbxUruguayo);

		comboItr = new JComboBox<Itr>();
		comboItr.setBounds(438, 256, 111, 17);
		contentPane.add(comboItr);

		textLocalidad = new VTextBox();
		textLocalidad.setBounds(439, 186, 110, 16);
		textLocalidad.setValidationFunc(text -> ValidacionesUsuario.validarLocalidad(text));
		contentPane.add(textLocalidad);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 290, 546, 2);
		contentPane.add(separator);

	}
}
