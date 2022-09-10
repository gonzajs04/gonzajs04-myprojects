package com.dirty.objetosMapa;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.configuraciones.ConfiguracionesSERVER;
import com.dirty.jugadores.JugadorBASESERVER;
import com.dirty.utiles.InteractiveTileObjectSERVER;

import com.dirty.utiles.UtilesSERVER;

public class MonedasSERVER extends InteractiveTileObjectSERVER {

	public MonedasSERVER(World world, TiledMap map, Rectangle rect, JugadorBASESERVER jugador) {
		super(map, world, rect, jugador);
		fixture.setUserData(this);
		setCategoryFilter(UtilesSERVER.COIN_BIT);
	}

	@Override
	public void choque(JugadorBASESERVER jugador) {
		setCategoryFilter(UtilesSERVER.DESTROYED_BIT);
		
		
	}

}
