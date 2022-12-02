package com.curso;

public class Principal {
	
	//Final en primitivos
	
	/* Al usar final con primitos estamos creando constantes, es decir, ya no podremos
	 * cambiar el valor de la variable primitiva:
	 */
	 static final int x = 8;   
	 //x = 5;     	Aqui marca error
	 
	 


	public static void main(String[] args) {
		
		
		/*Final en variables de referencia
		 * No podemos cambiar el objeto al que apunta la variable de referencia:
		 * 
		 */
		 final Felino felino = new Felino("Michi");
		 felino.nombre = "Milk";  //Si podemos cambiar sus atributos 
		 //Pero no el objeto al que apunta:
		 //felino = new Felino("Jerry") Esto es incorrecto, marcaría error
		
		
		
		Jaguar jaguar1 = new Jaguar("Toño");
		
		System.out.println(felino);
		System.out.println(jaguar1);
	}
}
