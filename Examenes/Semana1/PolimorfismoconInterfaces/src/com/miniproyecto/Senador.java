package com.miniproyecto;

public class Senador implements Legislador {
	
	String name;
	int edad;
	String partidoPolitico;
	String municipioQueRepresenta;

	public Senador(String name, int edad, String partidoPolitico, 
		   String municipioQueRepresenta) {
		this.name = name;
		this.edad = edad;
		this.partidoPolitico = partidoPolitico;
		this.municipioQueRepresenta = municipioQueRepresenta;
	}
	
	public String toString() {
		return  "La senadora " + name + " de edad "
				+ edad + " del partido " + partidoPolitico 
				+ " representa a " + municipioQueRepresenta;
	}


	@Override
	public String getCamaraEnQueTrabaja() {
		return "Senadores";
	}

}
