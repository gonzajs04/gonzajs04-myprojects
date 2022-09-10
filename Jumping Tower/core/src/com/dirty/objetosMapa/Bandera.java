package com.dirty.objetosMapa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.jugadores.JugadorBASE;
import com.dirty.pantallas.PantallaFin;
import com.dirty.pantallas.PantallaJuego;
import com.dirty.personajes.Personaje;
import com.dirty.utiles.InteractiveTileObject;
import com.dirty.utiles.Render;
import com.dirty.utiles.Utiles;

import configuraciones.ConfiguracionesCliente;

public class Bandera extends InteractiveTileObject {
	public Bandera(World world, TiledMap map, Rectangle rect, JugadorBASE jugador) {
		super(map, world, rect, jugador);
	
		fixture.setUserData(this);
	}

	@Override
	public void choque(JugadorBASE jugador) {
		
		if(jugador.personaje.getNombre().equals("Goku")) {
			
			
			ConfiguracionesCliente.hc.enviarMensaje("Actualice finjuego");
			
	} else if(jugador.personaje.getNombre().equals("Batman")) {
		

		
		ConfiguracionesCliente.hc.enviarMensaje("Actualice finjuego");
		
			
		}
		//Gdx.app.log("Bandera", "Chocaste");
		
		//hiloCliente.enviarMensaje("" +nombrePersonaje + "tocoBandera/");
		//Lo recibe hiloServidor -> valor = hiloCliente.enviarMensaje("" +nombrePersonaje + "tocoBandera/");
		//valor = "Ganaste" -> hiloServidor enviaValor a CLIENTE -> valor = hiloServidor -> valor.
		//ACA DEBERIA MANDARSE O GUARDARSE EL JUGADOR EN UNA VARIABLE -> EL CLIENTE QUE TOCA LA BANDERA DEBERIA MANDAR UN MENSAJE AL SERVER
		// EL SERTVER RECIBI EL ID O NOMBRE DEL JUGADOR Y ENTONCES, SI ESE JUGADOR ENVIA EL MENSAJE A PANTALLAFIN, SI EL NOMBRE DEL PERSONAJE
		// COINCIDE CON EL GANADOR EN EL SERVER ENTONCES LE MUESTRA GANADOR
		
	}

}
