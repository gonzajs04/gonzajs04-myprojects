package com.dirty.utiles;

import com.dirty.jugadores.Jugador1SERVER;
import com.dirty.jugadores.Jugador2SERVER;

public class reducirVelocidadSERVER implements ReducirVelocidadEventListenerSERVER{

	@Override
	public void reducirVelocidad(float velocidadX, Jugador1SERVER jugador1, Jugador2SERVER jugador2) {
		if(jugador1.personaje.getVelocidadX()>jugador1.personaje.getVelocidadNormal()) {
			jugador1.personaje.reducirVelocidadX(1);
			//System.out.println("Velocidad redujo: "+jugador1.personaje.getVelocidadX());
			
		} else if(jugador2.personaje.getVelocidadX()>jugador2.personaje.getVelocidadNormal()) {
			jugador2.personaje.reducirVelocidadX(1);
			//System.out.println("Velocidad redujo: "+jugador2.personaje.getVelocidadX());
			
		}
		
	}

}
