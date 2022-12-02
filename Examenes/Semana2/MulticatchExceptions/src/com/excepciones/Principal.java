package com.excepciones;

public class Principal {

	public static void main(String[] args) {
		
		int Saldo = 3000;
		int Retiro = 500;
		int SaldoFinal = 0;
		
		try {
			SaldoFinal = Retirar(Saldo, Retiro);
			System.out.println("Saldo actual: "+ SaldoFinal);
		} catch (SinBilletesException | SaldoNoDisponibleException e) { //multicatch
			e.printStackTrace();
			System.out.println("Intenta de nuevo");
		}
		finally {
			System.out.println("Cierra sesión");
		}
		
	}
	
	private static int Retirar(int Saldo, int Retiro) throws SinBilletesException, SaldoNoDisponibleException {
		if (Retiro < 100) {
			throw new SinBilletesException("Solo retiramos múltiplos de 100");
		} 
		if(Retiro>Saldo) {
			throw new SaldoNoDisponibleException("No cuentas con la cantidad suficiente para el retiro"); 
		}
		return Saldo - Retiro;
	}


}
