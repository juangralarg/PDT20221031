package com.entities.enums;

public enum EstadoUsuario  {
	SIN_VALIDAR,
	VALIDADO,
	ELIMINADO;
	
	@Override
	public String toString() {
		return name().charAt(0) + name().substring(1).toLowerCase();
	}
}
