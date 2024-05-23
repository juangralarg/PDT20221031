package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.entities.enums.Departamento;

import java.util.List;


/**
 * The persistent class for the ITRS database table.
 * 
 */
@Entity
@Table(name="ITRS")
public class Itr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "SEQ_ITRS")
	@Column(name="ID_ITR")
	private Long idItr;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Departamento departamento;
	
	@Column(nullable = false)
	private Boolean estado;

	@Column(nullable = false, unique = true)
	private String nombre;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="itr")
	private List<Usuario> usuarios;

	public Itr() {
	}

	public Long getIdItr() {
		return this.idItr;
	}

	public void setIdItr(Long idItr) {
		this.idItr = idItr;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setItr(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setItr(null);

		return usuario;
	}

}