package com.psp.ejercicio1;

class Barbero implements Runnable {

	Barberia tienda;

	public Barbero(Barberia tienda) {
		this.tienda = tienda;
	}

	public void run() {

		System.out.println("El barbero abre la barber�a");

		while (true) {
			tienda.cortarPelo();
		}
	}
}