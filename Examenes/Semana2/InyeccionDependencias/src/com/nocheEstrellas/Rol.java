package com.nocheEstrellas;

public abstract class Rol {
	
	String ColorPlayera;
	
	public Rol(String ColorPlayera) {
		super();
		this.ColorPlayera = ColorPlayera;
	}
	
	abstract void actividad();
	

}
