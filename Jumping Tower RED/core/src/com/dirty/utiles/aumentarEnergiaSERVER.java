package com.dirty.utiles;

import com.dirty.jugadores.Jugador1SERVER;
import com.dirty.jugadores.Jugador2SERVER;

public class aumentarEnergiaSERVER implements AumentarEnergiaEventListenerSERVER {

	@Override
	public void aumentarEnergia(float energia, Jugador1SERVER jugador1, Jugador2SERVER jugador2) {
		if (jugador1.personaje.getVelocidadX() == jugador1.personaje.getVelocidadNormal()) {
			jugador1.personaje.setEnergia(energia, false);
			//System.out.println("Aumento energia del jugador " + jugador1.personaje.getEnergia());
			if (jugador1.personaje.getEnergia() == jugador1.personaje.getEnergiaInicial()) {

				jugador1.personaje.setSprinteo(true);
			//	System.out.println("Se seteo sprinteo a true");

			}

		}
		else if (jugador2.personaje.getVelocidadX() == jugador2.personaje.getVelocidadNormal()) {
			jugador2.personaje.setEnergia(energia, false);
			//System.out.println("Aumento energia del jugador " + jugador2.personaje.getEnergia());
			if (jugador2.personaje.getEnergia() == jugador2.personaje.getEnergiaInicial()) {

				jugador2.personaje.setSprinteo(true);
			//	System.out.println("Se seteo sprinteo a true");

			}

		}
	}

}
