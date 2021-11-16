package com.psp.ejercicio4;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Rana {

	public static void main(String[] args) {

		ScheduledExecutorService ses = Executors.newScheduledThreadPool(0);
		Comer comer = new Comer();
		
		try {

			System.out.println("La rana sale a comer a las " + Comer.fecha());

			ses.scheduleWithFixedDelay(comer, 5, 3, TimeUnit.SECONDS);
			ses.awaitTermination(30, TimeUnit.SECONDS);			
			ses.shutdown();
			System.out.println("La rana se ha dormido");
			
		} catch (InterruptedException e) {
			System.out.println("Ha habido un error: " + e.getMessage());
		}
	}
}

class Comer implements Runnable {

	@Override
	public void run() {

		System.out.println("La rana come una mosca a los " + fecha());

	}

	public static String fecha() {
		DateTimeFormatter isoFecha = DateTimeFormatter.ofPattern("hh:mm:ss");
		return LocalTime.now().format(isoFecha).toString();
	}
}