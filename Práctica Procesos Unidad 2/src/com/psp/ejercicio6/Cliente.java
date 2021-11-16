package com.psp.ejercicio6;

public class Cliente implements Runnable {

	@Override
	public void run() {

		Banco banco = Banco.getInstance();
		CuentaBancaria cuenta = banco.getCuenta(0);

		synchronized (cuenta) {

			cuenta.depositar(1000);
			cuenta.retirar(2000);
		}
	}
}
