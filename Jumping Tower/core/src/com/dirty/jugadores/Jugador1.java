package com.dirty.jugadores;

public class Jugador1 extends JugadorBASE{
	float puntuacion;

	public Jugador1(String nombre) {
		super(nombre);
		
	}


	@Override
	public void sumarPuntos(float puntos) {
		
		this.puntuacion = puntos;
		if(this.puntuacion<0)
		{
			this.puntuacion = 0;
		}
		
	}
	
	public float getPuntos() {
		return this.puntuacion;
	}

}
