package com.nocheEstrellas;

public class Inyector {
	
	static void inyectarRol(MiembroAeif m) {
		m.setRol(getRol());
	}
	
	private static Rol getRol() {
		
		Rol[] arrayRol = {new Moderador("rojo"),new StaffCharlas("azul"),
						  new StaffCharlas("verde")};
	
		int numero = (int)(Math.random() * arrayRol.length);
		
		return arrayRol[numero];
	}
	
}
