package com.dirty.objetosMapa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.jugadores.JugadorBASESERVER;
import com.dirty.personajes.PersonajeSERVER;
import com.dirty.utiles.InteractiveTileObjectSERVER;

public class ObjetoSaltarSERVER extends InteractiveTileObjectSERVER {

	public ObjetoSaltarSERVER(World world, TiledMap map, Rectangle rect, JugadorBASESERVER jugador) {
		super(map, world, rect, jugador);
	
		fixture.setUserData(this);
	}

	@Override
	public void choque(JugadorBASESERVER jugador) {
		//Gdx.app.log("ObjetoSaltar", "Chocaste");
		
	}

	


}
