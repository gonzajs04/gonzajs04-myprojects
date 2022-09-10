package com.dirty.jugadores;

import com.dirty.camara.Camara;
import com.dirty.interfaz.AumentarPuntuacion;
import com.dirty.interfaz.Hud;
import com.dirty.personajes.Personaje;

public abstract class JugadorBASE implements AumentarPuntuacion{

	protected String nombre;
	protected float puntos = 0.0f;
	protected int i = 0;
	protected int d = 0; 
	protected int acum = 0; 
	public Personaje personaje;
	public Hud hud;
	public Camara camara;


	public JugadorBASE(String nombre) {
		this.nombre = nombre;
		
	}

	public void setPersonajeJugador(Personaje personaje) {
		this.personaje = personaje;
		
	}

	public Personaje getPersonajeJugador() {
		return this.personaje;
	}

	public String getNombre() {
		return nombre;
	}

	public void setCamara(Camara camara){
		this.camara = camara;
	}

	public Camara getCamara(){
		return this.camara;
	}

	public Hud getHud(){
		return this.hud;

	}
	public void setHud(Hud hud){
		this.hud = hud;
	}

	public void setPuntos(float puntos) {
	
		this.puntos += puntos; //SE SUMAN LOS PUNTOS QUE GANA AL SALTAR.


	}

	public float getPuntos() {
		return this.puntos;
	}

}
