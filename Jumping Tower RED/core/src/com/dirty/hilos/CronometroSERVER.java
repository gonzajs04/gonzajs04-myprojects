package com.dirty.hilos;

import com.dirty.configuraciones.ConfiguracionesSERVER;

public class CronometroSERVER {

	protected int tiempoJuego = 500;
	protected float tiempoGastando;

	public float getTiempoJuego() {
		return this.tiempoJuego;
	}

	public void setTiempoJuego(float tiempoGastado) {
		this.tiempoGastando=tiempoGastando;
		if (this.tiempoJuego <= 0) {
			this.tiempoJuego = 0;
			
		
		
			
		}

		if (this.tiempoJuego > 0) {
			this.tiempoJuego -= tiempoGastado;
			
			ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Cronometro/"+ this.tiempoJuego);
		
		}
	}

	public void update() {
		this.setTiempoJuego(1f);


	}

}
