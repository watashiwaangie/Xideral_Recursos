package com.nocheEstrellas;

public class StaffCharlas extends Rol{
	
	public StaffCharlas(String ColorPlayera) {
		super(ColorPlayera);
	}
	
	@Override
	public void actividad() {
		System.out.println("Staff Charla, sus actividades son "
				+ "tomar tiempo expositores, "
				+ "verificar audio, pasar micrófono, " 
				+ "su playera es color "
				+ ColorPlayera);
		
	}

}
