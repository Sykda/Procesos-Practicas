package com.psp.ejercicio1;

class Cliente implements Runnable {
	
	String nombre;
	Barberia tienda;

	public Cliente(Barberia tienda) {
		this.tienda = tienda;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void run() {
		tienda.add(this);
	}
}