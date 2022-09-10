package com.dirty.utiles;

import com.dirty.jugadores.Jugador1;
import com.dirty.jugadores.Jugador2;

public class aumentarEnergia implements AumentarEnergiaEventListener {

	@Override
	public void aumentarEnergia(float energia, Jugador1 jugador1, Jugador2 jugador2) {
		if( jugador1.personaje.getVelocidadX()==jugador1.personaje.getVelocidadNormal()) {
			jugador1.personaje.setEnergia(energia, false);
			//System.out.println("Aumento energia del jugador " + jugador2.personaje.getEnergia());System.out.println("Aumento energia del jugador "+ jugador1.personaje.getEnergia());
			if(jugador1.personaje.getEnergia()==jugador1.personaje.getEnergiaInicial()) {
				
				//System.out.println("Aumento energia del jugador " + jugador2.personaje.getEnergia());jugador1.personaje.setSprinteo(true);
				//System.out.println("Se seteo sprinteo a true");
				
			}
			
		}
		
		else if( jugador2.personaje.getVelocidadX()==jugador2.personaje.getVelocidadNormal()) {
			jugador2.personaje.setEnergia(energia, false);
		//System.out.println("Aumento energia del jugador " + jugador2.personaje.getEnergia());	System.out.println("Aumento energia del jugador "+ jugador2.personaje.getEnergia());
			if(jugador2.personaje.getEnergia()==jugador2.personaje.getEnergiaInicial()) {
				
				jugador2.personaje.setSprinteo(true);
			//System.out.println("Aumento energia del jugador " + jugador2.personaje.getEnergia());	System.out.println("Se seteo sprinteo a true");
				
			}
			
		}
		
	}




}
