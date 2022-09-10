package com.dirty.objetosMapa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.jugadores.JugadorBASESERVER;
import com.dirty.pantallas.PantallaJuegoSERVER;
import com.dirty.personajes.PersonajeSERVER;
import com.dirty.utiles.InteractiveTileObjectSERVER;
import com.dirty.utiles.RenderSERVER;
import com.dirty.utiles.UtilesSERVER;



public class BanderaSERVER extends InteractiveTileObjectSERVER {
	public BanderaSERVER(World world, TiledMap map, Rectangle rect, JugadorBASESERVER jugador) {
		super(map, world, rect, jugador);
	
		fixture.setUserData(this);
	}

	@Override
	public void choque(JugadorBASESERVER jugador) {
		
		
		if(jugador.personaje.getNombre().equals("Goku")) {
		
		
		
			
	} else {
	
		
		
			
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
