package com.psp.ejercicio2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Filosofo implements Runnable {

    private final Object leftFork;
    private final Object rightFork;
    DateTimeFormatter isoFecha = DateTimeFormatter.ofPattern("hh:mm:ss");

    Filosofo(Object left, Object right) {
        this.leftFork = left;
        this.rightFork = right;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 3000)));
    }

    @Override
    public void run() {
        try {
          
          doAction("pensando..."); 

		  while (true) {
               
                synchronized (leftFork) {
                		doAction(LocalTime.now().format(isoFecha).toString()+": Coje el palillo izquierdo");
   
                	synchronized (rightFork) {
                		doAction(LocalTime.now().format(isoFecha).toString()+": Coje el palillo derecho");
                    }doAction(LocalTime.now().format(isoFecha).toString()+": Deja el palillo derecho");
                }doAction(LocalTime.now().format(isoFecha).toString()+": Deja el palillo izquierdo");
                doAction(LocalTime.now().format(isoFecha).toString()+": pensando...");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}