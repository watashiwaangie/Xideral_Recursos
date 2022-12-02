package com.nocheEstrellas;

public class Moderador extends Rol{
	
	public Moderador(String ColorPlayera) {
		super(ColorPlayera);
	}

	@Override
	public void actividad() {
		System.out.println("Moderadora, su resposabilidada es presentar "
				+ "a los expositores, "
				+ "su playera es color "+ ColorPlayera);
	}

}
