package com.nocheEstrellas;

public class Principal {

	public static void main(String[] args) {
		
		MiembroAeif m1 = new MiembroAeif("Angie");
		MiembroAeif m2 = new MiembroAeif("Santin");
		
		Inyector.inyectarRol(m1);
		m1.CubrirPuesto();
		
		Inyector.inyectarRol(m2);
		m2.CubrirPuesto();
	}

}
