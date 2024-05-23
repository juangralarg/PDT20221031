package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.entities.Estudiante;
import com.entities.Usuario;
import com.entities.enums.EstadoUsuario;

import beans.BeanIntances;
import components.Roles;

public class ViewAnalista extends JFrame {

	private JPanel contentPane;
	private JTextField txtGeneracion;
	private ArrayList<Usuario> usuarios;

	/*
	 * Se utiliza una variable de tipo HashMap para gestionar los filtros que aplica
	 * el usuario El HashMap permite utilizar pares de datos <Key,Value> de esta
	 * manera cada vez que el usuario actualice los valores de los filtros, al tener
	 * el mismo Key se reemplaza el Value
	 */
	private Map filtros;
	private JTable tblUsuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					ViewAnalista frame = new ViewAnalista();
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
	public ViewAnalista() {
		usuarios = (ArrayList) BeanIntances.user().findAll(Usuario.class);

		filtros = new HashMap();
		setTitle("Listado de Usuarios");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnActivar = new JButton("Activar");
		btnActivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tblUsuarios.getSelectedRow();
				try {
					
					Long id = Long.parseLong(tblUsuarios.getModel().getValueAt(fila, 0).toString());
					Usuario usu = BeanIntances.user().findById(Usuario.class, id);
					
					if (usu != null && usu.getEstadoUsuario()==EstadoUsuario.SIN_VALIDAR || usu.getEstadoUsuario()==EstadoUsuario.ELIMINADO ) {
						usu.setEstadoUsuario(EstadoUsuario.VALIDADO);
						BeanIntances.user().updateEstadoUsuario(id, usu.getEstadoUsuario());
						
						usuarios = (ArrayList) BeanIntances.user().findAll(Usuario.class);
						filtrarListaUsuarios(tblUsuarios, filtros);
						
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un usuario primero");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Seleccione un usuario primero");
				}
			}
		});
		btnActivar.setBounds(12, 335, 105, 27);
		panel.add(btnActivar);

		JComboBox<Roles> comboTipoUsuario = new JComboBox();
		// Se utiliza el evento action performed para capturar cada vez que se cambia el
		// valor del comboBox
		comboTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = comboTipoUsuario.getSelectedItem().toString();

				switch (tipo) {
				case "ESTUDIANTE": {
					filtros.put("TIPO", "Estudiante");
					txtGeneracion.setEditable(true);
					break;
				}
				case "ANALISTA": {
					filtros.put("TIPO", "Analista");
					txtGeneracion.setEditable(false);
					break;
				}
				case "TUTOR": {
					filtros.put("TIPO", "Tutor");
					txtGeneracion.setEditable(false);
					break;
				}

				}
				// se llama al método que actualiza la lista en base a los filtros seleccionados
				filtrarListaUsuarios(tblUsuarios, filtros);
			}
		});
		comboTipoUsuario.setBounds(97, 13, 168, 26);
		panel.add(comboTipoUsuario);

		JComboBox comboITR = new JComboBox();
		comboITR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros.put("ITR", comboITR.getSelectedItem());

				// se llama al método que actualiza la lista en base a los filtros seleccionados
				filtrarListaUsuarios(tblUsuarios, filtros);
			}
		});
		comboITR.setBounds(97, 50, 168, 26);
		panel.add(comboITR);

		txtGeneracion = new JTextField();
		txtGeneracion.setBounds(97, 125, 168, 21);
		panel.add(txtGeneracion);
		txtGeneracion.setColumns(10);
		txtGeneracion.setEditable(false);

		DocumentListener l1 = new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					filtros.put("GENERACION", Integer.parseInt(txtGeneracion.getText()));
					filtrarListaUsuarios(tblUsuarios, filtros);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					filtros.put("GENERACION", Integer.parseInt(txtGeneracion.getText()));
					filtrarListaUsuarios(tblUsuarios, filtros);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					filtros.put("GENERACION", Integer.parseInt(txtGeneracion.getText()));
					filtrarListaUsuarios(tblUsuarios, filtros);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		};
		txtGeneracion.getDocument().addDocumentListener(l1);

		JComboBox<EstadoUsuario> comboEstado = new JComboBox();
		comboEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros.put("ESTADO", comboEstado.getSelectedItem());
				// se llama al método que actualiza la lista en base a los filtros seleccionados
				filtrarListaUsuarios(tblUsuarios, filtros);
			}
		});
		comboEstado.setBounds(97, 87, 168, 26);
		panel.add(comboEstado);

		JLabel lblNewLabel_1 = new JLabel("Estado");
		lblNewLabel_1.setBounds(12, 89, 60, 17);
		panel.add(lblNewLabel_1);

		JLabel lblGeneracion = new JLabel("Generación");
		lblGeneracion.setBounds(12, 127, 96, 17);
		panel.add(lblGeneracion);
		JLabel lblNewLabel_3 = new JLabel("Usuario");
		lblNewLabel_3.setBounds(12, 13, 60, 17);
		panel.add(lblNewLabel_3);

		JButton btnDesactivar = new JButton("Desactivar");
		btnDesactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tblUsuarios.getSelectedRow();
				try {
					Long id = Long.parseLong(tblUsuarios.getModel().getValueAt(fila, 0).toString());
					Usuario usu = BeanIntances.user().findById(Usuario.class, id);
					
					if (usu != null && usu.getEstadoUsuario()==EstadoUsuario.VALIDADO) {
						usu.setEstadoUsuario(EstadoUsuario.ELIMINADO);
						BeanIntances.user().updateEstadoUsuario(id, usu.getEstadoUsuario());
						
						usuarios = (ArrayList) BeanIntances.user().findAll(Usuario.class);
						filtrarListaUsuarios(tblUsuarios, filtros);
						
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un usuario primero");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Seleccione un usuario primero");
				}
			}
		});
		btnDesactivar.setBounds(224, 335, 105, 27);
		panel.add(btnDesactivar);

		JButton btnAbrirUsuario = new JButton("Ver detalles");
		btnAbrirUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tblUsuarios.getSelectedRow();
				try {
					Long id = Long.parseLong(tblUsuarios.getModel().getValueAt(fila, 0).toString());
					Usuario usu = BeanIntances.user().findById(Usuario.class, id);
					if (usu != null) {
						// lo de modificar
						//Registrarse reg = new Registrarse(usu);
						//reg.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un usuario primero");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Seleccione un usuario primero");
				}
			}
		});
		btnAbrirUsuario.setBounds(436, 335, 105, 27);
		panel.add(btnAbrirUsuario);

		JLabel lblNewLabel_4 = new JLabel("Generación");
		lblNewLabel_4.setBounds(12, 127, 96, 17);
		panel.add(lblNewLabel_4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 158, 520, 151);
		panel.add(scrollPane);

		tblUsuarios = new JTable();
		scrollPane.setViewportView(tblUsuarios);
		String columns[] = { "id", "Documento", "Nombres", "Apellidos", "ITR", "Estado" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0);
		tblUsuarios.setModel(modeloJTable);

		// Cargo el listado de usuario a la tabla
		cargarUsuarios(tblUsuarios, usuarios);

		// cargo los combos con los valores para poder hacer los filtros
		this.cargarCombosFiltros(comboEstado, comboTipoUsuario, comboITR);
		
		JLabel lblNewLabel = new JLabel("ITR");
		lblNewLabel.setBounds(12, 55, 60, 17);
		panel.add(lblNewLabel);

	}

	public void cargarUsuarios(JTable lstUsuarios, ArrayList<Usuario> listaUsuarios) {
		String columns[] = { "id", "Documento", "Nombres", "Apellidos", "ITR", "Estado" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0);
		if (lstUsuarios != null) {
			for (Usuario usu : listaUsuarios) {
				Long id = usu.getIdUsuario();
				String doc = usu.getDocumento();
				String nombres = usu.getNombres();
				String apellidos = usu.getApellidos();
				Long idITR = usu.getItr().getIdItr();
				String tipo = usu.getEstadoUsuario().name();
				Object[] datos = { id, doc, nombres, apellidos, idITR, tipo };
				modeloJTable.addRow(datos);
			}
		}
		tblUsuarios.setModel(modeloJTable);
	}

	// Método para cargar los valores que contienen los filtros
	public void cargarCombosFiltros(JComboBox comboEstado, JComboBox comboTipoUsuario, JComboBox comboITR) {

		/*
		 * esto queda hardcoded pero falta un método que traiga ITR
		 * 
		 */
		comboEstado.addItem(EstadoUsuario.VALIDADO);
		comboEstado.addItem(EstadoUsuario.ELIMINADO);
		comboEstado.addItem(EstadoUsuario.SIN_VALIDAR);

		comboTipoUsuario.addItem(Roles.ANALISTA);
		comboTipoUsuario.addItem(Roles.ESTUDIANTE);
		comboTipoUsuario.addItem(Roles.TUTOR);

		comboITR.addItem(1);
		comboITR.addItem(2);
	}

	public void filtrarListaUsuarios(JTable lstUsuarios, Map filtros) {

		ArrayList<Usuario> filtrados = new ArrayList<Usuario>();
		if (usuarios != null && !filtros.isEmpty() && filtros.get("ITR") != null) {

			for (Usuario usu : usuarios) {
				// Cargo la info del usuario necesaria para los filtros
				Long idITR = usu.getItr().getIdItr();
				String estado = usu.getEstadoUsuario().name();
				String tipo = usu.getClass().getSimpleName().trim();
				Integer gen = 0;
				if (filtros.get("TIPO").toString().equalsIgnoreCase(Roles.ESTUDIANTE.name())) {
					ArrayList<Estudiante> estudiantes = (ArrayList) BeanIntances.user().findAllEstudiantes();

					for (Estudiante est : estudiantes) {
						if (est.getIdUsuario() == usu.getIdUsuario()) {
							gen = est.getGeneracion();
						}

					}
				}

				// proceso que se cumplan las tres condiciones
				if (gen != null && gen != 0 && filtros.get("GENERACION") != null) {
					System.out.println("ENTRA NOT NULL" + " GEN " + gen + " FILTRO "
							+ Integer.parseInt(filtros.get("GENERACION").toString()));
					if (idITR == Long.parseLong(filtros.get("ITR").toString())
							&& tipo.equalsIgnoreCase(filtros.get("TIPO").toString())
							&& estado.equalsIgnoreCase(filtros.get("ESTADO").toString())
							&& gen == Integer.parseInt(filtros.get("GENERACION").toString())) {

						filtrados.add(usu);
					}
				} else {
					if (idITR == Long.parseLong(filtros.get("ITR").toString())
							&& tipo.equalsIgnoreCase(filtros.get("TIPO").toString())
							&& estado.equalsIgnoreCase(filtros.get("ESTADO").toString())) {
						filtrados.add(usu);
					}
				}
			}
		} else {

			cargarUsuarios(tblUsuarios, usuarios);
		}

		cargarUsuarios(tblUsuarios, filtrados);
	}
}
