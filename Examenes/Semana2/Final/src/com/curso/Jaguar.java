package com.curso;

public class Jaguar extends Felino{

	public Jaguar(String nombre) {
		super(nombre);
	}
	
	@Override
	void Correr() {
		System.out.println("Soy el más veloz");
	}
	
	@Override
	public String toString() {
		return "Jaguar [nombre = " + nombre + "]"; 	 
	}
}
