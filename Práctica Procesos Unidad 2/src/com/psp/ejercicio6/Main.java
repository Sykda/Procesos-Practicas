package com.psp.ejercicio6;

public class Main {
	public static void main(String[] args) {

		Thread hilo1 = new Thread(new Cliente());
		Thread hilo2 = new Thread(new Cliente());
		Thread hilo3 = new Thread(new Cliente());

		hilo1.setName("Jhon");
		hilo2.setName("Elena");
		hilo3.setName("Juan");

		hilo1.start();
		hilo2.start();
		hilo3.start();
	}
}

//Podr�an usarse synchronize a nivel de m�todo en lugar de objeto o un sem�foro