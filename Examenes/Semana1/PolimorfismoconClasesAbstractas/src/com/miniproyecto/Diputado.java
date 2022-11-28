package com.miniproyecto;

public class Diputado extends Legislador{

	public Diputado(String name, int edad, String partidoPolitico, 
					String municipioQueRepresenta) {
		super(name, edad, partidoPolitico, municipioQueRepresenta);
	}

	@Override
	public String getCamaraEnQueTrabaja() {
		return "Diputados"; //revisar como devolver
		
	}

}
