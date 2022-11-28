package com.miniproyecto;

public class Senador extends Legislador {

	public Senador(String name, int edad, String partidoPolitico, String municipioQueRepresenta) {
		super(name, edad, partidoPolitico, municipioQueRepresenta);
	}

	@Override
	public String getCamaraEnQueTrabaja() {
		return "Senadores";
	}

}
