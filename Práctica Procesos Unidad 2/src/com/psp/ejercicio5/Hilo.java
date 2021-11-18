package com.psp.ejercicio5;

import java.util.Random;

class Hilo implements Runnable {

	private final String nombre;

	private Thread hilo;

	public void setHilo(Thread hilo) {
		this.hilo = hilo;
	}

	Hilo(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {

		if (hilo != null) {

			try {
				hilo.join();
			} catch (InterruptedException ex) {
				System.out.println("Hilo principal interrumpido.");
			}
		}

		System.out.printf("Hola, soy el hilo: %s.\n", this.nombre);

		for (int i = 0; i < 5; i++) {
			Random r = new Random();
			int pausa = 10 + r.nextInt(500 - 10);
			System.out.printf("Hilo: %s hace pausa de %d ms.\n", this.nombre, pausa);
			try {
				Thread.sleep(pausa);
			} catch (InterruptedException e) {
				System.out.printf("Hilo %s interrumpido.\n", this.nombre);
			}
		}
		System.out.printf("Hilo %s terminado.\n", this.nombre);
	}
}
