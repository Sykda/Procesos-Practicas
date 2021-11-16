package com.psp.ejercicio3;

public class Hilo extends Thread {

	public Hilo(String str) {
		super(str);
	}

	public void run() {

		for (int i = 0; i < 10; i++) {

			System.out.println(i + "00 -->" + getName());

			try {

				Hilo.sleep((long) Math.random());

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		System.out.println("Ha llegado --> " + getName());
	}
}
