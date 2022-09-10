package com.dirty.hilos;


public class Cronometro {

	// = 100; // este valor debería regirse por lo que dice en server.
	protected float tiempoJuego; 
	public float getTiempoJuego() {
		return this.tiempoJuego;
	}

	public void setTiempoJuego(float tiempo) {
		if (this.tiempoJuego <= 0) {
			this.tiempoJuego = 0;
		}

		if (this.tiempoJuego > 0) {
			this.tiempoJuego = tiempo;
		}
	}

//	public void update() {
//		this.setTiempoJuego(1f);
//
//
//	}
	
	public void setTiempoJuegoInicio(float tiempoJuegoInicio) 
	{
		tiempoJuego = tiempoJuegoInicio;
	}
	


}
