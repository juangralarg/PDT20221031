package main;

import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Estudiante;
import com.entities.Itr;
import com.entities.Tutor;
import com.entities.enums.Departamento;
import com.entities.enums.EstadoUsuario;
import com.entities.enums.Genero;
import com.entities.enums.TipoTutor;

import beans.BeanIntances;
import components.Roles;
import components.VTextBox;
import validation.ValidacionesUsuario;

public class Test extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VTextBox textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Itr itr = new Itr();
		itr.setNombre("ITRCS");
		itr.setDepartamento(Departamento.DURAZNO);
		itr.setEstado(true);

		BeanIntances.itr().save(itr);
		System.out.println(BeanIntances.itr().findAll());
		System.exit(1);

		Estudiante e = new Estudiante();
		e.setNombres("Joaquin");
		e.setApellidos("Genova Guerendiain");
		e.setNombreUsuario("joaquin.genova");
		e.setContrasena("12345678");
		e.setEmail("joaquin.genova@estudiantes.utec.edu.uy");
		e.setDocumento("5.362.666-3");
		e.setTelefono("095112514");
		e.setFecNacimiento(LocalDate.of(2003, 9, 8));
		e.setDepartamento(Departamento.MONTEVIDEO);
		e.setEstado(true);
		e.setGenero(Genero.MASCULINO);
		e.setGeneracion(2022);
		e.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
		e.setLocalidad("Villa Española");
		e.setItr(itr);

		//System.out.println(ValidacionesUsuario.ValidarUsuario(e).getErrorMessage());

//		System.out.println(BeanIntances.user().register(e));
//		Estudiante e1 = BeanIntances.user().login("joaquin.genova", "1234", Estudiante.class);
//		System.out.println(e1);

//		System.out.println(BeanIntances.user().findAll(Estudiante.class));
//		System.out.println(BeanIntances.user().findAll(Tutor.class));

		// BeanIntances.user().updateEstadoUsuario(6l, EstadoUsuario.SIN_VALIDAR);
		/*
		 * for (Estudiante e : BeanIntances.user().findAll(Estudiante.class,
		 * EstadoUsuario.SIN_VALIDAR, itr)) { System.out.println(e); }
		 */

		// Tutor t = new Tutor();
//		t.setNombres("William");
//		t.setApellidos("Machado");
//		t.setNombreUsuario("william.machado");
//		t.setContrasena("1234");
//		t.setEmail("william.machado@utec.edu.uy");
//		t.setDocumento("45634524");
//		t.setTelefono("096412341");
//		t.setFecNacimiento(LocalDate.of(1980, 10, 3));
//		t.setDepartamento(Departamento.MONTEVIDEO);
//		t.setGenero(Genero.MASCULINO);
//		t.setArea("Ingles");
//		t.setTipo(TipoTutor.TUTOR);
//		t.setEstado(true);
//		t.setLocalidad("Otro lugar que no se cual es");
//		t.setItr(itr);
//		
//		System.out.println(BeanIntances.user().register(t));
//		System.out.println(BeanIntances.user().login("william.machado", "1234", Tutor.class));

		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() { try { Test frame
		 * = new Test(); frame.setVisible(true); } catch (Exception e) {
		 * e.printStackTrace(); } } });
		 */
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new VTextBox();
		textField.setBounds(12, 12, 114, 21);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
