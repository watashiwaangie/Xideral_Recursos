package com.curso;


/*
 * Al aplicar final en las clases estamos haciendo que dicha clase
 * ya no pueda heredar (En la clase hija jaguar ahora habría error al hacer el override de correr.)*/

//public final class Felino{

public class Felino {

	String nombre;

	public Felino(String nombre) {
		this.nombre = nombre;
	}
	
	//Al aplicar final a un método ya no se permite el override al heredar.
	//final void Correr(){
	void Correr() {
		System.out.println("Es veloz");
	}
	
	@Override
	public String toString() {
		return "Felino [nombre = " + nombre + "]"; 	 
	}
}
