package com.psp.ejercicio1;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class Barberia {

	int sillas;
	List<Cliente> listaClientes;

	public Barberia() {

		sillas = 5;
		listaClientes = new LinkedList<Cliente>();
	}

	public void cortarPelo() {
		Cliente cliente;
		System.out.println("El barbero espera a clientes");

		synchronized (listaClientes) {
			while (listaClientes.size() == 0) {
				System.out.println("El barbero se duerme");

				try {
					listaClientes.wait();
				} catch (InterruptedException e) {
					System.out.println("Ha habido un error: " + e.getMessage());
				}
				System.out.println("El barbero se despierta");
			}
			cliente = (Cliente) ((LinkedList<?>) listaClientes).poll(); // devuelve el primer elemento de la lista y lo
																		// elimina
		}

		// Tiempo que tarda en cortar el pelo
		try {
			System.out.println("Cortando el pelo al cliente " + cliente.getNombre());
			TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
		} catch (InterruptedException e) {
			System.out.println("Ha habido un error: " + e.getMessage());
		}
		System.out.println("Completado el corte de pelo al cliente " + cliente.getNombre());
	}

	public void add(Cliente cliente) {
		System.out.println("El cliente " + cliente.getNombre() + " entra en la barbería");

		synchronized (listaClientes) {
			if (listaClientes.size() == sillas) {
				System.out.println("No hay sillas para el cliente " + cliente.getNombre());
				System.out.println("El cliente " + cliente.getNombre() + " se da una vuelta");
				return;
			}

			((LinkedList<Cliente>) listaClientes).offer(cliente); // Añade un cliente a la lista en útima posición.
			System.out.println("El cliente " + cliente.getNombre() + " se sienta");

			if (listaClientes.size() == 1)
				listaClientes.notify();
		}
	}
}