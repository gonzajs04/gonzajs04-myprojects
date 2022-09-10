package com.dirty.jugadores;

import com.dirty.camara.CamaraSERVER;
import com.dirty.interfaz.AumentarPuntuacionSERVER;
import com.dirty.personajes.PersonajeSERVER;

public abstract class JugadorBASESERVER implements AumentarPuntuacionSERVER{

	protected String nombre;
	protected float puntos = 0.0f;
	protected int i = 0;
	protected int d = 0; 
	protected int acum = 0; 
	public PersonajeSERVER personaje;
	public CamaraSERVER camara;

	

	public JugadorBASESERVER(String nombre) {
		this.nombre = nombre;
	}

	public void setPersonajeJugador(PersonajeSERVER personaje) {
		this.personaje = personaje;
	}

	public PersonajeSERVER getPersonajeJugador() {
		return this.personaje;
	}

	public String getNombre() {
		return nombre;
	}

	public void setPuntos(float puntos) {
	
		this.puntos += puntos; //SE SUMAN LOS PUNTOS QUE GANA AL SALTAR.


	}

	public float getPuntos() {
		return this.puntos;
	}
	
	public CamaraSERVER getCamara() {
		return camara;
	}

	public void setCamara() {
		camara = new CamaraSERVER();
	}

}
