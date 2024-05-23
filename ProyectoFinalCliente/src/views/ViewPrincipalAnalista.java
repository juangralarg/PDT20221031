package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import org.hibernate.resource.beans.spi.BeanInstanceProducer;

import com.entities.Analista;

import beans.BeanIntances;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewPrincipalAnalista extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Analista p = BeanIntances.user().findById(Analista.class, 4L);
					ViewPrincipalAnalista frame = new ViewPrincipalAnalista(p);
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
	public ViewPrincipalAnalista(Analista analista) {
		setTitle("Menú Principal Analista");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 0, 440, 21);
		panel.add(toolBar);
		
		JPanel panel_1 = new JPanel();
		toolBar.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnPerfil = new JButton("Mi perfil");
		btnPerfil.setIcon(new ImageIcon(ViewPrincipalAnalista.class.getResource("/images/usuario (3).png")));
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPerfilUsuario vista = new ViewPerfilUsuario(analista);
				vista.show();
				//dispose();
			}
		});
		btnPerfil.setBounds(308, 0, 128, 17);
		panel_1.add(btnPerfil);
		
		JLabel lblBienvenida = new JLabel("New label");
		lblBienvenida.setText("Bienvenido: " + analista.getNombreUsuario());
		lblBienvenida.setBounds(0, 0, 196, 17);
		panel_1.add(lblBienvenida);
		
		JButton btnUsuarios = new JButton("Gestión de usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAnalista viewAnalista = new ViewAnalista();
			}
		});
		btnUsuarios.setBounds(31, 78, 173, 27);
		panel.add(btnUsuarios);
		
		JButton btnAccionReclamos = new JButton("Acción Reclamos");
		btnAccionReclamos.setBounds(31, 126, 173, 27);
		panel.add(btnAccionReclamos);
		
		JButton btnAccionConstancia = new JButton("Acción Constancia");
		btnAccionConstancia.setBounds(31, 174, 173, 27);
		panel.add(btnAccionConstancia);
		
		JButton btnITR = new JButton("Gestión ITR");
		btnITR.setBounds(31, 222, 173, 27);
		panel.add(btnITR);
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.setBounds(235, 78, 173, 27);
		panel.add(btnReportes);
	}
}
