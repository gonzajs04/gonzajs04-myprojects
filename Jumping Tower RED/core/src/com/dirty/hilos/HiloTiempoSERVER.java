package com.dirty.hilos;

import com.dirty.configuraciones.ConfiguracionesSERVER;
import com.dirty.jugadores.Jugador1SERVER;
import com.dirty.jugadores.Jugador2SERVER;
import com.dirty.utiles.UtilesSERVER;

public class HiloTiempoSERVER extends Thread {
	private int tics = 0, tiempo = 0, segundos = 0;
	private boolean fin = false;
	private CronometroSERVER cronometro;
	private Jugador1SERVER jugador1;
	private Jugador2SERVER jugador2;

	public HiloTiempoSERVER(CronometroSERVER cronometro, Jugador1SERVER jugador1, Jugador2SERVER jugador2) {
		this.cronometro = cronometro;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		;

		// le paso la clase cronometro para que acceda al update del mismo y me cambie
		// los valores
	}

	@Override
	public void run() {
		do {
			if (tics++ % 500000000 == 0) {
				tiempo++;
				if (tiempo % 2 == 0) {
// El valor %2 que puse es mas que nada aproximado, no son un segundo preciso pero se acerca demasiado a eso

					if (jugador1.personaje.getVelocidadX() > jugador1.personaje.getVelocidadNormal()) {
						UtilesSERVER.rvListener.reducirVelocidad(1, jugador1, jugador2);
					}

					if (jugador1.personaje.getVelocidadX() == jugador1.personaje.getVelocidadNormal()) {
						UtilesSERVER.aeListener.aumentarEnergia(3, jugador1, jugador2);         //NO SIRVE POR LO MENCIONADO DE LA VELOCIDAD

						//System.out.println("Aumento energia cada un segundo ");
					}
					restarVidaCadaSegundo();

					
					controlarTiempoMuerte();
					subirEnergiaVelPersonaje();
					cronometro.update();
					
					
					
					
				

				}
			}
		} while (!fin);
	}

	

	private void subirEnergiaVelPersonaje() {
		jugador1.personaje.setEnergiaSalto(1, jugador1.personaje.getEnergiaSalto(), true);
		jugador2.personaje.setEnergiaSalto(2, jugador2.personaje.getEnergiaSalto(), true);
		
	}

	private void controlarTiempoMuerte() {
		if(cronometro.getTiempoJuego()<=0) {
			if(!ConfiguracionesSERVER.ambosPerdieron) {
				ConfiguracionesSERVER.ambosPerdieron=true;
				jugador1.getPersonajeJugador().setVida(0); //No uso SETESTADOVICTORIA PORQUE SOLO SIRVE PARA LA VICTORIA, NO ES MUY FUNCIONAL, PORQUE DEBERIA PODER HACERSE CON LA DERROTA TAMB
				jugador2.getPersonajeJugador().setVida(0);
				ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Fin/Juego/AmbosPerdieron");
				
			}
		}
		
	}

	private void restarVidaCadaSegundo() {
		jugador1.getPersonajeJugador().setVida(1f,true,1);
		jugador2.getPersonajeJugador().setVida(1f,true,2);
		
		
	}

	public int getSegundos() {
		return segundos;
	}
}
