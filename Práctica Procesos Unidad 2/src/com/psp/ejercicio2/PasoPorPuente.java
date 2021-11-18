package com.psp.ejercicio2;

import java.util.Random;

class Puente {

	private static final int PESO_MAXIMO = 200;
	private static final int MAX_PERSONAS = 3;
	private int peso = 0;
	private int numPersonas = 0;

	synchronized public int getPeso() {
		return peso;
	}

	synchronized public int getNumPersonas() {
		return numPersonas;
	}

	synchronized public boolean autorizacionPaso(Persona persona) {

		boolean result;

		if (this.peso + persona.getPeso() <= Puente.PESO_MAXIMO && this.numPersonas < Puente.MAX_PERSONAS) {
			this.numPersonas++;
			this.peso += persona.getPeso();
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	synchronized public void terminaPaso(Persona persona) {
		this.peso -= persona.getPeso();
		this.numPersonas--;

	}
}

class Persona implements Runnable {
	private final Puente puente;

	private final String idPersona;
	private final int peso;
	private final int tMinPaso, tMaxPaso;

	public int getPeso() {
		return peso;
	}

	Persona(Puente puente, int peso, int tMinPaso, int tMaxPaso, String idP) {
		this.puente = puente;
		this.peso = peso;
		this.tMinPaso = tMinPaso;
		this.tMaxPaso = tMaxPaso;
		this.idPersona = idP;
	}

	@Override
	public void run() {

		System.out.println("- " + idPersona + " de " + this.peso + " kg quiere cruzar, en el puente " + puente.getPeso()
				+ " kg, " + puente.getNumPersonas() + " persona.");
		boolean autorizado = false;
		while (!autorizado) {
			synchronized (this.puente) {
				autorizado = this.puente.autorizacionPaso(this);
				if (!autorizado) {
					try {
						System.out.println("# " + idPersona + " debe esperar.");
						this.puente.wait();
					} catch (InterruptedException ex) {
						System.out.println("Ha habido un error: " + ex.getMessage());
					}
					System.out
							.println("> " + idPersona + " con peso " + this.peso + " puede cruzar, puente soporta peso "
									+ puente.getPeso() + ", con " + puente.getNumPersonas() + " personas.");
				}
			}
		}

		Random r = new Random();
		int tiempoPaso = this.tMinPaso + r.nextInt(this.tMaxPaso - this.tMinPaso + 1);
		System.out.println(this.idPersona + " va a tardar " + tiempoPaso + " en cruzar");
		try {

			Thread.sleep(1000 * tiempoPaso);

		} catch (InterruptedException ex) {
			System.out.println("Ha habido un error: " + ex.getMessage());
		}

		synchronized (this.puente) {
			this.puente.terminaPaso(this);
			puente.notifyAll();
			System.out.println("< " + idPersona + " sale del puente, puente soporta peso " + puente.getPeso() + ", "
					+ puente.getNumPersonas() + " persona.");
		}
	}
}

public class PasoPorPuente {

	public static void main(String[] args) {

		final Puente puente = new Puente();

		int tMinParaLlegadaPersona = 1;
		int tMaxParaLlegadaPersona = 30;
		int tMinPaso = 10;
		int tMaxPaso = 50;
		int minPesoPersona = 40;
		int maxPesoPersona = 120;

		System.out.println(">>>>>>>>>>>> Comienza simulación.");
		Random r = new Random();
		int idPersona = 1;

		while (true) {

			int tParaLlegadaPersona = tMinParaLlegadaPersona
					+ r.nextInt(tMaxParaLlegadaPersona - tMinParaLlegadaPersona + 1);
			System.out.println("Siguiente persona llega en " + tParaLlegadaPersona);

			int pesoPersona = minPesoPersona + r.nextInt(maxPesoPersona - minPesoPersona + 1);

			try {
				Thread.sleep(1000 * tParaLlegadaPersona);
			} catch (InterruptedException ex) {
				System.out.println("Ha habido un error: " + ex.getMessage());
			}

			Thread hiloPersona = new Thread(new Persona(puente, pesoPersona, tMinPaso, tMaxPaso, "P" + idPersona));
			hiloPersona.start();

			idPersona++;

		}

	}

}
