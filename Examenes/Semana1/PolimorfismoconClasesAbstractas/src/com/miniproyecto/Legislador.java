package com.miniproyecto;

//Clase padre abstracta.
public abstract class Legislador {
	
	//Atributos
	String name;
	int edad;
	String partidoPolitico;
	String municipioQueRepresenta;
	
	//Constructor
	public Legislador(String name, int edad, String partidoPolitico, String
						municipioQueRepresenta) {
		this.name = name;
		this.edad = edad;
		this.partidoPolitico = partidoPolitico;
		this.municipioQueRepresenta = municipioQueRepresenta;
	}
	
	//Metodo abstracto
	public abstract String getCamaraEnQueTrabaja();
	
	//Overriding para obtener el contenido en lugar del lugar en memoria del objeto
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " " + name + " de edad " 
					+ edad + " del partido " + partidoPolitico 
					+ " representa a " + municipioQueRepresenta;
	}
	
	
}
