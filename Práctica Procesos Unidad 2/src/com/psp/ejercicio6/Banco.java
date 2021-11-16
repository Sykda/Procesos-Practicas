package com.psp.ejercicio6;

import java.util.ArrayList;

public class Banco {

	private final static Banco banco = new Banco();

	private ArrayList<CuentaBancaria> cuenta = new ArrayList<CuentaBancaria>();

	private Banco() {

		cuenta.add(new CuentaBancaria(0));
	}

	public static Banco getInstance() {
		return banco;
	}

	public CuentaBancaria getCuenta(final Integer saldo) {
		return cuenta.get(saldo);
	}
}