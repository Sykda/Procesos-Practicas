package com.psp.ejercicio3;

public class Principal {

	public static void main(String[] args) {

		Hilo uno, dos, tres;

		uno = new Hilo("Galgo");
		dos = new Hilo("Conejo");
		tres = new Hilo("Liebre");

		uno.start();
		dos.start();
		tres.start();

		try {
			uno.join();
			dos.join();
		} catch (InterruptedException e) {

			System.out.println("Hilo principal interrumpido.");
		}
	}
}
