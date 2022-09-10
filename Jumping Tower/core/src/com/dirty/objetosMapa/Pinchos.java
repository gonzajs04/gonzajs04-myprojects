package com.dirty.objetosMapa;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.jugadores.JugadorBASE;
import com.dirty.utiles.InteractiveTileObject;

import configuraciones.ConfiguracionesCliente;

public class Pinchos extends InteractiveTileObject {

	public Pinchos(World world, TiledMap map, Rectangle rect, JugadorBASE jugador) {
		super(map, world, rect, jugador);
		fixture.setUserData(this);
	}

	@Override
	public void choque(JugadorBASE jugador) {
		
		if(jugador.getPersonajeJugador().getNombre().equals("Goku")) {
			
			ConfiguracionesCliente.hc.enviarMensaje("Choco Goku");
			
		} if(jugador.getPersonajeJugador().getNombre().equals("Batman"))
			
		{
			
			ConfiguracionesCliente.hc.enviarMensaje("Choco Batman");
			
		}
		
		
	}
	

}
