package com.dirty.objetosMapa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.jugadores.JugadorBASE;
import com.dirty.personajes.Personaje;
import com.dirty.utiles.InteractiveTileObject;

public class ObjetoSaltar extends InteractiveTileObject {

	public ObjetoSaltar(World world, TiledMap map, Rectangle rect, JugadorBASE jugador) {
		super(map, world, rect, jugador);
	
		fixture.setUserData(this);
	}

	@Override

	public void choque(JugadorBASE jugador) {
	//	Gdx.app.log("ObjetoSaltar", "Chocaste");
		
	}

	


}
