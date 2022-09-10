package com.dirty.utiles;

import com.dirty.jugadores.Jugador1;
import com.dirty.jugadores.Jugador2;

public class reducirVelocidad implements ReducirVelocidadEventListener{

	@Override
	public void reducirVelocidad(float velocidadX, Jugador1 jugador1, Jugador2 jugador2) {
		if(jugador1.personaje.getVelocidadX()>jugador1.personaje.getVelocidadNormal()) {
			jugador1.personaje.reducirVelocidadX(1);
			//System.out.println("Velocidad redujo: "+jugador1.personaje.getVelocidadX());
			
		}
		
		else if(jugador2.personaje.getVelocidadX()>jugador2.personaje.getVelocidadNormal()) {
			jugador2.personaje.reducirVelocidadX(1);
			//System.out.println("Velocidad redujo: "+jugador2.personaje.getVelocidadX());
			
		}
		
	}

}
