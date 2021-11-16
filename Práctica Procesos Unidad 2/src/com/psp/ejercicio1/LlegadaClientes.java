package com.psp.ejercicio1;

import java.util.concurrent.TimeUnit;

//Un hilo para generar 10 clientes
class LlegadaClientes implements Runnable {

	Barberia tienda;

	public LlegadaClientes(Barberia tienda) {
		this.tienda = tienda;
	}

	public void run() {

		for (int i = 1; i <= 10; i++) {

			Cliente customer = new Cliente(tienda);
			Thread thcustomer = new Thread(customer);
			customer.setNombre("" + i);
			thcustomer.start();

			try {
				TimeUnit.SECONDS.sleep((long) (Math.random() * 5)); // Tiempo que tardan en llegar los clientes
			} catch (InterruptedException e) {
				System.out.println("Ha habido un error: " + e.getMessage());
			}
		}
	}
}