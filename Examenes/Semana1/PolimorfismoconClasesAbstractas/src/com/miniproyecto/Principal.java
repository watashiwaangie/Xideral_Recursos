package com.miniproyecto;
import java.util.*;

public class Principal {

	public static void main(String[] args) {
		
		List<Legislador> legisladores = new ArrayList<>();
		
		legisladores.add(new Diputado("Angelo",45,"PRI", "Solidaridad"));
		legisladores.add(new Diputado("Paola",56,"PAN", "Benito Juarez"));
		legisladores.add(new Diputado("Romina",50,"MORENA", "Tulum"));
		legisladores.add(new Senador("Elizabeth",56,"Mov Naranja", "Tulum"));
		legisladores.add(new Senador("Melissa",48,"Alianza", "Chetumal"));
		legisladores.add(new Senador("Dennis",44,"Partido Verde", "Cozumel"));
		
		for(Legislador legis :legisladores) {
			System.out.println(legis);
			String camaraTrabajo = legis.getCamaraEnQueTrabaja();
			System.out.print ("Este legislador trabaja en la Cámara de "
							+ camaraTrabajo +"\n");
			System.out.print ("-------------------------------------------------------------------------"+"\n");
		}

	}

}
