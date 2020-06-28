package com.cibertec.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {

	private String idUsuario;
	private String nombre;
	private String apellido;
	private String login;
	private String clave;
	private String dni;
	private String nombreCompleto;

	public String getNombreCompleto() {
		nombreCompleto = nombre.concat(" ").concat(apellido);
		return nombreCompleto;
	}

}
