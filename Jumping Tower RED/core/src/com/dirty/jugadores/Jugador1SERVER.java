package com.dirty.jugadores;

import com.dirty.configuraciones.ConfiguracionesSERVER;


public class Jugador1SERVER extends JugadorBASESERVER{
	float puntuacion;

	public Jugador1SERVER(String nombre) {
		super(nombre);
		
	}


	@Override
	public void sumarPuntos(float puntos) {
		
		this.puntuacion += puntos;
		ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Puntuacion/Jugador1/" +this.puntuacion);
		System.out.println(this.puntuacion);
		if(this.puntuacion<0)
		{
			this.puntuacion = 0;
		}
		
	}
	
	public float getPuntos() {
		return this.puntuacion;
	}

}
