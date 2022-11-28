package com.miniproyecto;

public class Diputado implements Legislador{
	
	String name;
	int edad;
	String partidoPolitico;
	String municipioQueRepresenta;

	public Diputado(String name, int edad, 
			String partidoPolitico, 
			String municipioQueRepresenta) {
		super();
		this.name = name;
		this.edad = edad;
		this.partidoPolitico = partidoPolitico;
		this.municipioQueRepresenta = municipioQueRepresenta;
	}

	@Override
	public String getCamaraEnQueTrabaja() {
		return "Diputados"; 
		
	}
	
	public String toString() {
		return  "La diputada " + name + " de edad "
				+ edad + " del partido " + partidoPolitico 
				+ " representa a " + municipioQueRepresenta;
	}

}
