package com.dirty.jugadores;

public class Jugador2 extends JugadorBASE{

	
	float puntuacion; 
	
	public Jugador2(String nombre) {
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
