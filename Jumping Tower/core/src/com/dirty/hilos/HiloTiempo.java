package com.dirty.hilos;

import com.dirty.jugadores.Jugador1;
import com.dirty.jugadores.Jugador2;
import com.dirty.utiles.Utiles;

public class HiloTiempo extends Thread {
	private int tics = 0, tiempo = 0, segundos = 0;
	private boolean fin = false;
	private Cronometro cronometro;
private Jugador1 jugador1;
private Jugador2 jugador2;
	public HiloTiempo(Cronometro cronometro, Jugador1 jugador1, Jugador2 jugador2) {
		this.cronometro = cronometro;
		this.jugador1=jugador1;
		this.jugador2 = jugador2;
		
		
		//le paso la clase cronometro para que acceda al update del mismo y me cambie los valores
	}

	@Override
	public void run() {
		do {
			if (tics++ % 500000000 == 0) {
				tiempo++;
				if (tiempo % 2 == 0) {
// El valor %2 que puse es mas que nada aproximado, no son un segundo preciso pero se acerca demasiado a eso
					
					if(jugador1.personaje.getVelocidadX()>jugador1.personaje.getVelocidadNormal()) {
						Utiles.rvListener.reducirVelocidad(1, jugador1, jugador2);	
					}
					
					if(jugador1.personaje.getVelocidadX()==jugador1.personaje.getVelocidadNormal()) {
					Utiles.aeListener.aumentarEnergia(3, jugador1, jugador2);
					
					//System.out.println("Aumento energia cada un segundo ");
					}
					
					if(jugador2.personaje.getVelocidadX()>jugador2.personaje.getVelocidadNormal()) {
						Utiles.rvListener.reducirVelocidad(1, jugador1, jugador2);	
					}
					
					if(jugador2.personaje.getVelocidadX()==jugador2.personaje.getVelocidadNormal()) {
					Utiles.aeListener.aumentarEnergia(3, jugador1, jugador2);
					
					//System.out.println("Aumento energia cada un segundo ");
					}
					
					
				
					
					
					//cronometro.update();
					
					
					
					
				}
			}
		} while (!fin);
	}
	
	
	

	public int getSegundos() {
		return segundos;
	}
}
