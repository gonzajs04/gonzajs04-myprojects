package com.dirty.objetosMapa;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.jugadores.JugadorBASESERVER;
import com.dirty.utiles.InteractiveTileObjectSERVER;
import com.dirty.utiles.UtilesSERVER;

public class VidaSERVER extends InteractiveTileObjectSERVER{

	public VidaSERVER(TiledMap map, World world, Rectangle rect, JugadorBASESERVER jugador) {
		super(map, world, rect, jugador);
		fixture.setUserData(this);
		setCategoryFilter(UtilesSERVER.VIDA_BIT);
	}

	@Override
	public void choque(JugadorBASESERVER jugador) {
		
		setCategoryFilter(UtilesSERVER.DESTROYED_BIT);
		
	}

}
