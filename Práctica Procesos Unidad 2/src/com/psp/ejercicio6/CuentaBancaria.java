package com.psp.ejercicio6;

public class CuentaBancaria {

	private Integer saldo;

	public CuentaBancaria(Integer saldo) {
		this.saldo = saldo;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void depositar(final Integer cantidad) {

		this.saldo = this.saldo + cantidad;

		System.out.println(Thread.currentThread().getName() + " Depositando : " + cantidad + "€");
		System.out.println("\tEn la cuenta quedan: " + this.saldo + "€");

	}

	public Integer retirar(final Integer cantidad) {

		System.out.println(Thread.currentThread().getName() + " Intentado Retirar " + cantidad + "€");

		if (saldo < cantidad) {
			System.out.println("\tERROR: NO HAY DINERO EN CUENTA");
			return 0;
		}

		this.saldo = this.saldo - cantidad;

		System.out.println(
				Thread.currentThread().getName() + " Saca dinero con éxito. En la cuenta quedan: " + this.saldo + "€");

		return saldo;
	}
}