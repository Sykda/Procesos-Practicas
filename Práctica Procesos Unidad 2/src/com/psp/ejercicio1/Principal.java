package com.psp.ejercicio1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Principal {

	public static void main(String a[]) throws InterruptedException {

		Barberia tienda = new Barberia();

		Barbero barbero = new Barbero(tienda);
		LlegadaClientes cliente = new LlegadaClientes(tienda);

		ScheduledExecutorService ses = Executors.newScheduledThreadPool(0);
		ses.execute(barbero);

		Thread hiloCliente = new Thread(cliente);
		hiloCliente.start();

		ses.awaitTermination(50, TimeUnit.SECONDS); // Cuanto tiempo va a estar abierto. Si está en medio de un corte se
													// va a medio afeitar.
		ses.shutdown();
		System.out.println("Hora de irse a casa");
		System.exit(0);
	}
}