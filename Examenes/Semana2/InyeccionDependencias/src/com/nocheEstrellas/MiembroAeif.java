package com.nocheEstrellas;

public class MiembroAeif {
	
	private String nombre;
	private Rol rol;

	public MiembroAeif(String nombre) {
		this.nombre = nombre;
	}
	
	void CubrirPuesto() {
		System.out.println(nombre);
		rol.actividad();
	}
	
	public Rol getRol() {
		return rol;
	}
	
	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
